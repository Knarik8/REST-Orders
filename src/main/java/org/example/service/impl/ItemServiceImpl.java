package org.example.service.impl;

import org.example.model.Customer;
import org.example.model.Item;
import org.example.repository.ItemRepository;
import org.example.repository.impl.CustomerRepositoryImpl;
import org.example.repository.impl.ItemRepositoryImpl;
import org.example.service.Service;

import java.sql.SQLException;
import java.util.List;

public class ItemServiceImpl implements Service<Item> {

    private static ItemServiceImpl instance;
    ItemRepository itemRepository;


    public static ItemServiceImpl getInstance() {
        if (instance == null) {
            instance = new ItemServiceImpl();
        }
        return instance;
    }

    ItemServiceImpl() {
        this.itemRepository = ItemRepositoryImpl.getInstance();
    }

    @Override
    public Item save(Item item) {
        return itemRepository.save(item);
    }

    @Override
    public Item findById(Integer id) throws SQLException {
        return itemRepository.findById(id);
    }

    @Override
    public List<Item> findAll() {
        return itemRepository.findAll();
    }

    @Override
    public boolean deleteById(Integer id) {
        return itemRepository.deleteById(id);
    }
}
