package com.order_service.service;

import com.order_service.client.CartClient;
import com.order_service.client.InventoryClient;
import com.order_service.client.PaymentClient;
import com.order_service.client.ProductClient;
import com.order_service.dtos.*;
import com.order_service.dtos.apiResponse.ApiResponse;
import com.order_service.entity.Order;
import com.order_service.entity.OrderItem;
import com.order_service.enums.OrderStatus;
import com.order_service.exception.ResourceNotFoundException;
import com.order_service.kafka.OrderProducer;
import com.order_service.mapper.OrderMapper;
import com.order_service.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final InventoryClient inventoryClient;
    private final ProductClient productClient;
    private final OrderRepository orderRepository;
    private final CartClient cartClient;
    private final OrderMapper orderMapper;
    private final PaymentClient paymentClient;
    private final OrderProducer orderProducer;


    @Transactional
    @Override
    public OrderResponse placeOrder1(String productId, int quantity) {
//
//        boolean isAvailable = inventoryClient.isProductAvailable(productId, quantity);
//        if (!isAvailable) {
//            throw new InsufficientStockException("Not enough stock for product ID: " + productId);
//        }
//
//        ProductResponse product = productClient.getProductById(productId);
//
//        StockUpdateRequest stockUpdateRequest = new StockUpdateRequest(productId, quantity);
//
//        inventoryClient.reduceStock(stockUpdateRequest);
//
//        Order newOrder = new Order();
////        newOrder.setProductId(productId);
////        newOrder.setProductName(product.getName());
////        newOrder.setProductPrice(product.getPrice());
////        newOrder.setQuantity(quantity);
//        orderRepository.save(newOrder);
//
//        return new OrderResponse(newOrder.getId(), "Order placed successfully!");
        return null;
    }

    @Transactional
    @Override
    public Order placeOrder(Long userId) {

//        Cart cart   = cartService.getCartByUserId(userId);
        ResponseEntity<ApiResponse<Cart>> res = cartClient.getCartForOrder(userId);

        Cart cart = Objects.requireNonNull(res.getBody()).getData();

        Order order = createOrder(cart);
        List<OrderItem> orderItemList = createOrderItems1(order, cart);
        order.setOrderItems(new HashSet<>(orderItemList));
        order.setTotalAmount(calculateTotalAmount(orderItemList));
        Order savedOrder = orderRepository.save(order);


//        cartClient.clearCart(cart.getCartId());

        var paymentRequest = PaymentRequest.builder()
                .paymentMethod(savedOrder.getPaymentMethod())
                .orderReference(savedOrder.getReference())
                .amount(savedOrder.getTotalAmount())
                .orderId(savedOrder.getId())
                .user(Customer.builder()
                        .id(userId)
                        .email("abcd@gmail.com")
                        .firstname("Mukesh")
                        .lastname("Ambani")
                        .build())
                .build();

//        var paymentRequest1 = new PaymentRequest(
//                savedOrder.getTotalAmount(),
//                savedOrder.getPaymentMethod(),
//                savedOrder.getId(),
//                savedOrder.getReference(),
//                Customer.builder()
//                        .id(userId).build()
//        );

        paymentClient.requestOrderPayment(paymentRequest);

        List<Long> productIdsList = new ArrayList<>();
        cart.getItems()
                .forEach(e ->
                        productIdsList.add(e.getProductId()));
      ResponseEntity<ApiResponse<List<ProductsConfirmed>>> response =  productClient.getAllProductsByProductIdsForConfirm(productIdsList);
      List<ProductsConfirmed> productsConfirmed = Objects.requireNonNull(response.getBody()).getData();
        orderProducer.sendOrderConfirmation(
                new OrderConfirmation(
                        savedOrder.getReference(),
                        savedOrder.getTotalAmount(),
                        savedOrder.getPaymentMethod(),
                        Customer.builder()
                                .id(userId)
                                .email("abcd@gmail.com")
                                .firstname("Mukesh")
                                .lastname("Ambani")
                                .build(),
                        productsConfirmed
                )
        );

        return savedOrder;
    }

    private Order createOrder(Cart cart) {
        Order order = new Order();
//        order.setUser(cart.getUser());
        order.setOrderStatus(OrderStatus.PENDING);
        order.setReference("ORDER-" + UUID.randomUUID().toString() + " " + System.currentTimeMillis());
//        order.setCreatedDate(LocalDateTime.from(LocalDate.now()));
        return  order;
    }

    private List<OrderItem> createOrderItems(Order order, Cart cart) {

        // yet to implement -
        // first call inventory to check inStock
        // then if inStock go reduceStock
        // else Product not in stock

        List<StockUpdateRequest> stockUpdateRequests = new ArrayList<>();

        return  cart.getItems().stream().map(cartItem -> {
            var productId = cartItem.getProductId();

            stockUpdateRequests.add(new StockUpdateRequest(productId, cartItem.getQuantity()));

            StockUpdateRequest stockUpdateRequest = new StockUpdateRequest(productId, cartItem.getQuantity());

            inventoryClient.reduceStock(stockUpdateRequest);

//            product.setInventory(product.getInventory() - cartItem.getQuantity());
            // unnecessary call coz we can return cartItem.getProductId()
            ProductResponse product = productClient.getProductById(productId);

            return  new OrderItem(
                    order,
                    product.getProductId(),
                    cartItem.getQuantity(),
                    cartItem.getUnitPrice());
        }).toList();

    }


    private List<OrderItem> createOrderItems1(Order order, Cart cart) {

        // yet to implement -
        // first call inventory to check inStock
        // then if inStock go reduceStock
        // else Product not in stock

        List<StockUpdateRequest> stockUpdateRequests = new ArrayList<>();

        cart.getItems()
                .forEach(e ->
                        stockUpdateRequests.add(new StockUpdateRequest(e.getProductId(), e.getQuantity())));

//        List<StockUpdateResponse> responses =  inventoryClient.checkAndUpdateStock(stockUpdateRequests);

                inventoryClient.checkAndUpdateStock(stockUpdateRequests);


        return  cart.getItems().stream().map(cartItem -> new OrderItem(
                order,
                cartItem.getProductId(),
                cartItem.getQuantity(),
                cartItem.getUnitPrice())).toList();

    }




    private BigDecimal calculateTotalAmount(List<OrderItem> orderItemList) {
        return  orderItemList
                .stream()
                .map(item -> item.getPrice()
                        .multiply(BigDecimal.valueOf(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }


    @Override
    public OrderDto getOrder(Long orderId) {
        return orderRepository.findById(orderId)
                .map(this :: convertToDto)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found"));
    }
    private OrderDto convertToDto(Order order) {
        return orderMapper.toOrderDto(order);
    }
}
