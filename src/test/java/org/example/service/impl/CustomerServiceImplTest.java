package org.example.service.impl;

import org.example.model.Customer;
import org.example.repository.impl.CustomerRepositoryImpl;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;
import static org.mockito.Mockito.*;

public class CustomerServiceImplTest {


    @Test
    void testSave() {

        CustomerRepositoryImpl customerRepositoryMock = mock(CustomerRepositoryImpl.class);

        CustomerServiceImpl customerService = new CustomerServiceImpl();
        customerService.customerRepository = customerRepositoryMock;

        Customer customer = new Customer("testFirstName", "testLastName", "testEmail");
        when(customerRepositoryMock.save(customer)).thenReturn(customer); //describe the behaviour
        Customer savedCustomer = customerService.save(customer);
        verify(customerRepositoryMock).save(customer);
        assertEquals(customer, savedCustomer);
    }


    @Test
    void testFindById() {
        CustomerRepositoryImpl customerRepositoryMock = mock(CustomerRepositoryImpl.class);

        CustomerServiceImpl customerService = new CustomerServiceImpl();
        customerService.customerRepository = customerRepositoryMock;

        Customer customer = new Customer(7, "testFirstName", "testLastName", "testEmail");
        when(customerRepositoryMock.findById(7)).thenReturn(customer);
        Customer foundCustomer = customerService.findById(7);
        verify(customerRepositoryMock).findById(7);
        assertEquals(customer, foundCustomer);
    }

    @Test
    void testFindAll() {
        CustomerRepositoryImpl customerRepositoryMock = mock(CustomerRepositoryImpl.class); //create a mock CRImpl

        CustomerServiceImpl customerService = new CustomerServiceImpl();
        customerService.customerRepository = customerRepositoryMock;

        Customer customer1 = new Customer(11, "Ivan", "Ivanov", "ivan@mail.ru");
        Customer customer2 = new Customer(2, "Anna", "Petrova", "anna@mail.ru");
        List<Customer> customers = Arrays.asList(customer1, customer2);
        when(customerRepositoryMock.findAll()).thenReturn(customers); //describe behaviour
        List<Customer> foundCustomers = customerService.findAll(); //call findAll
        verify(customerRepositoryMock).findAll(); // verify that method was called
        assertEquals(customers, foundCustomers); //compare
    }

    @Test
    void testDeleteById() {
        CustomerRepositoryImpl customerRepositoryMock = mock(CustomerRepositoryImpl.class);
        CustomerServiceImpl customerService = new CustomerServiceImpl();
        customerService.customerRepository = customerRepositoryMock;
        when(customerRepositoryMock.deleteById(7)).thenReturn(true);
        boolean result = customerService.deleteById(7);
        verify(customerRepositoryMock).deleteById(7);
        assertTrue(result);
    }

}
