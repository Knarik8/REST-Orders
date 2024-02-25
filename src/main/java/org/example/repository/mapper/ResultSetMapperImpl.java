package org.example.repository.mapper;

import org.example.model.Customer;
import org.example.model.Item;
import org.example.model.Order;
import org.example.repository.impl.CustomerRepositoryImpl;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ResultSetMapperImpl implements ResultSetMapper {

    private static ResultSetMapperImpl instance;
    public static ResultSetMapperImpl getInstance() {
        if (instance==null){
            instance= new ResultSetMapperImpl();
        }
            return instance;
    }

    @Override
    public Customer mapToCustomer(ResultSet resultSet) throws SQLException {
        Customer customer = null;
        while (resultSet.next()) {
            Object id = resultSet.getObject("id");
            Object firstName = resultSet.getObject("first_name");
            Object lastname = resultSet.getObject("last_name");
            Object email = resultSet.getObject("email");
            customer =  new Customer((Integer)id, (String) firstName, (String) lastname, (String) email);
        }
        return customer;
    }

    @Override
    public List<Customer> mapToCustomerList(ResultSet resultSet) throws SQLException {
        List<Customer> customers = new ArrayList<>();
        while (resultSet.next()) {
            Object id = resultSet.getObject("id");
            Object firstName = resultSet.getObject("first_name");
            Object lastname = resultSet.getObject("last_name");
            Object email = resultSet.getObject("email");
            Customer customer = new Customer((Integer) id, (String) firstName, (String) lastname, (String) email);
            customers.add(customer);
        }
        return customers;

    }

    @Override
    public Item mapToItem(ResultSet resultSet) throws SQLException {
        Item item = null;
        while (resultSet.next()) {
            Object id = resultSet.getObject("id");
            Object name = resultSet.getObject("name");
            Object price = resultSet.getObject("price");
            item =  new Item((Integer)id, (String) name, (BigDecimal) price);
        }
        return item;
    }

    @Override
    public List<Item> mapToItemList(ResultSet resultSet) throws SQLException {
        List<Item> items = new ArrayList<>();
        while (resultSet.next()) {
            Object id = resultSet.getObject("id");
            Object name = resultSet.getObject("name");
            Object price = resultSet.getObject("price");
            Item item = new Item((Integer) id, (String) name, (BigDecimal) price);
            items.add(item);
        }
        return items;
    }

    @Override
    public Order mapToOrder(ResultSet resultSet) throws SQLException {
        Order order = null;
        while (resultSet.next()) {
            Object orderId = resultSet.getObject("order_id");
            Date date = resultSet.getDate("order_date");
            int customer_id = resultSet.getInt("customer_id");
            Customer customer = CustomerRepositoryImpl.getInstance().findById(customer_id);
            order =  new Order((Integer)orderId, customer,  date);
        }
        return order;
    }

    @Override
    public List<Order> mapToOrderList(ResultSet resultSet) throws SQLException {
        List<Order> orders = new ArrayList<>();
        while (resultSet.next()) {
            Object orderId = resultSet.getObject("order_id");
            Date date = resultSet.getDate("order_date");
            Customer customer = new Customer();
            Order order =  new Order((Integer)orderId, customer,  date);
            orders.add(order);
        }
        return orders;
    }


}
