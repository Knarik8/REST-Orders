package org.example.repository.impl;

import org.example.db.ConnectionManager;
import org.example.model.Item;
import org.example.repository.ItemRepository;
import org.example.repository.mapper.ResultSetMapper;
import org.example.repository.mapper.ResultSetMapperImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

    @Override
    public Item findById(Integer id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM items where id=?");
            preparedStatement.setObject(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSetMapper.mapToItem(resultSet);

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
