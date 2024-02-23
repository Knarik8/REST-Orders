package org.example.service.impl;

import org.example.model.Item;
import org.example.repository.impl.ItemRepositoryImpl;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;
import static org.mockito.Mockito.*;

public class ItemServiceImplTest {

    @Test
    void testSave() {
        ItemRepositoryImpl itemRepositoryMock = mock(ItemRepositoryImpl.class);

        ItemServiceImpl itemService = new ItemServiceImpl();
        itemService.itemRepository = itemRepositoryMock;

        Item item = new Item(11,"Twix", new BigDecimal(8));

        when(itemRepositoryMock.save(item)).thenReturn(item);
        Item savedItem = itemService.save(item);
        verify(itemRepositoryMock).save(item);
        assertEquals(item, savedItem);
    }

    @Test
    void testFindById() throws SQLException {
        ItemRepositoryImpl itemRepositoryMock = mock(ItemRepositoryImpl.class);
        ItemServiceImpl itemService = new ItemServiceImpl();
        itemService.itemRepository = itemRepositoryMock;
        Item item = new Item(12,"Mars", new BigDecimal(6));
        when(itemRepositoryMock.findById(12)).thenReturn(item);
        Item foundItem = itemService.findById(12);
        verify(itemRepositoryMock).findById(12);
        assertEquals(item, foundItem);
    }

    @Test
    void testFindAll() {
        ItemRepositoryImpl itemRepositoryMock = mock(ItemRepositoryImpl.class);
        ItemServiceImpl itemService = new ItemServiceImpl();
        itemService.itemRepository = itemRepositoryMock;

        Item item1 = new Item(13,"Milk", new BigDecimal(5));
        Item item2 = new Item(14,"Cottage cheese", new BigDecimal(10));
        List<Item> items = Arrays.asList(item1, item2);
        when(itemRepositoryMock.findAll()).thenReturn(items);
        List<Item> foundItems = itemService.findAll();
        verify(itemRepositoryMock).findAll();
        assertEquals(items, foundItems);
    }

    @Test
    void testDeleteById() {
        ItemRepositoryImpl itemRepositoryMock = mock(ItemRepositoryImpl.class);
        ItemServiceImpl itemService = new ItemServiceImpl();
        itemService.itemRepository = itemRepositoryMock;
        when(itemRepositoryMock.deleteById(1)).thenReturn(true);
        boolean result = itemService.deleteById(1);
        verify(itemRepositoryMock).deleteById(1);
        assertTrue(result);
    }
}
