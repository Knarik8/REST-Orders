package org.example.service.impl;

import org.example.model.Customer;
import org.example.repository.impl.CustomerRepositoryImpl;
import org.example.service.Service;

import java.util.List;

public class CustomerServiceImpl implements Service<Customer> {

    private static CustomerServiceImpl instance;

    public static CustomerServiceImpl getInstance() {
        if (instance == null) {
            instance = new CustomerServiceImpl();
        }
        return instance;
    }

    CustomerServiceImpl() {
        this.customerRepository = CustomerRepositoryImpl.getInstance();
    }

    CustomerRepositoryImpl customerRepository;

    @Override
    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Customer findById(Integer id) {
        return customerRepository.findById(id);
    }

    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public boolean deleteById(Integer id) {
        return customerRepository.deleteById(id);
    }
}
