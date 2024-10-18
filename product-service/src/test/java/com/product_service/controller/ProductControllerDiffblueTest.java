package com.product_service.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.product_service.client.InventoryClient;
import com.product_service.dtos.ProductDto;
import com.product_service.dtos.apiResponse.ApiResponse;
import com.product_service.dtos.request.ProductRequest;
import com.product_service.entity.Category;
import com.product_service.entity.Product;
import com.product_service.productMapper.ImageMapper;
import com.product_service.productMapper.ProductMapper;
import com.product_service.repository.CategoryRepository;
import com.product_service.repository.ImageRepository;
import com.product_service.repository.ProductRepository;
import com.product_service.service.ProductService;
import com.product_service.service.ProductServiceImpl;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.reactive.function.client.WebClient;

@ContextConfiguration(classes = {ProductController.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class ProductControllerDiffblueTest {
    @Autowired
    private ProductController productController;

    @MockBean
    private ProductService productService;

    /**
     * Method under test: {@link ProductController#addProduct(ProductRequest)}
     */
    @Test
    void testAddProduct() throws Exception {
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
        when(productService.convertToDto(Mockito.<Product>any())).thenReturn(buildResult);
        when(productService.addProduct(Mockito.<ProductRequest>any())).thenReturn(product);

        Category category3 = new Category();
        category3.setId(1L);
        category3.setName("Name");
        category3.setProducts(new ArrayList<>());
        ProductRequest buildResult2 = ProductRequest.builder()
                .availableQuantity(10.0d)
                .category(category3)
                .productBrand("Product Brand")
                .productDescription("Product Description")
                .productName("Product Name")
                .productPrice(10.0d)
                .build();
        String content = (new ObjectMapper()).writeValueAsString(buildResult2);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/products/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(productController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"message\":\"Add product success!\",\"data\":{\"id\":1,\"productId\":\"42\",\"productName\":\"Product Name\","
                                        + "\"productBrand\":\"Product Brand\",\"productDescription\":\"Product Description\",\"productImages\":[],\"productPrice"
                                        + "\":10.0,\"category\":{\"id\":1,\"name\":\"Name\"},\"availableQuantity\":10.0}}"));
    }

    /**
     * Method under test:
     * {@link ProductController#countProductsByBrandAndName(String, String)}
     */
    @Test
    void testCountProductsByBrandAndName() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        ProductRepository productRepository = mock(ProductRepository.class);
        when(productRepository.countByProductBrandAndProductName(Mockito.<String>any(), Mockito.<String>any()))
                .thenReturn(3L);
        ProductMapper productMapper = new ProductMapper();
        ImageRepository imageRepository = mock(ImageRepository.class);

        // Act
        ResponseEntity<ApiResponse> actualCountProductsByBrandAndNameResult = (new ProductController(
                new ProductServiceImpl(productRepository, productMapper, imageRepository, new ImageMapper(),
                        mock(CategoryRepository.class), mock(InventoryClient.class), mock(WebClient.class))))
                .countProductsByBrandAndName("Brand", "Name");

        // Assert
        verify(productRepository).countByProductBrandAndProductName(eq("Brand"), eq("Name"));
        HttpStatusCode statusCode = actualCountProductsByBrandAndNameResult.getStatusCode();
        assertTrue(statusCode instanceof HttpStatus);
        assertEquals("Product count!", actualCountProductsByBrandAndNameResult.getBody().getMessage());
        assertEquals(200, actualCountProductsByBrandAndNameResult.getStatusCodeValue());
        assertEquals(HttpStatus.OK, statusCode);
        assertTrue(actualCountProductsByBrandAndNameResult.hasBody());
        assertTrue(actualCountProductsByBrandAndNameResult.getHeaders().isEmpty());
    }

    /**
     * Method under test:
     * {@link ProductController#countProductsByBrandAndName(String, String)}
     */
    @Test
    void testCountProductsByBrandAndName2() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange and Act
        ResponseEntity<ApiResponse> actualCountProductsByBrandAndNameResult = (new ProductController(null))
                .countProductsByBrandAndName("Brand", "Name");

        // Assert
        HttpStatusCode statusCode = actualCountProductsByBrandAndNameResult.getStatusCode();
        assertTrue(statusCode instanceof HttpStatus);
        ApiResponse body = actualCountProductsByBrandAndNameResult.getBody();
        assertEquals(
                "Cannot invoke \"com.product_service.service.ProductService.countProductsByBrandAndName(String, String)\""
                        + " because \"this.productService\" is null",
                body.getMessage());
        assertNull(body.getData());
        assertEquals(500, actualCountProductsByBrandAndNameResult.getStatusCodeValue());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, statusCode);
        assertTrue(actualCountProductsByBrandAndNameResult.hasBody());
        assertTrue(actualCountProductsByBrandAndNameResult.getHeaders().isEmpty());
    }
}
