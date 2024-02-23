package org.example.repository.impl;

import org.example.db.ConnectionManager;
import org.example.model.Order;
import org.example.repository.OrderRepository;
import org.example.repository.mapper.ResultSetMapper;
import org.example.repository.mapper.ResultSetMapperImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class OrderRepositoryImpl implements OrderRepository {
    private final Connection connection;
    private static OrderRepositoryImpl instance;


    private ResultSetMapper resultSetMapper;

    public OrderRepositoryImpl(Connection connection) {
        this.resultSetMapper = ResultSetMapperImpl.getInstance();
        this.connection = connection;
    }

    public static OrderRepositoryImpl getInstance() {
        if (instance == null) {
            instance = new OrderRepositoryImpl(ConnectionManager.getInstance().getConnection());
        }
        return instance;
    }


    @Override
    public Order findById(Integer id) throws SQLException {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM orders where order_id =?");
            preparedStatement.setObject(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSetMapper.mapToOrder(resultSet);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean deleteById(Integer id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE from orders where order_id=?");
            preparedStatement.setObject(1, id);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    @Override
    public List<Order> findAll() {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM orders");
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSetMapper.mapToOrderList(resultSet);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Order save(Order order) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO orders(order_id, customer_id, order_date) VALUES(?,?,CURRENT_TIMESTAMP)");
            preparedStatement.setObject(1, order.getId());
            preparedStatement.setObject(2, order.getCustomer().getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return order;
    }
}
