package org.example.repository.mapper;

import org.example.model.Customer;
import org.example.model.Item;
import org.example.model.Order;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface ResultSetMapper {


    Customer mapToCustomer(ResultSet resultSet) throws SQLException;

    List<Customer> mapToCustomerList(ResultSet resultSet) throws SQLException;

    Item mapToItem(ResultSet resultSet) throws SQLException;

    List<Item> mapToItemList(ResultSet resultSet) throws SQLException;

    Order mapToOrder(ResultSet resultSet) throws SQLException;

    List<Order> mapToOrderList(ResultSet resultSet) throws SQLException;

}
