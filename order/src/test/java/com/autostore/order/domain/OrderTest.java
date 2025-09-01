package com.autostore.order.domain;


import com.autostore.order.domain.exception.BusinessException;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class OrderTest {

    @Test
    void testShouldInstantiateOrder() {
        // Arrange
        Product product = new Product("Product 1", 100.0);
        List<OrderProducts> orderProducts = List.of(new OrderProducts(product, 2));
        OrderCustomer customer = OrderCustomer.of("customer123");

        // Act
        Order order = Order.of(
                "order123",
                orderProducts,
                LocalDateTime.now(),
                "txn123",
                200.0,
                2, customer
        );

        // Assert
        assertNotNull(order);
        assertEquals("order123", order.getId());
        assertEquals(200.0, order.getTotalAmount());
        assertEquals(2, order.getTotalItems());
        assertEquals("txn123", order.getTransactionId());
        assertEquals(1, order.getProducts().size());
        assertEquals("Product 1", order.getProducts().get(0).product().code());
        assertEquals(2, order.getProducts().get(0).quantity());
    }

    @Test
    void testShouldCreateOrder() {
        // Arrange
        Product product = new Product("Product 1", 100.0);
        List<OrderProducts> orderProducts = List.of(new OrderProducts(product, 2));
        String transactionId = "txn123";
        OrderCustomer customer = OrderCustomer.of("customer123");
        var expectedTotalAmount = 200.0;
        var expectedTotalItems = 2;

        // Act
        Order order = Order.create(orderProducts, transactionId, customer);

        // Assert
        assertNotNull(order);
        assertNull(order.getId());
        assertEquals(expectedTotalAmount, order.getTotalAmount());
        assertEquals(expectedTotalItems, order.getTotalItems());
        assertEquals(transactionId, order.getTransactionId());
        assertEquals(1, order.getProducts().size());
        assertEquals("Product 1", order.getProducts().get(0).product().code());
        assertEquals(2, order.getProducts().get(0).quantity());
    }

    @Test
    void testShouldThrowExceptionWhenCreateOrderWithInvalidAmount() {
        // Arrange
        Product product = new Product("Product 1", 0.0);
        List<OrderProducts> orderProducts = List.of(new OrderProducts(product, 2));
        String transactionId = "txn123";
        OrderCustomer customer = OrderCustomer.of("customer123");

        // Act & Assert
        assertThrows(BusinessException.class, () -> Order.create(orderProducts, transactionId, customer));
    }

    @Test
    void testShouldThrowExceptionWhenCreateEmptyOrder() {
        // Arrange
        List<OrderProducts> orderProducts = List.of();
        String transactionId = "txn123";
        OrderCustomer customer = OrderCustomer.of("customer123");

        // Act & Assert
        assertThrows(BusinessException.class, () -> Order.create(orderProducts, transactionId, customer));
    }

    @Test
    void testShouldThrowExceptionWhenCreateOrderWithNoCustomer() {
        // Arrange
        Product product = new Product("Product 1", 100.0);
        List<OrderProducts> orderProducts = List.of(new OrderProducts(product, 2));
        String transactionId = "txn123";

        // Act & Assert
        assertThrows(BusinessException.class, () -> Order.create(orderProducts, transactionId, OrderCustomer.of(null)));
        assertThrows(BusinessException.class, () -> Order.create(orderProducts, transactionId, OrderCustomer.of("")));
    }

}
