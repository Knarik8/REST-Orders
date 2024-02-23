package org.example.repository.impl;

import org.example.config.ContainersEnvironment;
import org.example.model.Customer;
import org.example.model.Item;
import org.example.repository.ItemRepository;
import org.junit.jupiter.api.*;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ItemRepositoryTest extends ContainersEnvironment {
    private static Connection connection;
    private static ItemRepository itemRepository;



    @BeforeAll
    static void beforeAll() throws SQLException {
        mySQLContainer.start();
        String jdbcUrl = mySQLContainer.getJdbcUrl();
        String username = mySQLContainer.getUsername();
        String password = mySQLContainer.getPassword();
        connection = DriverManager.getConnection(jdbcUrl, username, password);
        itemRepository = new ItemRepositoryImpl(connection);
    }


    @AfterAll
    static void afterAll(){
        mySQLContainer.stop();
    }


    @Test
    public void getAllTest(){

        List<Item> itemList = itemRepository.findAll();
        assertEquals(3, itemList.size());
    }

    @Test
    void findByIdIfExistTest() throws SQLException {
        Item item1 = new Item(2,"milk", new BigDecimal(8));
        Item actualItem = itemRepository.findById(item1.getId());
        assertEquals(item1, actualItem);
    }

    @Test
    void findByIdIfNotExistTest() throws SQLException {
        int notExistedId = 71;
        Item actualItem = itemRepository.findById(notExistedId);
        assertNull(actualItem);
    }

    @Test
    void deleteByIdTest() throws SQLException {
        int existedId = 3;
        Item actualItem = itemRepository.findById(existedId);
        assertNotNull(actualItem);
        itemRepository.deleteById(actualItem.getId());
        assertNull(itemRepository.findById(actualItem.getId()));
    }

    @Test
    void saveTest() throws SQLException {
        Item item1 = new Item();
        item1.setId(8);
        item1.setName("pizza");
        item1.setPrice(new BigDecimal(6));
        itemRepository.save(item1);
        assertNotNull(itemRepository.findById(item1.getId()));
    }

    @Test
    void hashCodeTest() throws SQLException {
        Item item1 = itemRepository.findById(2);
        Item item2 = itemRepository.findById(2);
        assertEquals(item1.hashCode(), item2.hashCode());
    }
}
