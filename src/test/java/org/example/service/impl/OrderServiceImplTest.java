package org.example.service.impl;

import org.example.model.Customer;
import org.example.model.Order;
import org.example.repository.impl.OrderRepositoryImpl;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;
import static org.mockito.Mockito.*;

public class OrderServiceImplTest {
    @Test
    void testSave() {
        OrderRepositoryImpl orderRepositoryMock = mock(OrderRepositoryImpl.class);

        OrderServiceImpl orderService = new OrderServiceImpl();
        orderService.orderRepository = orderRepositoryMock;
        Customer customer = new Customer(11, "Ivan", "Ivanov", "ivan@mail.ru");
        Order order = new Order(1, customer, new Date());
        when(orderRepositoryMock.save(order)).thenReturn(order);
        Order savedOrder = orderService.save(order);
        verify(orderRepositoryMock).save(order);
        assertEquals(order, savedOrder);
    }

    @Test
    void testFindById() throws SQLException {
        OrderRepositoryImpl orderRepositoryMock = mock(OrderRepositoryImpl.class);

        OrderServiceImpl orderService = new OrderServiceImpl();
        orderService.orderRepository = orderRepositoryMock;
        Customer customer = new Customer(11, "Ivan", "Ivanov", "ivan@mail.ru");
        Order order = new Order(1, customer, new Date());

        when(orderRepositoryMock.findById(1)).thenReturn(order);
        Order foundOrder = orderService.findById(1);
        verify(orderRepositoryMock).findById(1);
        assertEquals(order, foundOrder);
    }

    @Test
    void testFindAll() {
        OrderRepositoryImpl orderRepositoryMock = mock(OrderRepositoryImpl.class);
        OrderServiceImpl orderService = new OrderServiceImpl();
        orderService.orderRepository = orderRepositoryMock;
        Customer customer = new Customer(11, "Ivan", "Ivanov", "ivan@mail.ru");
        Order order1 = new Order(1, customer, new Date());
        Order order2 = new Order(1, customer, new Date());
        List<Order> orders = Arrays.asList(order1, order2);

        when(orderRepositoryMock.findAll()).thenReturn(orders);
        List<Order> foundOrders = orderService.findAll();
        verify(orderRepositoryMock).findAll();
        assertEquals(orders, foundOrders);
    }

    @Test
    void testDeleteById() {
        OrderRepositoryImpl orderRepositoryMock = mock(OrderRepositoryImpl.class);

        OrderServiceImpl orderService = new OrderServiceImpl();
        orderService.orderRepository = orderRepositoryMock;

        when(orderRepositoryMock.deleteById(1)).thenReturn(true);
        boolean result = orderService.deleteById(1);
        verify(orderRepositoryMock).deleteById(1);
        assertTrue(result);
    }
}
