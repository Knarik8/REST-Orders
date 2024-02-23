package org.example.repository.impl;

import org.example.config.ContainersEnvironment;
import org.example.model.Customer;
import org.example.model.Item;
import org.example.model.Order;
import org.example.repository.OrderRepository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class OrderRepositoryTest extends ContainersEnvironment {
    private static Connection connection;
    private static OrderRepository orderRepository;


    @BeforeAll
    static void beforeAll() throws SQLException {
        mySQLContainer.start();
        String jdbcUrl = mySQLContainer.getJdbcUrl();
        String username = mySQLContainer.getUsername();
        String password = mySQLContainer.getPassword();
        connection = DriverManager.getConnection(jdbcUrl, username, password);
        orderRepository = new OrderRepositoryImpl(connection);

    }


    @AfterAll
    static void afterAll() {
        mySQLContainer.stop();
    }


    @Test
    void getAllTest() {
        List<Order> orderList = orderRepository.findAll();
        assertEquals(4, orderList.size());

    }

    @Test
    void saveTest() throws SQLException {
        Customer customer = new Customer(7, "Petr", "Petrov", "petr@mail.ru");
        Order order = new Order();
        order.setId(8);
        order.setCustomer(customer);
        order.setDate(new Date());
        orderRepository.save(order);
        assertNotNull(orderRepository.findById(order.getId()));
    }

    @Test
    void findByIdIfExistTest() throws SQLException {
        int existedId = 3;
        Order actualOrder = orderRepository.findById(existedId);

        assertNotNull(actualOrder);
        assertEquals(existedId, actualOrder.getId());

    }

    @Test
    void findByIdIfNotExistTest() throws SQLException {
        int existedId = 38;
        Order actualOrder = orderRepository.findById(existedId);

        assertNull(actualOrder);

    }


    @Test
    void deleteByIdTest() throws SQLException {
        int existedId = 2;
        Order actualOrder = orderRepository.findById(existedId);
        assertNotNull(actualOrder);
        orderRepository.deleteById(actualOrder.getId());
        assertNull(orderRepository.findById(actualOrder.getId()));
    }

    @Test
    void hashCodeTest() throws SQLException {
        Order order1 = orderRepository.findById(3);
        Order order2 = orderRepository.findById(3);
        assertEquals(order1.hashCode(), order2.hashCode());
    }
}
