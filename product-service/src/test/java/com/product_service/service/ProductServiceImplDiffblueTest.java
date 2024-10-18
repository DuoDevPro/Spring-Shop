package com.product_service.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.anyDouble;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.product_service.client.InventoryClient;
import com.product_service.dtos.Inventory;
import com.product_service.dtos.ProductDto;
import com.product_service.dtos.request.BuyProductRequest;
import com.product_service.dtos.request.ProductRequest;
import com.product_service.dtos.response.BuyProductResponse;
import com.product_service.entity.Category;
import com.product_service.entity.Image;
import com.product_service.entity.Product;
import com.product_service.exceptions.ResourceNotFoundException;
import com.product_service.productMapper.ImageMapper;
import com.product_service.productMapper.ProductMapper;
import com.product_service.repository.CategoryRepository;
import com.product_service.repository.ImageRepository;
import com.product_service.repository.ProductRepository;

import java.sql.Blob;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@ContextConfiguration(classes = {ProductServiceImpl.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class ProductServiceImplDiffblueTest {
    @MockBean
    private CategoryRepository categoryRepository;

    @MockBean
    private ImageMapper imageMapper;

    @MockBean
    private ImageRepository imageRepository;

    @MockBean
    private InventoryClient inventoryClient;

    @MockBean
    private ProductMapper productMapper;

    @MockBean
    private ProductRepository productRepository;

    @Autowired
    private ProductServiceImpl productServiceImpl;

    @MockBean
    private WebClient webClient;

    /**
     * Method under test: {@link ProductServiceImpl#addProduct(ProductRequest)}
     */
    @Test
    void testAddProduct() {
        // Arrange
        Category category = new Category();
        category.setId(1L);
        category.setName("Name");
        category.setProducts(new ArrayList<>());

        Product product = new Product();
        product.setAvailableQuantity(10.0d);
        product.setCategory(category);
        product.setId(1L);
        product.setProductBrand("Product Brand");
        product.setProductDescription("Product Description");
        product.setProductId("42");
        product.setProductImages(new ArrayList<>());
        product.setProductName("Product Name");
        product.setProductPrice(10.0d);
        when(productRepository.save(Mockito.<Product>any())).thenReturn(product);

        Category category2 = new Category();
        category2.setId(1L);
        category2.setName("Name");
        category2.setProducts(new ArrayList<>());

        Product product2 = new Product();
        product2.setAvailableQuantity(10.0d);
        product2.setCategory(category2);
        product2.setId(1L);
        product2.setProductBrand("Product Brand");
        product2.setProductDescription("Product Description");
        product2.setProductId("42");
        product2.setProductImages(new ArrayList<>());
        product2.setProductName("Product Name");
        product2.setProductPrice(10.0d);
        when(productMapper.toProduct(Mockito.<ProductRequest>any())).thenReturn(product2);

        Category category3 = new Category();
        category3.setId(1L);
        category3.setName("Name");
        category3.setProducts(new ArrayList<>());

        Category category4 = new Category();
        category4.setId(1L);
        category4.setName("Name");
        category4.setProducts(new ArrayList<>());
        when(categoryRepository.findByName(Mockito.<String>any())).thenReturn(category3);
        when(categoryRepository.save(Mockito.<Category>any())).thenReturn(category4);
        Inventory buildResult = Inventory.builder().availableQuantity(10.0d).id(1L).productId("42").build();
        when(inventoryClient.addInventory(Mockito.<String>any(), anyDouble())).thenReturn(buildResult);

        Category category5 = new Category();
        category5.setId(1L);
        category5.setName("Name");
        category5.setProducts(new ArrayList<>());
        ProductRequest request = ProductRequest.builder()
                .availableQuantity(10.0d)
                .category(category5)
                .productBrand("Product Brand")
                .productDescription("Product Description")
                .productName("Product Name")
                .productPrice(10.0d)
                .build();

        // Act
        Product actualAddProductResult = productServiceImpl.addProduct(request);

        // Assert
        verify(inventoryClient).addInventory(eq("1"), eq(10.0d));
        verify(productMapper).toProduct(isA(ProductRequest.class));
        verify(categoryRepository).findByName(eq("Name"));
        verify(productRepository).save(isA(Product.class));
        assertSame(product, actualAddProductResult);
    }

    /**
     * Method under test: {@link ProductServiceImpl#addProduct(ProductRequest)}
     */
    @Test
    void testAddProduct2() {
        // Arrange
        Category category = new Category();
        category.setId(1L);
        category.setName("Name");
        category.setProducts(new ArrayList<>());

        Product product = new Product();
        product.setAvailableQuantity(10.0d);
        product.setCategory(category);
        product.setId(1L);
        product.setProductBrand("Product Brand");
        product.setProductDescription("Product Description");
        product.setProductId("42");
        product.setProductImages(new ArrayList<>());
        product.setProductName("Product Name");
        product.setProductPrice(10.0d);
        when(productRepository.save(Mockito.<Product>any())).thenReturn(product);

        Category category2 = new Category();
        category2.setId(1L);
        category2.setName("Name");
        category2.setProducts(new ArrayList<>());

        Product product2 = new Product();
        product2.setAvailableQuantity(10.0d);
        product2.setCategory(category2);
        product2.setId(1L);
        product2.setProductBrand("Product Brand");
        product2.setProductDescription("Product Description");
        product2.setProductId("42");
        product2.setProductImages(new ArrayList<>());
        product2.setProductName("Product Name");
        product2.setProductPrice(10.0d);
        when(productMapper.toProduct(Mockito.<ProductRequest>any())).thenReturn(product2);

        Category category3 = new Category();
        category3.setId(1L);
        category3.setName("Name");
        category3.setProducts(new ArrayList<>());

        Category category4 = new Category();
        category4.setId(1L);
        category4.setName("Name");
        category4.setProducts(new ArrayList<>());
        when(categoryRepository.findByName(Mockito.<String>any())).thenReturn(category3);
        when(categoryRepository.save(Mockito.<Category>any())).thenReturn(category4);
        when(inventoryClient.addInventory(Mockito.<String>any(), anyDouble()))
                .thenThrow(new ResourceNotFoundException("foo"));

        Category category5 = new Category();
        category5.setId(1L);
        category5.setName("Name");
        category5.setProducts(new ArrayList<>());
        ProductRequest request = ProductRequest.builder()
                .availableQuantity(10.0d)
                .category(category5)
                .productBrand("Product Brand")
                .productDescription("Product Description")
                .productName("Product Name")
                .productPrice(10.0d)
                .build();

        // Act and Assert
        assertThrows(ResourceNotFoundException.class, () -> productServiceImpl.addProduct(request));
        verify(inventoryClient).addInventory(eq("1"), eq(10.0d));
        verify(productMapper).toProduct(isA(ProductRequest.class));
        verify(categoryRepository).findByName(eq("Name"));
        verify(productRepository).save(isA(Product.class));
    }

    /**
     * Method under test: {@link ProductServiceImpl#addProduct(ProductRequest)}
     */
    @Test
    void testAddProduct3() {
        // Arrange
        Category category = new Category();
        category.setId(1L);
        category.setName("Name");
        category.setProducts(new ArrayList<>());
        Product product = mock(Product.class);
        when(product.getAvailableQuantity()).thenReturn(10.0d);
        when(product.getId()).thenReturn(1L);
        doNothing().when(product).setAvailableQuantity(anyDouble());
        doNothing().when(product).setCategory(Mockito.<Category>any());
        doNothing().when(product).setId(Mockito.<Long>any());
        doNothing().when(product).setProductBrand(Mockito.<String>any());
        doNothing().when(product).setProductDescription(Mockito.<String>any());
        doNothing().when(product).setProductId(Mockito.<String>any());
        doNothing().when(product).setProductImages(Mockito.<List<Image>>any());
        doNothing().when(product).setProductName(Mockito.<String>any());
        doNothing().when(product).setProductPrice(anyDouble());
        product.setAvailableQuantity(10.0d);
        product.setCategory(category);
        product.setId(1L);
        product.setProductBrand("Product Brand");
        product.setProductDescription("Product Description");
        product.setProductId("42");
        product.setProductImages(new ArrayList<>());
        product.setProductName("Product Name");
        product.setProductPrice(10.0d);
        when(productRepository.save(Mockito.<Product>any())).thenReturn(product);

        Category category2 = new Category();
        category2.setId(1L);
        category2.setName("Name");
        category2.setProducts(new ArrayList<>());

        Product product2 = new Product();
        product2.setAvailableQuantity(10.0d);
        product2.setCategory(category2);
        product2.setId(1L);
        product2.setProductBrand("Product Brand");
        product2.setProductDescription("Product Description");
        product2.setProductId("42");
        product2.setProductImages(new ArrayList<>());
        product2.setProductName("Product Name");
        product2.setProductPrice(10.0d);
        when(productMapper.toProduct(Mockito.<ProductRequest>any())).thenReturn(product2);

        Category category3 = new Category();
        category3.setId(1L);
        category3.setName("Name");
        category3.setProducts(new ArrayList<>());

        Category category4 = new Category();
        category4.setId(1L);
        category4.setName("Name");
        category4.setProducts(new ArrayList<>());
        when(categoryRepository.findByName(Mockito.<String>any())).thenReturn(category3);
        when(categoryRepository.save(Mockito.<Category>any())).thenReturn(category4);
        Inventory buildResult = Inventory.builder().availableQuantity(10.0d).id(1L).productId("42").build();
        when(inventoryClient.addInventory(Mockito.<String>any(), anyDouble())).thenReturn(buildResult);

        Category category5 = new Category();
        category5.setId(1L);
        category5.setName("Name");
        category5.setProducts(new ArrayList<>());
        ProductRequest request = ProductRequest.builder()
                .availableQuantity(10.0d)
                .category(category5)
                .productBrand("Product Brand")
                .productDescription("Product Description")
                .productName("Product Name")
                .productPrice(10.0d)
                .build();

        // Act
        productServiceImpl.addProduct(request);

        // Assert
        verify(inventoryClient).addInventory(eq("1"), eq(10.0d));
        verify(product).getAvailableQuantity();
        verify(product).getId();
        verify(product).setAvailableQuantity(eq(10.0d));
        verify(product).setCategory(isA(Category.class));
        verify(product).setId(eq(1L));
        verify(product).setProductBrand(eq("Product Brand"));
        verify(product).setProductDescription(eq("Product Description"));
        verify(product).setProductId(eq("42"));
        verify(product).setProductImages(isA(List.class));
        verify(product).setProductName(eq("Product Name"));
        verify(product).setProductPrice(eq(10.0d));
        verify(productMapper).toProduct(isA(ProductRequest.class));
        verify(categoryRepository).findByName(eq("Name"));
        verify(productRepository).save(isA(Product.class));
    }

    /**
     * Method under test: {@link ProductServiceImpl#getProductById(Long)}
     */
    @Test
    void testGetProductById() {
        // Arrange
        Category category = new Category();
        category.setId(1L);
        category.setName("Name");
        category.setProducts(new ArrayList<>());

        Product product = new Product();
        product.setAvailableQuantity(10.0d);
        product.setCategory(category);
        product.setId(1L);
        product.setProductBrand("Product Brand");
        product.setProductDescription("Product Description");
        product.setProductId("42");
        product.setProductImages(new ArrayList<>());
        product.setProductName("Product Name");
        product.setProductPrice(10.0d);
        Optional<Product> ofResult = Optional.of(product);
        when(productRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        // Act
        Product actualProductById = productServiceImpl.getProductById(1L);

        // Assert
        verify(productRepository).findById(eq(1L));
        assertSame(product, actualProductById);
    }

    /**
     * Method under test: {@link ProductServiceImpl#getProductById(Long)}
     */
    @Test
    void testGetProductById2() {
        // Arrange
        Optional<Product> emptyResult = Optional.empty();
        when(productRepository.findById(Mockito.<Long>any())).thenReturn(emptyResult);

        // Act and Assert
        assertThrows(ResourceNotFoundException.class, () -> productServiceImpl.getProductById(1L));
        verify(productRepository).findById(eq(1L));
    }

    /**
     * Method under test: {@link ProductServiceImpl#getProductById(Long)}
     */
    @Test
    void testGetProductById3() {
        // Arrange
        when(productRepository.findById(Mockito.<Long>any()))
                .thenThrow(new ResourceNotFoundException("Product not found!"));

        // Act and Assert
        assertThrows(ResourceNotFoundException.class, () -> productServiceImpl.getProductById(1L));
        verify(productRepository).findById(eq(1L));
    }

    /**
     * Method under test: {@link ProductServiceImpl#deleteProductById(Long)}
     */
    @Test
    void testDeleteProductById() {
        // Arrange
        Category category = new Category();
        category.setId(1L);
        category.setName("Name");
        category.setProducts(new ArrayList<>());

        Product product = new Product();
        product.setAvailableQuantity(10.0d);
        product.setCategory(category);
        product.setId(1L);
        product.setProductBrand("Product Brand");
        product.setProductDescription("Product Description");
        product.setProductId("42");
        product.setProductImages(new ArrayList<>());
        product.setProductName("Product Name");
        product.setProductPrice(10.0d);
        Optional<Product> ofResult = Optional.of(product);
        doNothing().when(productRepository).delete(Mockito.<Product>any());
        when(productRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        // Act
        productServiceImpl.deleteProductById(1L);

        // Assert that nothing has changed
        verify(productRepository).delete(isA(Product.class));
        verify(productRepository).findById(eq(1L));
    }

    /**
     * Method under test: {@link ProductServiceImpl#deleteProductById(Long)}
     */
    @Test
    void testDeleteProductById2() {
        // Arrange
        Optional<Product> emptyResult = Optional.empty();
        when(productRepository.findById(Mockito.<Long>any())).thenReturn(emptyResult);

        // Act and Assert
        assertThrows(ResourceNotFoundException.class, () -> productServiceImpl.deleteProductById(1L));
        verify(productRepository).findById(eq(1L));
    }

    /**
     * Method under test:
     * {@link ProductServiceImpl#updateProduct(ProductRequest, Long)}
     */
    @Test
    void testUpdateProduct() {
        // Arrange
        Optional<Product> emptyResult = Optional.empty();
        when(productRepository.findById(Mockito.<Long>any())).thenReturn(emptyResult);

        // Act and Assert
        assertThrows(ResourceNotFoundException.class, () -> productServiceImpl.updateProduct(null, 1L));
        verify(productRepository).findById(eq(1L));
    }

    /**
     * Method under test:
     * {@link ProductServiceImpl#updateProduct(ProductRequest, Long)}
     */
    @Test
    void testUpdateProduct2() {
        // Arrange
        Category category = new Category();
        category.setId(1L);
        category.setName("Name");
        category.setProducts(new ArrayList<>());

        Product product = new Product();
        product.setAvailableQuantity(10.0d);
        product.setCategory(category);
        product.setId(1L);
        product.setProductBrand("Product Brand");
        product.setProductDescription("Product Description");
        product.setProductId("42");
        product.setProductImages(new ArrayList<>());
        product.setProductName("Product Name");
        product.setProductPrice(10.0d);
        Optional<Product> ofResult = Optional.of(product);

        Category category2 = new Category();
        category2.setId(1L);
        category2.setName("Name");
        category2.setProducts(new ArrayList<>());

        Product product2 = new Product();
        product2.setAvailableQuantity(10.0d);
        product2.setCategory(category2);
        product2.setId(1L);
        product2.setProductBrand("Product Brand");
        product2.setProductDescription("Product Description");
        product2.setProductId("42");
        product2.setProductImages(new ArrayList<>());
        product2.setProductName("Product Name");
        product2.setProductPrice(10.0d);
        when(productRepository.save(Mockito.<Product>any())).thenReturn(product2);
        when(productRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        Category category3 = new Category();
        category3.setId(1L);
        category3.setName("Name");
        category3.setProducts(new ArrayList<>());
        when(categoryRepository.findByName(Mockito.<String>any())).thenReturn(category3);

        Category category4 = new Category();
        category4.setId(1L);
        category4.setName("Name");
        category4.setProducts(new ArrayList<>());
        ProductRequest productRequest = ProductRequest.builder()
                .availableQuantity(10.0d)
                .category(category4)
                .productBrand("Product Brand")
                .productDescription("Product Description")
                .productName("Product Name")
                .productPrice(10.0d)
                .build();

        // Act
        Product actualUpdateProductResult = productServiceImpl.updateProduct(productRequest, 1L);

        // Assert
        verify(categoryRepository).findByName(eq("Name"));
        verify(productRepository).findById(eq(1L));
        verify(productRepository).save(isA(Product.class));
        assertSame(product2, actualUpdateProductResult);
    }

    /**
     * Method under test:
     * {@link ProductServiceImpl#updateProduct(ProductRequest, Long)}
     */
    @Test
    void testUpdateProduct3() {
        // Arrange
        Category category = new Category();
        category.setId(1L);
        category.setName("Name");
        category.setProducts(new ArrayList<>());

        Product product = new Product();
        product.setAvailableQuantity(10.0d);
        product.setCategory(category);
        product.setId(1L);
        product.setProductBrand("Product Brand");
        product.setProductDescription("Product Description");
        product.setProductId("42");
        product.setProductImages(new ArrayList<>());
        product.setProductName("Product Name");
        product.setProductPrice(10.0d);
        Optional<Product> ofResult = Optional.of(product);
        when(productRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        when(categoryRepository.findByName(Mockito.<String>any()))
                .thenThrow(new ResourceNotFoundException("Product not found!"));

        Category category2 = new Category();
        category2.setId(1L);
        category2.setName("Name");
        category2.setProducts(new ArrayList<>());
        ProductRequest productRequest = ProductRequest.builder()
                .availableQuantity(10.0d)
                .category(category2)
                .productBrand("Product Brand")
                .productDescription("Product Description")
                .productName("Product Name")
                .productPrice(10.0d)
                .build();

        // Act and Assert
        assertThrows(ResourceNotFoundException.class, () -> productServiceImpl.updateProduct(productRequest, 1L));
        verify(categoryRepository).findByName(eq("Name"));
        verify(productRepository).findById(eq(1L));
    }

    /**
     * Method under test:
     * {@link ProductServiceImpl#updateProduct(ProductRequest, Long)}
     */
    @Test
    void testUpdateProduct4() {
        // Arrange
        Category category = new Category();
        category.setId(1L);
        category.setName("Name");
        category.setProducts(new ArrayList<>());
        Product product = mock(Product.class);
        when(product.getAvailableQuantity()).thenReturn(10.0d);
        doNothing().when(product).setAvailableQuantity(anyDouble());
        doNothing().when(product).setCategory(Mockito.<Category>any());
        doNothing().when(product).setId(Mockito.<Long>any());
        doNothing().when(product).setProductBrand(Mockito.<String>any());
        doNothing().when(product).setProductDescription(Mockito.<String>any());
        doNothing().when(product).setProductId(Mockito.<String>any());
        doNothing().when(product).setProductImages(Mockito.<List<Image>>any());
        doNothing().when(product).setProductName(Mockito.<String>any());
        doNothing().when(product).setProductPrice(anyDouble());
        product.setAvailableQuantity(10.0d);
        product.setCategory(category);
        product.setId(1L);
        product.setProductBrand("Product Brand");
        product.setProductDescription("Product Description");
        product.setProductId("42");
        product.setProductImages(new ArrayList<>());
        product.setProductName("Product Name");
        product.setProductPrice(10.0d);
        Optional<Product> ofResult = Optional.of(product);

        Category category2 = new Category();
        category2.setId(1L);
        category2.setName("Name");
        category2.setProducts(new ArrayList<>());

        Product product2 = new Product();
        product2.setAvailableQuantity(10.0d);
        product2.setCategory(category2);
        product2.setId(1L);
        product2.setProductBrand("Product Brand");
        product2.setProductDescription("Product Description");
        product2.setProductId("42");
        product2.setProductImages(new ArrayList<>());
        product2.setProductName("Product Name");
        product2.setProductPrice(10.0d);
        when(productRepository.save(Mockito.<Product>any())).thenReturn(product2);
        when(productRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        Category category3 = new Category();
        category3.setId(1L);
        category3.setName("Name");
        category3.setProducts(new ArrayList<>());
        when(categoryRepository.findByName(Mockito.<String>any())).thenReturn(category3);

        Category category4 = new Category();
        category4.setId(1L);
        category4.setName("Name");
        category4.setProducts(new ArrayList<>());
        ProductRequest productRequest = ProductRequest.builder()
                .availableQuantity(10.0d)
                .category(category4)
                .productBrand("Product Brand")
                .productDescription("Product Description")
                .productName("Product Name")
                .productPrice(10.0d)
                .build();

        // Act
        Product actualUpdateProductResult = productServiceImpl.updateProduct(productRequest, 1L);

        // Assert
        verify(product).getAvailableQuantity();
        verify(product).setAvailableQuantity(eq(10.0d));
        verify(product, atLeast(1)).setCategory(Mockito.<Category>any());
        verify(product).setId(eq(1L));
        verify(product, atLeast(1)).setProductBrand(eq("Product Brand"));
        verify(product, atLeast(1)).setProductDescription(eq("Product Description"));
        verify(product).setProductId(eq("42"));
        verify(product).setProductImages(isA(List.class));
        verify(product, atLeast(1)).setProductName(eq("Product Name"));
        verify(product, atLeast(1)).setProductPrice(eq(10.0d));
        verify(categoryRepository).findByName(eq("Name"));
        verify(productRepository).findById(eq(1L));
        verify(productRepository).save(isA(Product.class));
        assertSame(product2, actualUpdateProductResult);
    }

    /**
     * Method under test: {@link ProductServiceImpl#getAllProducts()}
     */
    @Test
    void testGetAllProducts() {
        // Arrange
        ArrayList<Product> productList = new ArrayList<>();
        when(productRepository.findAll()).thenReturn(productList);

        // Act
        List<Product> actualAllProducts = productServiceImpl.getAllProducts();

        // Assert
        verify(productRepository).findAll();
        assertTrue(actualAllProducts.isEmpty());
        assertSame(productList, actualAllProducts);
    }

    /**
     * Method under test: {@link ProductServiceImpl#getAllProducts()}
     */
    @Test
    void testGetAllProducts2() {
        // Arrange
        when(productRepository.findAll()).thenThrow(new ResourceNotFoundException("foo"));

        // Act and Assert
        assertThrows(ResourceNotFoundException.class, () -> productServiceImpl.getAllProducts());
        verify(productRepository).findAll();
    }

    /**
     * Method under test: {@link ProductServiceImpl#getProductsByCategory(String)}
     */
    @Test
    void testGetProductsByCategory() {
        // Arrange
        ArrayList<Product> productList = new ArrayList<>();
        when(productRepository.findByCategoryName(Mockito.<String>any())).thenReturn(productList);

        // Act
        List<Product> actualProductsByCategory = productServiceImpl.getProductsByCategory("Category");

        // Assert
        verify(productRepository).findByCategoryName(eq("Category"));
        assertTrue(actualProductsByCategory.isEmpty());
        assertSame(productList, actualProductsByCategory);
    }

    /**
     * Method under test: {@link ProductServiceImpl#getProductsByCategory(String)}
     */
    @Test
    void testGetProductsByCategory2() {
        // Arrange
        when(productRepository.findByCategoryName(Mockito.<String>any())).thenThrow(new ResourceNotFoundException("foo"));

        // Act and Assert
        assertThrows(ResourceNotFoundException.class, () -> productServiceImpl.getProductsByCategory("Category"));
        verify(productRepository).findByCategoryName(eq("Category"));
    }

    /**
     * Method under test: {@link ProductServiceImpl#getProductsByBrand(String)}
     */
    @Test
    void testGetProductsByBrand() {
        // Arrange
        ArrayList<Product> productList = new ArrayList<>();
        when(productRepository.findByProductBrand(Mockito.<String>any())).thenReturn(productList);

        // Act
        List<Product> actualProductsByBrand = productServiceImpl.getProductsByBrand("Brand");

        // Assert
        verify(productRepository).findByProductBrand(eq("Brand"));
        assertTrue(actualProductsByBrand.isEmpty());
        assertSame(productList, actualProductsByBrand);
    }

    /**
     * Method under test: {@link ProductServiceImpl#getProductsByBrand(String)}
     */
    @Test
    void testGetProductsByBrand2() {
        // Arrange
        when(productRepository.findByProductBrand(Mockito.<String>any())).thenThrow(new ResourceNotFoundException("foo"));

        // Act and Assert
        assertThrows(ResourceNotFoundException.class, () -> productServiceImpl.getProductsByBrand("Brand"));
        verify(productRepository).findByProductBrand(eq("Brand"));
    }

    /**
     * Method under test:
     * {@link ProductServiceImpl#getProductsByCategoryAndBrand(String, String)}
     */
    @Test
    void testGetProductsByCategoryAndBrand() {
        // Arrange
        ArrayList<Product> productList = new ArrayList<>();
        when(productRepository.findByCategoryNameAndProductBrand(Mockito.<String>any(), Mockito.<String>any()))
                .thenReturn(productList);

        // Act
        List<Product> actualProductsByCategoryAndBrand = productServiceImpl.getProductsByCategoryAndBrand("Category",
                "Brand");

        // Assert
        verify(productRepository).findByCategoryNameAndProductBrand(eq("Category"), eq("Brand"));
        assertTrue(actualProductsByCategoryAndBrand.isEmpty());
        assertSame(productList, actualProductsByCategoryAndBrand);
    }

    /**
     * Method under test:
     * {@link ProductServiceImpl#getProductsByCategoryAndBrand(String, String)}
     */
    @Test
    void testGetProductsByCategoryAndBrand2() {
        // Arrange
        when(productRepository.findByCategoryNameAndProductBrand(Mockito.<String>any(), Mockito.<String>any()))
                .thenThrow(new ResourceNotFoundException("foo"));

        // Act and Assert
        assertThrows(ResourceNotFoundException.class,
                () -> productServiceImpl.getProductsByCategoryAndBrand("Category", "Brand"));
        verify(productRepository).findByCategoryNameAndProductBrand(eq("Category"), eq("Brand"));
    }

    /**
     * Method under test: {@link ProductServiceImpl#getProductsByName(String)}
     */
    @Test
    void testGetProductsByName() {
        // Arrange
        ArrayList<Product> productList = new ArrayList<>();
        when(productRepository.findByProductName(Mockito.<String>any())).thenReturn(productList);

        // Act
        List<Product> actualProductsByName = productServiceImpl.getProductsByName("Name");

        // Assert
        verify(productRepository).findByProductName(eq("Name"));
        assertTrue(actualProductsByName.isEmpty());
        assertSame(productList, actualProductsByName);
    }

    /**
     * Method under test: {@link ProductServiceImpl#getProductsByName(String)}
     */
    @Test
    void testGetProductsByName2() {
        // Arrange
        when(productRepository.findByProductName(Mockito.<String>any())).thenThrow(new ResourceNotFoundException("foo"));

        // Act and Assert
        assertThrows(ResourceNotFoundException.class, () -> productServiceImpl.getProductsByName("Name"));
        verify(productRepository).findByProductName(eq("Name"));
    }

    /**
     * Method under test:
     * {@link ProductServiceImpl#getProductsByBrandAndName(String, String)}
     */
    @Test
    void testGetProductsByBrandAndName() {
        // Arrange
        ArrayList<Product> productList = new ArrayList<>();
        when(productRepository.findByProductBrandAndProductName(Mockito.<String>any(), Mockito.<String>any()))
                .thenReturn(productList);

        // Act
        List<Product> actualProductsByBrandAndName = productServiceImpl.getProductsByBrandAndName("Brand", "Name");

        // Assert
        verify(productRepository).findByProductBrandAndProductName(eq("Brand"), eq("Name"));
        assertTrue(actualProductsByBrandAndName.isEmpty());
        assertSame(productList, actualProductsByBrandAndName);
    }

    /**
     * Method under test:
     * {@link ProductServiceImpl#getProductsByBrandAndName(String, String)}
     */
    @Test
    void testGetProductsByBrandAndName2() {
        // Arrange
        when(productRepository.findByProductBrandAndProductName(Mockito.<String>any(), Mockito.<String>any()))
                .thenThrow(new ResourceNotFoundException("foo"));

        // Act and Assert
        assertThrows(ResourceNotFoundException.class, () -> productServiceImpl.getProductsByBrandAndName("Brand", "Name"));
        verify(productRepository).findByProductBrandAndProductName(eq("Brand"), eq("Name"));
    }

    /**
     * Method under test:
     * {@link ProductServiceImpl#countProductsByBrandAndName(String, String)}
     */
    @Test
    void testCountProductsByBrandAndName() {
        // Arrange
        when(productRepository.countByProductBrandAndProductName(Mockito.<String>any(), Mockito.<String>any()))
                .thenReturn(3L);

        // Act
        Long actualCountProductsByBrandAndNameResult = productServiceImpl.countProductsByBrandAndName("Brand", "Name");

        // Assert
        verify(productRepository).countByProductBrandAndProductName(eq("Brand"), eq("Name"));
        assertEquals(3L, actualCountProductsByBrandAndNameResult.longValue());
    }

    /**
     * Method under test:
     * {@link ProductServiceImpl#countProductsByBrandAndName(String, String)}
     */
    @Test
    void testCountProductsByBrandAndName2() {
        // Arrange
        when(productRepository.countByProductBrandAndProductName(Mockito.<String>any(), Mockito.<String>any()))
                .thenThrow(new ResourceNotFoundException("foo"));

        // Act and Assert
        assertThrows(ResourceNotFoundException.class,
                () -> productServiceImpl.countProductsByBrandAndName("Brand", "Name"));
        verify(productRepository).countByProductBrandAndProductName(eq("Brand"), eq("Name"));
    }

    /**
     * Method under test: {@link ProductServiceImpl#getConvertedProducts(List)}
     */
    @Test
    void testGetConvertedProducts() {
        // Arrange, Act and Assert
        assertTrue(productServiceImpl.getConvertedProducts(new ArrayList<>()).isEmpty());
    }

    /**
     * Method under test: {@link ProductServiceImpl#getConvertedProducts(List)}
     */
    @Test
    void testGetConvertedProducts2() {
        // Arrange
        Category category = new Category();
        category.setId(1L);
        category.setName("Name");
        category.setProducts(new ArrayList<>());
        ProductDto.ProductDtoBuilder productIdResult = ProductDto.builder()
                .availableQuantity(10.0d)
                .category(category)
                .id(1L)
                .productBrand("Product Brand")
                .productDescription("Product Description")
                .productId("42");
        ProductDto buildResult = productIdResult.productImages(new ArrayList<>())
                .productName("Product Name")
                .productPrice(10.0d)
                .build();
        when(productMapper.toProductDto(Mockito.<Product>any())).thenReturn(buildResult);
        when(imageRepository.findByProductId(Mockito.<Long>any())).thenReturn(new ArrayList<>());

        Category category2 = new Category();
        category2.setId(1L);
        category2.setName("Name");
        category2.setProducts(new ArrayList<>());

        Product product = new Product();
        product.setAvailableQuantity(10.0d);
        product.setCategory(category2);
        product.setId(1L);
        product.setProductBrand("Product Brand");
        product.setProductDescription("Product Description");
        product.setProductId("42");
        product.setProductImages(new ArrayList<>());
        product.setProductName("Product Name");
        product.setProductPrice(10.0d);

        ArrayList<Product> products = new ArrayList<>();
        products.add(product);

        // Act
        List<ProductDto> actualConvertedProducts = productServiceImpl.getConvertedProducts(products);

        // Assert
        verify(productMapper).toProductDto(isA(Product.class));
        verify(imageRepository).findByProductId(eq(1L));
        assertEquals(1, actualConvertedProducts.size());
        ProductDto getResult = actualConvertedProducts.get(0);
        assertEquals("42", getResult.getProductId());
        assertEquals("Product Brand", getResult.getProductBrand());
        assertEquals("Product Description", getResult.getProductDescription());
        assertEquals("Product Name", getResult.getProductName());
        assertEquals(10.0d, getResult.getAvailableQuantity());
        assertEquals(10.0d, getResult.getProductPrice());
        assertEquals(1L, getResult.getId().longValue());
        assertTrue(getResult.getProductImages().isEmpty());
        assertSame(category, getResult.getCategory());
    }

    /**
     * Method under test: {@link ProductServiceImpl#getConvertedProducts(List)}
     */
    @Test
    void testGetConvertedProducts3() {
        // Arrange
        Category category = new Category();
        category.setId(1L);
        category.setName("Name");
        category.setProducts(new ArrayList<>());
        ProductDto.ProductDtoBuilder productIdResult = ProductDto.builder()
                .availableQuantity(10.0d)
                .category(category)
                .id(1L)
                .productBrand("Product Brand")
                .productDescription("Product Description")
                .productId("42");
        ProductDto buildResult = productIdResult.productImages(new ArrayList<>())
                .productName("Product Name")
                .productPrice(10.0d)
                .build();
        when(productMapper.toProductDto(Mockito.<Product>any())).thenReturn(buildResult);
        when(imageRepository.findByProductId(Mockito.<Long>any())).thenThrow(new ResourceNotFoundException("foo"));

        Category category2 = new Category();
        category2.setId(1L);
        category2.setName("Name");
        category2.setProducts(new ArrayList<>());

        Product product = new Product();
        product.setAvailableQuantity(10.0d);
        product.setCategory(category2);
        product.setId(1L);
        product.setProductBrand("Product Brand");
        product.setProductDescription("Product Description");
        product.setProductId("42");
        product.setProductImages(new ArrayList<>());
        product.setProductName("Product Name");
        product.setProductPrice(10.0d);

        ArrayList<Product> products = new ArrayList<>();
        products.add(product);

        // Act and Assert
        assertThrows(ResourceNotFoundException.class, () -> productServiceImpl.getConvertedProducts(products));
        verify(productMapper).toProductDto(isA(Product.class));
        verify(imageRepository).findByProductId(eq(1L));
    }

    /**
     * Method under test: {@link ProductServiceImpl#getConvertedProducts(List)}
     */
    @Test
    void testGetConvertedProducts4() {
        // Arrange
        Category category = new Category();
        category.setId(1L);
        category.setName("Name");
        category.setProducts(new ArrayList<>());
        ProductDto.ProductDtoBuilder productIdResult = ProductDto.builder()
                .availableQuantity(10.0d)
                .category(category)
                .id(1L)
                .productBrand("Product Brand")
                .productDescription("Product Description")
                .productId("42");
        ProductDto buildResult = productIdResult.productImages(new ArrayList<>())
                .productName("Product Name")
                .productPrice(10.0d)
                .build();
        when(productMapper.toProductDto(Mockito.<Product>any())).thenReturn(buildResult);

        Category category2 = new Category();
        category2.setId(1L);
        category2.setName("Name");
        category2.setProducts(new ArrayList<>());

        Product product = new Product();
        product.setAvailableQuantity(10.0d);
        product.setCategory(category2);
        product.setId(1L);
        product.setProductBrand("Product Brand");
        product.setProductDescription("Product Description");
        product.setProductId("42");
        product.setProductImages(new ArrayList<>());
        product.setProductName("Product Name");
        product.setProductPrice(10.0d);

        Image image = new Image();
        image.setDownloadUrl("https://example.org/example");
        image.setFileName("foo.txt");
        image.setFileType("File Type");
        image.setId(1L);
        image.setImage(mock(Blob.class));
        image.setProduct(product);

        ArrayList<Image> imageList = new ArrayList<>();
        imageList.add(image);
        when(imageRepository.findByProductId(Mockito.<Long>any())).thenReturn(imageList);
        when(imageMapper.toImageDto(Mockito.<Image>any())).thenThrow(new ResourceNotFoundException("foo"));

        Category category3 = new Category();
        category3.setId(1L);
        category3.setName("Name");
        category3.setProducts(new ArrayList<>());

        Product product2 = new Product();
        product2.setAvailableQuantity(10.0d);
        product2.setCategory(category3);
        product2.setId(1L);
        product2.setProductBrand("Product Brand");
        product2.setProductDescription("Product Description");
        product2.setProductId("42");
        product2.setProductImages(new ArrayList<>());
        product2.setProductName("Product Name");
        product2.setProductPrice(10.0d);

        ArrayList<Product> products = new ArrayList<>();
        products.add(product2);

        // Act and Assert
        assertThrows(ResourceNotFoundException.class, () -> productServiceImpl.getConvertedProducts(products));
        verify(imageMapper).toImageDto(isA(Image.class));
        verify(productMapper).toProductDto(isA(Product.class));
        verify(imageRepository).findByProductId(eq(1L));
    }

    /**
     * Method under test: {@link ProductServiceImpl#convertToDto(Product)}
     */
    @Test
    void testConvertToDto() {
        // Arrange
        Category category = new Category();
        category.setId(1L);
        category.setName("Name");
        category.setProducts(new ArrayList<>());
        ProductDto.ProductDtoBuilder productIdResult = ProductDto.builder()
                .availableQuantity(10.0d)
                .category(category)
                .id(1L)
                .productBrand("Product Brand")
                .productDescription("Product Description")
                .productId("42");
        ProductDto buildResult = productIdResult.productImages(new ArrayList<>())
                .productName("Product Name")
                .productPrice(10.0d)
                .build();
        when(productMapper.toProductDto(Mockito.<Product>any())).thenReturn(buildResult);
        when(imageRepository.findByProductId(Mockito.<Long>any())).thenReturn(new ArrayList<>());

        Category category2 = new Category();
        category2.setId(1L);
        category2.setName("Name");
        category2.setProducts(new ArrayList<>());

        Product product = new Product();
        product.setAvailableQuantity(10.0d);
        product.setCategory(category2);
        product.setId(1L);
        product.setProductBrand("Product Brand");
        product.setProductDescription("Product Description");
        product.setProductId("42");
        product.setProductImages(new ArrayList<>());
        product.setProductName("Product Name");
        product.setProductPrice(10.0d);

        // Act
        ProductDto actualConvertToDtoResult = productServiceImpl.convertToDto(product);

        // Assert
        verify(productMapper).toProductDto(isA(Product.class));
        verify(imageRepository).findByProductId(eq(1L));
        assertEquals("42", actualConvertToDtoResult.getProductId());
        assertEquals("Product Brand", actualConvertToDtoResult.getProductBrand());
        assertEquals("Product Description", actualConvertToDtoResult.getProductDescription());
        assertEquals("Product Name", actualConvertToDtoResult.getProductName());
        assertEquals(10.0d, actualConvertToDtoResult.getAvailableQuantity());
        assertEquals(10.0d, actualConvertToDtoResult.getProductPrice());
        assertEquals(1L, actualConvertToDtoResult.getId().longValue());
        assertTrue(actualConvertToDtoResult.getProductImages().isEmpty());
        assertSame(category, actualConvertToDtoResult.getCategory());
    }

    /**
     * Method under test: {@link ProductServiceImpl#convertToDto(Product)}
     */
    @Test
    void testConvertToDto2() {
        // Arrange
        Category category = new Category();
        category.setId(1L);
        category.setName("Name");
        category.setProducts(new ArrayList<>());
        ProductDto.ProductDtoBuilder productIdResult = ProductDto.builder()
                .availableQuantity(10.0d)
                .category(category)
                .id(1L)
                .productBrand("Product Brand")
                .productDescription("Product Description")
                .productId("42");
        ProductDto buildResult = productIdResult.productImages(new ArrayList<>())
                .productName("Product Name")
                .productPrice(10.0d)
                .build();
        when(productMapper.toProductDto(Mockito.<Product>any())).thenReturn(buildResult);
        when(imageRepository.findByProductId(Mockito.<Long>any())).thenThrow(new ResourceNotFoundException("foo"));

        Category category2 = new Category();
        category2.setId(1L);
        category2.setName("Name");
        category2.setProducts(new ArrayList<>());

        Product product = new Product();
        product.setAvailableQuantity(10.0d);
        product.setCategory(category2);
        product.setId(1L);
        product.setProductBrand("Product Brand");
        product.setProductDescription("Product Description");
        product.setProductId("42");
        product.setProductImages(new ArrayList<>());
        product.setProductName("Product Name");
        product.setProductPrice(10.0d);

        // Act and Assert
        assertThrows(ResourceNotFoundException.class, () -> productServiceImpl.convertToDto(product));
        verify(productMapper).toProductDto(isA(Product.class));
        verify(imageRepository).findByProductId(eq(1L));
    }

    /**
     * Method under test: {@link ProductServiceImpl#convertToDto(Product)}
     */
    @Test
    void testConvertToDto3() {
        // Arrange
        Category category = new Category();
        category.setId(1L);
        category.setName("Name");
        category.setProducts(new ArrayList<>());
        ProductDto.ProductDtoBuilder productIdResult = ProductDto.builder()
                .availableQuantity(10.0d)
                .category(category)
                .id(1L)
                .productBrand("Product Brand")
                .productDescription("Product Description")
                .productId("42");
        ProductDto buildResult = productIdResult.productImages(new ArrayList<>())
                .productName("Product Name")
                .productPrice(10.0d)
                .build();
        when(productMapper.toProductDto(Mockito.<Product>any())).thenReturn(buildResult);

        Category category2 = new Category();
        category2.setId(1L);
        category2.setName("Name");
        category2.setProducts(new ArrayList<>());

        Product product = new Product();
        product.setAvailableQuantity(10.0d);
        product.setCategory(category2);
        product.setId(1L);
        product.setProductBrand("Product Brand");
        product.setProductDescription("Product Description");
        product.setProductId("42");
        product.setProductImages(new ArrayList<>());
        product.setProductName("Product Name");
        product.setProductPrice(10.0d);

        Image image = new Image();
        image.setDownloadUrl("https://example.org/example");
        image.setFileName("foo.txt");
        image.setFileType("File Type");
        image.setId(1L);
        image.setImage(mock(Blob.class));
        image.setProduct(product);

        ArrayList<Image> imageList = new ArrayList<>();
        imageList.add(image);
        when(imageRepository.findByProductId(Mockito.<Long>any())).thenReturn(imageList);
        when(imageMapper.toImageDto(Mockito.<Image>any())).thenThrow(new ResourceNotFoundException("foo"));

        Category category3 = new Category();
        category3.setId(1L);
        category3.setName("Name");
        category3.setProducts(new ArrayList<>());

        Product product2 = new Product();
        product2.setAvailableQuantity(10.0d);
        product2.setCategory(category3);
        product2.setId(1L);
        product2.setProductBrand("Product Brand");
        product2.setProductDescription("Product Description");
        product2.setProductId("42");
        product2.setProductImages(new ArrayList<>());
        product2.setProductName("Product Name");
        product2.setProductPrice(10.0d);

        // Act and Assert
        assertThrows(ResourceNotFoundException.class, () -> productServiceImpl.convertToDto(product2));
        verify(imageMapper).toImageDto(isA(Image.class));
        verify(productMapper).toProductDto(isA(Product.class));
        verify(imageRepository).findByProductId(eq(1L));
    }

    /**
     * Method under test: {@link ProductServiceImpl#buyProducts(List)}
     */
    @Test
    void testBuyProducts() {
        // Arrange
        when(productRepository.findAllByIdInOrderById(Mockito.<List<Long>>any())).thenReturn(new ArrayList<>());

        // Act
        List<BuyProductResponse> actualBuyProductsResult = productServiceImpl.buyProducts(new ArrayList<>());

        // Assert
        verify(productRepository).findAllByIdInOrderById(isA(List.class));
        assertTrue(actualBuyProductsResult.isEmpty());
    }

    /**
     * Method under test: {@link ProductServiceImpl#buyProducts(List)}
     */
    @Test
    void testBuyProducts2() {
        // Arrange
        Category category = new Category();
        category.setId(1L);
        category.setName("Name");
        category.setProducts(new ArrayList<>());

        Product product = new Product();
        product.setAvailableQuantity(10.0d);
        product.setCategory(category);
        product.setId(1L);
        product.setProductBrand("Product Brand");
        product.setProductDescription("Product Description");
        product.setProductId("42");
        product.setProductImages(new ArrayList<>());
        product.setProductName("Product Name");
        product.setProductPrice(10.0d);

        ArrayList<Product> productList = new ArrayList<>();
        productList.add(product);
        when(productRepository.findAllByIdInOrderById(Mockito.<List<Long>>any())).thenReturn(productList);

        // Act and Assert
        assertThrows(ResourceNotFoundException.class, () -> productServiceImpl.buyProducts(new ArrayList<>()));
        verify(productRepository).findAllByIdInOrderById(isA(List.class));
    }

    /**
     * Method under test: {@link ProductServiceImpl#buyProducts(List)}
     */
    @Test
    void testBuyProducts3() {
        // Arrange
        when(productRepository.findAllByIdInOrderById(Mockito.<List<Long>>any())).thenReturn(new ArrayList<>());

        ArrayList<BuyProductRequest> request = new ArrayList<>();
        BuyProductRequest buildResult = BuyProductRequest.builder().availableQuantity(10.0d).id(1L).build();
        request.add(buildResult);

        // Act and Assert
        assertThrows(ResourceNotFoundException.class, () -> productServiceImpl.buyProducts(request));
        verify(productRepository).findAllByIdInOrderById(isA(List.class));
    }

    /**
     * Method under test: {@link ProductServiceImpl#buyProducts(List)}
     */
    @Test
    void testBuyProducts4() {
        // Arrange
        when(productRepository.findAllByIdInOrderById(Mockito.<List<Long>>any())).thenReturn(new ArrayList<>());

        ArrayList<BuyProductRequest> request = new ArrayList<>();
        BuyProductRequest buildResult = BuyProductRequest.builder().availableQuantity(10.0d).id(1L).build();
        request.add(buildResult);
        BuyProductRequest buildResult2 = BuyProductRequest.builder().availableQuantity(10.0d).id(1L).build();
        request.add(buildResult2);

        // Act and Assert
        assertThrows(ResourceNotFoundException.class, () -> productServiceImpl.buyProducts(request));
        verify(productRepository).findAllByIdInOrderById(isA(List.class));
    }

    /**
     * Method under test: {@link ProductServiceImpl#buyProducts(List)}
     */
    @Test
    void testBuyProducts5() {
        // Arrange
        when(productRepository.findAllByIdInOrderById(Mockito.<List<Long>>any()))
                .thenThrow(new ResourceNotFoundException("foo"));

        // Act and Assert
        assertThrows(ResourceNotFoundException.class, () -> productServiceImpl.buyProducts(new ArrayList<>()));
        verify(productRepository).findAllByIdInOrderById(isA(List.class));
    }

    /**
     * Method under test: {@link ProductServiceImpl#buyProducts(List)}
     */
    @Test
    void testBuyProducts6() {
        // Arrange
        Category category = new Category();
        category.setId(1L);
        category.setName("Name");
        category.setProducts(new ArrayList<>());

        Product product = new Product();
        product.setAvailableQuantity(10.0d);
        product.setCategory(category);
        product.setId(1L);
        product.setProductBrand("Product Brand");
        product.setProductDescription("Product Description");
        product.setProductId("42");
        product.setProductImages(new ArrayList<>());
        product.setProductName("Product Name");
        product.setProductPrice(10.0d);

        ArrayList<Product> productList = new ArrayList<>();
        productList.add(product);

        Category category2 = new Category();
        category2.setId(1L);
        category2.setName("Name");
        category2.setProducts(new ArrayList<>());

        Product product2 = new Product();
        product2.setAvailableQuantity(10.0d);
        product2.setCategory(category2);
        product2.setId(1L);
        product2.setProductBrand("Product Brand");
        product2.setProductDescription("Product Description");
        product2.setProductId("42");
        product2.setProductImages(new ArrayList<>());
        product2.setProductName("Product Name");
        product2.setProductPrice(10.0d);
        when(productRepository.save(Mockito.<Product>any())).thenReturn(product2);
        when(productRepository.findAllByIdInOrderById(Mockito.<List<Long>>any())).thenReturn(productList);
        BuyProductResponse buildResult = BuyProductResponse.builder()
                .availableQuantity(10.0d)
                .id(1L)
                .productBrand("Product Brand")
                .productDescription("Product Description")
                .productName("Product Name")
                .productPrice(10.0d)
                .build();
        when(productMapper.toBuyProductResponse(Mockito.<Product>any(), anyDouble())).thenReturn(buildResult);

        ArrayList<BuyProductRequest> request = new ArrayList<>();
        BuyProductRequest buildResult2 = BuyProductRequest.builder().availableQuantity(10.0d).id(1L).build();
        request.add(buildResult2);

        // Act
        List<BuyProductResponse> actualBuyProductsResult = productServiceImpl.buyProducts(request);

        // Assert
        verify(productMapper).toBuyProductResponse(isA(Product.class), eq(10.0d));
        verify(productRepository).findAllByIdInOrderById(isA(List.class));
        verify(productRepository).save(isA(Product.class));
        assertEquals(1, actualBuyProductsResult.size());
        BuyProductResponse getResult = actualBuyProductsResult.get(0);
        assertEquals("Product Brand", getResult.getProductBrand());
        assertEquals("Product Description", getResult.getProductDescription());
        assertEquals("Product Name", getResult.getProductName());
        assertEquals(10.0d, getResult.getAvailableQuantity());
        assertEquals(10.0d, getResult.getProductPrice());
        assertEquals(1L, getResult.getId().longValue());
    }

    /**
     * Method under test: {@link ProductServiceImpl#buyProducts(List)}
     */
    @Test
    void testBuyProducts7() {
        // Arrange
        Category category = new Category();
        category.setId(1L);
        category.setName("Name");
        category.setProducts(new ArrayList<>());

        Product product = new Product();
        product.setAvailableQuantity(10.0d);
        product.setCategory(category);
        product.setId(1L);
        product.setProductBrand("Product Brand");
        product.setProductDescription("Product Description");
        product.setProductId("42");
        product.setProductImages(new ArrayList<>());
        product.setProductName("Product Name");
        product.setProductPrice(10.0d);

        ArrayList<Product> productList = new ArrayList<>();
        productList.add(product);

        Category category2 = new Category();
        category2.setId(1L);
        category2.setName("Name");
        category2.setProducts(new ArrayList<>());

        Product product2 = new Product();
        product2.setAvailableQuantity(10.0d);
        product2.setCategory(category2);
        product2.setId(1L);
        product2.setProductBrand("Product Brand");
        product2.setProductDescription("Product Description");
        product2.setProductId("42");
        product2.setProductImages(new ArrayList<>());
        product2.setProductName("Product Name");
        product2.setProductPrice(10.0d);
        when(productRepository.save(Mockito.<Product>any())).thenReturn(product2);
        when(productRepository.findAllByIdInOrderById(Mockito.<List<Long>>any())).thenReturn(productList);
        when(productMapper.toBuyProductResponse(Mockito.<Product>any(), anyDouble()))
                .thenThrow(new ResourceNotFoundException("foo"));

        ArrayList<BuyProductRequest> request = new ArrayList<>();
        BuyProductRequest buildResult = BuyProductRequest.builder().availableQuantity(10.0d).id(1L).build();
        request.add(buildResult);

        // Act and Assert
        assertThrows(ResourceNotFoundException.class, () -> productServiceImpl.buyProducts(request));
        verify(productMapper).toBuyProductResponse(isA(Product.class), eq(10.0d));
        verify(productRepository).findAllByIdInOrderById(isA(List.class));
        verify(productRepository).save(isA(Product.class));
    }

    /**
     * Method under test: {@link ProductServiceImpl#buyProducts(List)}
     */
    @Test
    void testBuyProducts8() {
        // Arrange
        Category category = new Category();
        category.setId(1L);
        category.setName("Name");
        category.setProducts(new ArrayList<>());
        Product product = mock(Product.class);
        when(product.getAvailableQuantity()).thenReturn(10.0d);
        doNothing().when(product).setAvailableQuantity(anyDouble());
        doNothing().when(product).setCategory(Mockito.<Category>any());
        doNothing().when(product).setId(Mockito.<Long>any());
        doNothing().when(product).setProductBrand(Mockito.<String>any());
        doNothing().when(product).setProductDescription(Mockito.<String>any());
        doNothing().when(product).setProductId(Mockito.<String>any());
        doNothing().when(product).setProductImages(Mockito.<List<Image>>any());
        doNothing().when(product).setProductName(Mockito.<String>any());
        doNothing().when(product).setProductPrice(anyDouble());
        product.setAvailableQuantity(10.0d);
        product.setCategory(category);
        product.setId(1L);
        product.setProductBrand("Product Brand");
        product.setProductDescription("Product Description");
        product.setProductId("42");
        product.setProductImages(new ArrayList<>());
        product.setProductName("Product Name");
        product.setProductPrice(10.0d);

        ArrayList<Product> productList = new ArrayList<>();
        productList.add(product);

        Category category2 = new Category();
        category2.setId(1L);
        category2.setName("Name");
        category2.setProducts(new ArrayList<>());

        Product product2 = new Product();
        product2.setAvailableQuantity(10.0d);
        product2.setCategory(category2);
        product2.setId(1L);
        product2.setProductBrand("Product Brand");
        product2.setProductDescription("Product Description");
        product2.setProductId("42");
        product2.setProductImages(new ArrayList<>());
        product2.setProductName("Product Name");
        product2.setProductPrice(10.0d);
        when(productRepository.save(Mockito.<Product>any())).thenReturn(product2);
        when(productRepository.findAllByIdInOrderById(Mockito.<List<Long>>any())).thenReturn(productList);
        BuyProductResponse buildResult = BuyProductResponse.builder()
                .availableQuantity(10.0d)
                .id(1L)
                .productBrand("Product Brand")
                .productDescription("Product Description")
                .productName("Product Name")
                .productPrice(10.0d)
                .build();
        when(productMapper.toBuyProductResponse(Mockito.<Product>any(), anyDouble())).thenReturn(buildResult);

        ArrayList<BuyProductRequest> request = new ArrayList<>();
        BuyProductRequest buildResult2 = BuyProductRequest.builder().availableQuantity(10.0d).id(1L).build();
        request.add(buildResult2);

        // Act
        List<BuyProductResponse> actualBuyProductsResult = productServiceImpl.buyProducts(request);

        // Assert
        verify(product, atLeast(1)).getAvailableQuantity();
        verify(product, atLeast(1)).setAvailableQuantity(anyDouble());
        verify(product).setCategory(isA(Category.class));
        verify(product).setId(eq(1L));
        verify(product).setProductBrand(eq("Product Brand"));
        verify(product).setProductDescription(eq("Product Description"));
        verify(product).setProductId(eq("42"));
        verify(product).setProductImages(isA(List.class));
        verify(product).setProductName(eq("Product Name"));
        verify(product).setProductPrice(eq(10.0d));
        verify(productMapper).toBuyProductResponse(isA(Product.class), eq(10.0d));
        verify(productRepository).findAllByIdInOrderById(isA(List.class));
        verify(productRepository).save(isA(Product.class));
        assertEquals(1, actualBuyProductsResult.size());
        BuyProductResponse getResult = actualBuyProductsResult.get(0);
        assertEquals("Product Brand", getResult.getProductBrand());
        assertEquals("Product Description", getResult.getProductDescription());
        assertEquals("Product Name", getResult.getProductName());
        assertEquals(10.0d, getResult.getAvailableQuantity());
        assertEquals(10.0d, getResult.getProductPrice());
        assertEquals(1L, getResult.getId().longValue());
    }

    /**
     * Method under test: {@link ProductServiceImpl#buyProducts(List)}
     */
    @Test
    void testBuyProducts9() {
        // Arrange
        Category category = new Category();
        category.setId(1L);
        category.setName("Name");
        category.setProducts(new ArrayList<>());
        Product product = mock(Product.class);
        when(product.getAvailableQuantity()).thenReturn(0.5d);
        doNothing().when(product).setAvailableQuantity(anyDouble());
        doNothing().when(product).setCategory(Mockito.<Category>any());
        doNothing().when(product).setId(Mockito.<Long>any());
        doNothing().when(product).setProductBrand(Mockito.<String>any());
        doNothing().when(product).setProductDescription(Mockito.<String>any());
        doNothing().when(product).setProductId(Mockito.<String>any());
        doNothing().when(product).setProductImages(Mockito.<List<Image>>any());
        doNothing().when(product).setProductName(Mockito.<String>any());
        doNothing().when(product).setProductPrice(anyDouble());
        product.setAvailableQuantity(10.0d);
        product.setCategory(category);
        product.setId(1L);
        product.setProductBrand("Product Brand");
        product.setProductDescription("Product Description");
        product.setProductId("42");
        product.setProductImages(new ArrayList<>());
        product.setProductName("Product Name");
        product.setProductPrice(10.0d);

        ArrayList<Product> productList = new ArrayList<>();
        productList.add(product);
        when(productRepository.findAllByIdInOrderById(Mockito.<List<Long>>any())).thenReturn(productList);

        ArrayList<BuyProductRequest> request = new ArrayList<>();
        BuyProductRequest buildResult = BuyProductRequest.builder().availableQuantity(10.0d).id(1L).build();
        request.add(buildResult);

        // Act and Assert
        assertThrows(ResourceNotFoundException.class, () -> productServiceImpl.buyProducts(request));
        verify(product).getAvailableQuantity();
        verify(product).setAvailableQuantity(eq(10.0d));
        verify(product).setCategory(isA(Category.class));
        verify(product).setId(eq(1L));
        verify(product).setProductBrand(eq("Product Brand"));
        verify(product).setProductDescription(eq("Product Description"));
        verify(product).setProductId(eq("42"));
        verify(product).setProductImages(isA(List.class));
        verify(product).setProductName(eq("Product Name"));
        verify(product).setProductPrice(eq(10.0d));
        verify(productRepository).findAllByIdInOrderById(isA(List.class));
    }

    /**
     * Method under test: {@link ProductServiceImpl#findAllByIdInOrderById(List)}
     */
    @Test
    void testFindAllByIdInOrderById() {
        // Arrange
        ArrayList<Product> productList = new ArrayList<>();
        when(productRepository.findAllByIdInOrderById(Mockito.<List<Long>>any())).thenReturn(productList);

        // Act
        List<Product> actualFindAllByIdInOrderByIdResult = productServiceImpl.findAllByIdInOrderById(new ArrayList<>());

        // Assert
        verify(productRepository).findAllByIdInOrderById(isA(List.class));
        assertTrue(actualFindAllByIdInOrderByIdResult.isEmpty());
        assertSame(productList, actualFindAllByIdInOrderByIdResult);
    }

    /**
     * Method under test: {@link ProductServiceImpl#findAllByIdInOrderById(List)}
     */
    @Test
    void testFindAllByIdInOrderById2() {
        // Arrange
        ArrayList<Product> productList = new ArrayList<>();
        when(productRepository.findAllByIdInOrderById(Mockito.<List<Long>>any())).thenReturn(productList);

        ArrayList<Long> ids = new ArrayList<>();
        ids.add(1L);

        // Act
        List<Product> actualFindAllByIdInOrderByIdResult = productServiceImpl.findAllByIdInOrderById(ids);

        // Assert
        verify(productRepository).findAllByIdInOrderById(isA(List.class));
        assertTrue(actualFindAllByIdInOrderByIdResult.isEmpty());
        assertSame(productList, actualFindAllByIdInOrderByIdResult);
    }

    /**
     * Method under test: {@link ProductServiceImpl#findAllByIdInOrderById(List)}
     */
    @Test
    void testFindAllByIdInOrderById3() {
        // Arrange
        ArrayList<Product> productList = new ArrayList<>();
        when(productRepository.findAllByIdInOrderById(Mockito.<List<Long>>any())).thenReturn(productList);

        ArrayList<Long> ids = new ArrayList<>();
        ids.add(0L);
        ids.add(1L);

        // Act
        List<Product> actualFindAllByIdInOrderByIdResult = productServiceImpl.findAllByIdInOrderById(ids);

        // Assert
        verify(productRepository).findAllByIdInOrderById(isA(List.class));
        assertTrue(actualFindAllByIdInOrderByIdResult.isEmpty());
        assertSame(productList, actualFindAllByIdInOrderByIdResult);
    }

    /**
     * Method under test: {@link ProductServiceImpl#findAllByIdInOrderById(List)}
     */
    @Test
    void testFindAllByIdInOrderById4() {
        // Arrange
        when(productRepository.findAllByIdInOrderById(Mockito.<List<Long>>any()))
                .thenThrow(new ResourceNotFoundException("foo"));

        // Act and Assert
        assertThrows(ResourceNotFoundException.class, () -> productServiceImpl.findAllByIdInOrderById(new ArrayList<>()));
        verify(productRepository).findAllByIdInOrderById(isA(List.class));
    }

    /**
     * Method under test: {@link ProductServiceImpl#addToInventory(String, double)}
     */
    @Test
    void testAddToInventory() {
        // Arrange
        WebClient.ResponseSpec responseSpec = mock(WebClient.ResponseSpec.class);
        Inventory buildResult = Inventory.builder().availableQuantity(10.0d).id(1L).productId("42").build();
        Mono<Inventory> justResult = Mono.just(buildResult);
        when(responseSpec.bodyToMono(Mockito.<Class<Inventory>>any())).thenReturn(justResult);
        WebClient.RequestHeadersSpec<WebClient.RequestBodySpec> requestHeadersSpec = mock(
                WebClient.RequestHeadersSpec.class);
        when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
        WebClient.RequestBodySpec requestBodySpec = mock(WebClient.RequestBodySpec.class);
        Mockito.<WebClient.RequestHeadersSpec<?>>when(
                        requestBodySpec.body(Mockito.<Publisher<Object>>any(), Mockito.<Class<Object>>any()))
                .thenReturn(requestHeadersSpec);
        WebClient.RequestBodyUriSpec requestBodyUriSpec = mock(WebClient.RequestBodyUriSpec.class);
        when(requestBodyUriSpec.uri(Mockito.<String>any(), isA(Object[].class))).thenReturn(requestBodySpec);
        when(webClient.post()).thenReturn(requestBodyUriSpec);

        // Act
        Inventory actualAddToInventoryResult = productServiceImpl.addToInventory("42", 10.0d);

        // Assert
        verify(webClient).post();
        verify(requestBodySpec).body(isA(Publisher.class), isA(Class.class));
        verify(requestHeadersSpec).retrieve();
        verify(responseSpec).bodyToMono(isA(Class.class));
        verify(requestBodyUriSpec).uri(eq("http://inventory-service/inventory/addInventory/"), isA(Object[].class));
        assertEquals("42", actualAddToInventoryResult.getProductId());
        assertEquals(10.0d, actualAddToInventoryResult.getAvailableQuantity());
        assertEquals(1L, actualAddToInventoryResult.getId().longValue());
    }

    /**
     * Method under test: {@link ProductServiceImpl#addToInventory(String, double)}
     */
    @Test
    void testAddToInventory2() {
        // Arrange
        WebClient.ResponseSpec responseSpec = mock(WebClient.ResponseSpec.class);
        when(responseSpec.bodyToMono(Mockito.<Class<Inventory>>any()))
                .thenThrow(new ResourceNotFoundException("http://inventory-service/inventory/addInventory/"));
        WebClient.RequestHeadersSpec<WebClient.RequestBodySpec> requestHeadersSpec = mock(
                WebClient.RequestHeadersSpec.class);
        when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
        WebClient.RequestBodySpec requestBodySpec = mock(WebClient.RequestBodySpec.class);
        Mockito.<WebClient.RequestHeadersSpec<?>>when(
                        requestBodySpec.body(Mockito.<Publisher<Object>>any(), Mockito.<Class<Object>>any()))
                .thenReturn(requestHeadersSpec);
        WebClient.RequestBodyUriSpec requestBodyUriSpec = mock(WebClient.RequestBodyUriSpec.class);
        when(requestBodyUriSpec.uri(Mockito.<String>any(), isA(Object[].class))).thenReturn(requestBodySpec);
        when(webClient.post()).thenReturn(requestBodyUriSpec);

        // Act and Assert
        assertThrows(ResourceNotFoundException.class, () -> productServiceImpl.addToInventory("42", 10.0d));
        verify(webClient).post();
        verify(requestBodySpec).body(isA(Publisher.class), isA(Class.class));
        verify(requestHeadersSpec).retrieve();
        verify(responseSpec).bodyToMono(isA(Class.class));
        verify(requestBodyUriSpec).uri(eq("http://inventory-service/inventory/addInventory/"), isA(Object[].class));
    }
}
