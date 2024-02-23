package org.example.servlet;

import org.example.model.Item;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.service.Service;
import org.example.servlet.dto.OutGoingItemDTO;
import org.example.servlet.mapper.ItemDTOMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ShowAllItemsServletTest {

    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private PrintWriter writer;
    @Mock
    private OutGoingItemDTO outGoingItemDTO;
    @Mock
    private Service itemService;
    @Mock
    private ItemDTOMapper itemDTOMapper;
    @Mock
    private ObjectMapper objectMapper;
    @InjectMocks()
    private ShowAllItemsServlet servlet;

    @Test
    void testDoGet() throws Exception {

        List<Item> items = new ArrayList<>();
        items.add(new Item());
        items.add(new Item());

        when(itemService.findAll()).thenReturn(items);
        when(itemDTOMapper.map(any(Item.class))).thenReturn(outGoingItemDTO);
        String jsonItem = "test";
        when(objectMapper.writeValueAsString(outGoingItemDTO)).thenReturn(jsonItem);
        when(response.getWriter()).thenReturn(writer);

        servlet.doGet(request, response);

        verify(response.getWriter(), times(2)).println(jsonItem);
    }

}
