package org.example.repository.impl;

import org.example.db.ConnectionManager;
import org.example.model.Customer;
import org.example.model.Item;
import org.example.model.Order;
import org.example.repository.OrderRepository;
import org.example.repository.mapper.ResultSetMapper;
import org.example.repository.mapper.ResultSetMapperImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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


    public List<Order> findOrdersByItem(Item item) {
        List<Order> orders = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT o.* " +
                            "FROM orders o " +
                            "INNER JOIN orders_items oi ON o.order_id = oi.order_id " +
                            "WHERE oi.item_id = ?");
            preparedStatement.setObject(1, item.getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Order order = new Order();
                order.setId(resultSet.getInt("order_id"));
                int customerId = resultSet.getInt("customer_id");
                Customer customer = CustomerRepositoryImpl.getInstance().findById(customerId);
                order.setCustomer(customer);
                order.setDate(resultSet.getDate("order_date"));
                orders.add(order);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return orders;
    }

    public List<Item> findItemsByOrder(Order order) {
        List<Item> items = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT i.* FROM items i INNER JOIN orders_items oi ON i.id = oi.item_id WHERE oi.order_id = ?");
            preparedStatement.setObject(1, order.getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Item item = new Item();
                item.setId(resultSet.getInt("id"));
                item.setName(resultSet.getString("name"));
                item.setPrice(resultSet.getBigDecimal("price"));
                items.add(item);
            }
//            List<Order> orderList = findOrdersByItem(i)// взять все orders где есть этот item

            for (Item item: items){
                List<Order> orderList = findOrdersByItem(item);
                item.setOrderList(orderList);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return items;
    }
    @Override
    public Order findById(Integer id) throws SQLException {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT o.*, i.* FROM orders o LEFT JOIN orders_items oi ON o.order_id = oi.order_id LEFT JOIN items i ON oi.item_id = i.id WHERE o.order_id = ?");
            preparedStatement.setObject(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            Order order = resultSetMapper.mapToOrder(resultSet);
            if (order!=null){
                List<Item> items = OrderRepositoryImpl.getInstance().findItemsByOrder(order);
                order.setItemList(items);
                for (Item item: items){
                    List<Order> orderList = findOrdersByItem(item);
                    item.setOrderList(orderList);
                }
                order.setCustomer(order.getCustomer());
            }
            return order;

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
