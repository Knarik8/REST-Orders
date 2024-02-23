package org.example.servlet;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.model.Item;
import org.example.service.Service;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CreateItemServletTest {
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private Service<Item> itemService;
    @InjectMocks
    private CreateItemServlet servlet;


    @Test
    void testDoPost() throws Exception {
        when(request.getParameter("name")).thenReturn("milk");
        when(request.getParameter("price")).thenReturn("10");
        servlet.itemService = itemService;
        servlet.doPost(request, response);

        verify(itemService).save(new Item("milk", new BigDecimal("10")));
    }
}
