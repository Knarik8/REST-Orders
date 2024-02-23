package org.example.repository.impl;

import org.example.config.ContainersEnvironment;
import org.example.model.Customer;
import org.example.repository.CustomerRepository;
import org.junit.jupiter.api.*;

import java.sql.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CustomerRepositoryTest extends ContainersEnvironment {

    private static Connection connection;
    private static CustomerRepository customerRepository;

    @BeforeAll
    static void beforeAll() throws SQLException {
        mySQLContainer.start();
        String jdbcUrl = mySQLContainer.getJdbcUrl();
        String username = mySQLContainer.getUsername();
        String password = mySQLContainer.getPassword();
        connection = DriverManager.getConnection(jdbcUrl, username, password);
        customerRepository = new CustomerRepositoryImpl(connection);

    }

    @AfterAll
    static void afterAll(){
        mySQLContainer.stop();
    }


    @Test
    public void getAllTest(){

        List<Customer> customerList = customerRepository.findAll();
        assertEquals(3, customerList.size());
    }

    @Test
    void findByIdIfExistTest() throws SQLException {
        int existedId = 2;
        Customer actualCustomer = customerRepository.findById(existedId);
        assertEquals(existedId, actualCustomer.getId());
    }

    @Test
    void findByIdIfNotExistTest() throws SQLException {
        int notExistedId = 7;
        Customer actualCustomer = customerRepository.findById(notExistedId);
        assertNull(actualCustomer);
    }

    @Test
    void deleteByIdTest() throws SQLException {
        Customer customer = createTestCustomer();
        Customer customerFromDB = customerRepository.findById(customer.getId());
        assertNotNull(customerFromDB);
        customerRepository.deleteById(customer.getId());
        assertNull(customerRepository.findById(customer.getId()));
    }

    @Test
    void saveTest() throws SQLException {
        Customer customer = createTestCustomer();
        customerRepository.save(customer);
        assertNotNull(customerRepository.findById(customer.getId()));
    }

    @Test
    void hashCodeTest() throws SQLException {
        Customer customer1 = customerRepository.findById(3);
        Customer customer2 = customerRepository.findById(3);
        assertEquals(customer1.hashCode(), customer2.hashCode());
    }

    Customer createTestCustomer(){
        Customer customer = new Customer();
        customer.setId(1);
        customer.setFirstName("Ivan");
        customer.setLastName("Ivanov");
        customer.setEmail("ivan@mail.ru");
        return customer;

    }
}
