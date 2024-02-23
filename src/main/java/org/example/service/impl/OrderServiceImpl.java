package org.example.service.impl;

import org.example.model.Order;
import org.example.repository.impl.OrderRepositoryImpl;
import org.example.service.Service;

import java.sql.SQLException;
import java.util.List;

public class OrderServiceImpl implements Service<Order> {

    private static OrderServiceImpl instance;

    public static OrderServiceImpl getInstance() {
        if (instance == null) {
            instance = new OrderServiceImpl();
        }
        return instance;
    }

    OrderServiceImpl() {
        this.orderRepository = OrderRepositoryImpl.getInstance();
    }

    OrderRepositoryImpl orderRepository;

    @Override
    public Order save(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Order findById(Integer id) throws SQLException {
        return orderRepository.findById(id);
    }

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public boolean deleteById(Integer id) {
        return orderRepository.deleteById(id);
    }
}
