package org.example.repository.impl;

import org.example.db.ConnectionManager;
import org.example.model.Customer;
import org.example.repository.CustomerRepository;
import org.example.repository.mapper.ResultSetMapper;
import org.example.repository.mapper.ResultSetMapperImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CustomerRepositoryImpl implements CustomerRepository {
    private final Connection connection;
    private static CustomerRepositoryImpl instance;

    private ResultSetMapper resultSetMapper;

    public CustomerRepositoryImpl(Connection connection) {
        this.resultSetMapper = ResultSetMapperImpl.getInstance();
        this.connection = connection;
    }

    public static CustomerRepositoryImpl getInstance() {
        if (instance == null) {
            instance = new CustomerRepositoryImpl(ConnectionManager.getInstance().getConnection());
        }
        return instance;
    }


    @Override
    public Customer findById(Integer id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM customers where id=?");
            preparedStatement.setObject(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSetMapper.mapToCustomer(resultSet);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean deleteById(Integer id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("delete from customers where id=?;");
            preparedStatement.setObject(1, id);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    @Override
    public List<Customer> findAll() {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM customers");
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSetMapper.mapToCustomerList(resultSet);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Customer save(Customer customer) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO customers(first_name, last_name, email) VALUES(?,?,?)");
//            preparedStatement.setInt(1, customer.getId());
            preparedStatement.setString(1, customer.getFirstName());
            preparedStatement.setString(2, customer.getLastName());
            preparedStatement.setString(3, customer.getEmail());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customer;
    }
}
