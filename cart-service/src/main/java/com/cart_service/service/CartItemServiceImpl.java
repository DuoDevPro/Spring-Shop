package com.cart_service.service;

import com.cart_service.client.ProductClient;
import com.cart_service.dtos.ProductResponse;
import com.cart_service.dtos.apiResponse.ApiResponse;
import com.cart_service.entity.Cart;
import com.cart_service.entity.CartItem;
import com.cart_service.exception.ResourceNotFoundException;
import com.cart_service.repository.CartItemRepository;
import com.cart_service.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CartItemServiceImpl implements CartItemService {
    private final CartItemRepository cartItemRepository;
    private final CartRepository cartRepository;
    private final CartService cartService;
    private final ProductClient productClient;

    
    @Override
    @Transactional
    public void addItemToCart(Long cartId, Long productId, double quantity) {

        Cart cart = cartService.getCart(cartId);
        ResponseEntity<ApiResponse<ProductResponse>> response = productClient.getProductById(productId);
        ProductResponse product = new ProductResponse();
        if(response != null) {
            product = (ProductResponse) Objects.requireNonNull(response.getBody()).getData();
        }
        CartItem cartItem = cart.getItems()
                .stream()
                .filter(item -> item.getProductId().equals(productId))
                .findFirst().orElse(new CartItem());

        if (cartItem.getId() == null) {
            cartItem.setCart(cart);
            cartItem.setProductId(product.getProductId());
            cartItem.setQuantity(quantity);
            cartItem.setUnitPrice(product.getPrice());
        }
        else {
            cartItem.setQuantity(cartItem.getQuantity() + quantity);
        }
        cartItem.setTotalPrice();
        cart.addItem(cartItem);
        cartItemRepository.save(cartItem);
        cartRepository.save(cart);
    }

    @Override
    public void removeItemFromCart(Long cartId, Long productId) {

        Cart cart = cartService.getCart(cartId);
        CartItem itemToRemove = getCartItem(cartId, productId);
        cart.removeItem(itemToRemove);
        cartRepository.save(cart);
    }

    @Override
    public void updateItemQuantity(Long cartId, Long productId, double quantity) {

        Cart cart = cartService.getCart(cartId);
//        ProductResponse product = productClient.getProductById(productId);
        ResponseEntity<ApiResponse<ProductResponse>> response = productClient.getProductById(productId);
        ProductResponse product = new ProductResponse();
        product = (ProductResponse)(Objects.requireNonNull(response.getBody())).getData();

        ProductResponse finalProduct = product;
        cart.getItems()
                .stream()
                .filter(item -> item.getProductId().equals(productId))
                .findFirst()
                .ifPresent(item -> {
                    item.setQuantity(quantity);
                    item.setUnitPrice(finalProduct.getPrice());
                    item.setTotalPrice();
                });
        BigDecimal totalAmount = cart.getItems()
                .stream().map(CartItem ::getTotalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        cart.setTotalAmount(totalAmount);
        cartRepository.save(cart);

    }

    @Override
    public CartItem getCartItem(Long cartId, Long productId) {
        Cart cart = cartService.getCart(cartId);
        return  cart.getItems()
                .stream()
                .filter(item -> item.getProductId().equals(productId))
                .findFirst().orElseThrow(() -> new ResourceNotFoundException("Item not found"));
    }

}
