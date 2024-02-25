package org.example.repository.impl;

import org.example.db.ConnectionManager;
import org.example.model.Customer;
import org.example.model.Item;
import org.example.model.Order;
import org.example.repository.ItemRepository;
import org.example.repository.mapper.ResultSetMapper;
import org.example.repository.mapper.ResultSetMapperImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemRepositoryImpl implements ItemRepository {
    private final Connection connection;
    private static ItemRepositoryImpl instance;

    private ResultSetMapper resultSetMapper;

    public ItemRepositoryImpl(Connection connection) {
        this.resultSetMapper = ResultSetMapperImpl.getInstance();
        this.connection = connection;
    }

    public static ItemRepositoryImpl getInstance() {
        if (instance == null) {
            instance = new ItemRepositoryImpl(ConnectionManager.getInstance().getConnection());
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

    @Override
    public Item findById(Integer id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT i.id, i.name, i.price, o.order_id " +
                    "FROM items i " +
                    "INNER JOIN orders_items oi ON i.id = oi.item_id " +
                    "INNER JOIN orders o ON oi.order_id = o.order_id " +
                    "WHERE i.id = ?");
            preparedStatement.setObject(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            Item item = resultSetMapper.mapToItem(resultSet);
            if (item != null) {
                List<Order> orders = ItemRepositoryImpl.getInstance().findOrdersByItem(item);
                item.setOrderList(orders);
            }
            return item;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public boolean deleteById(Integer id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("delete from items where id=?");
            preparedStatement.setObject(1, id);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    @Override
    public List<Item> findAll() {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM items");
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSetMapper.mapToItemList(resultSet);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Item save(Item item) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO items(id,name, price) VALUES(?,?,?)");
            preparedStatement.setInt(1, item.getId());
            preparedStatement.setString(2, item.getName());
            preparedStatement.setBigDecimal(3, item.getPrice());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return item;
    }
}
