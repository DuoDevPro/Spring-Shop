package com.product_service.service;

//import com.product_service.client.InventoryClient;
//import com.product_service.client.UserProperties;
//import com.product_service.client.InventoryClient;
import com.product_service.client.InventoryClient;
import com.product_service.dtos.ImageDto;
import com.product_service.dtos.Inventory;
import com.product_service.dtos.ProductDto;
import com.product_service.dtos.request.BuyProductRequest;
import com.product_service.dtos.request.ProductRequest;
import com.product_service.dtos.response.BuyProductResponse;
import com.product_service.entity.Category;
import com.product_service.entity.Image;
import com.product_service.entity.Product;
import com.product_service.exceptions.ResourceNotFoundException;
import com.product_service.productMapper.CategoryMapper;
import com.product_service.productMapper.ImageMapper;
import com.product_service.productMapper.ProductMapper;
import com.product_service.repository.CategoryRepository;
import com.product_service.repository.ImageRepository;
import com.product_service.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

   private final ProductRepository productRepository;
   private final ProductMapper productMapper;
   private final ImageRepository imageRepository;
   private final ImageMapper imageMapper;
   private final CategoryRepository categoryRepository;
   private final InventoryClient inventoryClient;

//   private UserProperties userProperties;
   private final WebClient webClient;


    @Override
    @Transactional
    public Product addProduct(ProductRequest request) {

        // ideally if not present it should call addCategory method
        Category category = Optional.ofNullable(categoryRepository.findByName(request.getCategory().getName()))
                .orElseGet(() -> {
                    Category newCategory = new Category(request.getCategory().getName());
                    return categoryRepository.save(newCategory);
                });
        request.setCategory(category);
        Product product = productRepository.save(productMapper.toProduct(request));
        inventoryClient.addInventory(Long.toString(product.getId()),product.getAvailableQuantity());
        return product;
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Product not found!"));    }

    @Override
    public void deleteProductById(Long id) {
        productRepository.findById(id)
                .ifPresentOrElse(productRepository::delete,
                        () -> {throw new ResourceNotFoundException("Product not found!");});
    }

    @Override
    @Transactional
    public Product updateProduct(ProductRequest productRequest, Long productId) {
        return productRepository.findById(productId)
                .map(existingProduct -> updateExistingProduct(existingProduct,productRequest))
                .map(productRepository :: save)
                .orElseThrow(()-> new ResourceNotFoundException("Product not found!"));
    }

    private Product updateExistingProduct(Product existingProduct, ProductRequest request) {
        existingProduct.setProductBrand(request.getProductBrand());
        existingProduct.setProductName(request.getProductName());
        existingProduct.setProductPrice(request.getProductPrice());
        existingProduct.setProductDescription(request.getProductDescription());

        if(existingProduct.getAvailableQuantity() != request.getAvailableQuantity()){
//            inventoryClient.addInventory(existingProduct.getProductId(),request.getAvailableQuantity());
        }
        Category category = categoryRepository.findByName(request.getCategory().getName());
        existingProduct.setCategory(category);
        return  existingProduct;
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> getProductsByCategory(String category) {
        return productRepository.findByCategoryName(category);
    }

    @Override
    public List<Product> getProductsByBrand(String brand) {
        return productRepository.findByProductBrand(brand);
    }

    @Override
    public List<Product> getProductsByCategoryAndBrand(String category, String brand) {
        return productRepository.findByCategoryNameAndProductBrand(category, brand);
    }

    @Override
    public List<Product> getProductsByName(String name) {
        return productRepository.findByProductName(name);
    }

    @Override
    public List<Product> getProductsByBrandAndName(String brand, String name) {
        return productRepository.findByProductBrandAndProductName(brand, name);
    }

    @Override
    public Long countProductsByBrandAndName(String brand, String name) {
        return productRepository.countByProductBrandAndProductName(brand, name);
    }

    @Override
    public List<ProductDto> getConvertedProducts(List<Product> products) {
        return products.stream().map(this::convertToDto).toList();
    }

    @Override
    public ProductDto convertToDto(Product product) {
        ProductDto productDto = productMapper.toProductDto(product);
        List<Image> images = imageRepository.findByProductId(product.getId());
        List<ImageDto> imageDtos = images.stream()
                .map(imageMapper::toImageDto)
                .toList();
        productDto.setProductImages(imageDtos);
        return productDto;
    }

    @Override
    @Transactional(rollbackFor = ResourceNotFoundException.class)
    public List<BuyProductResponse> buyProducts(List<BuyProductRequest> request) {

        var productIds = request
                .stream()
                .map(BuyProductRequest::getId)
                .toList();
        var storedProducts = productRepository.findAllByIdInOrderById(productIds);
        if (productIds.size() != storedProducts.size()) {
            throw new ResourceNotFoundException("One or more products does not exist");
        }
        var sortedRequest = request
                .stream()
                .sorted(Comparator.comparing(BuyProductRequest::getId))
                .toList();
        var purchasedProducts = new ArrayList<BuyProductResponse>();

        for (int i = 0; i < storedProducts.size(); i++) {
            var product = storedProducts.get(i);
            var productRequest = sortedRequest.get(i);
            if (product.getAvailableQuantity() < productRequest.getAvailableQuantity()) {
                throw new ResourceNotFoundException("Insufficient stock quantity for product with ID:: " + productRequest.getId());
            }
            var newAvailableQuantity = product.getAvailableQuantity() - productRequest.getAvailableQuantity();
            product.setAvailableQuantity(newAvailableQuantity);
            productRepository.save(product);
            purchasedProducts.add(productMapper.toBuyProductResponse(product, productRequest.getAvailableQuantity()));
        }

        return purchasedProducts;
    }

    @Override
    public List<Product> findAllByIdInOrderById(List<Long> ids) {
        return productRepository.findAllByIdInOrderById(ids);
    }


    public Inventory addToInventory(String productId, double quantity) {
        Inventory inventory = new Inventory();
        inventory.setProductId(productId);
        inventory.setAvailableQuantity(quantity);
        return webClient.post()
                .uri("http://inventory-service/inventory/addInventory/")
                .body(Mono.just(inventory), Inventory.class)
                .retrieve()
                .bodyToMono(Inventory.class)
                .block();
    }
    // Remaining part - global exception, validation, logs
}
