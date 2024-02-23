package org.example.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.model.Item;
import org.example.service.Service;
import org.example.servlet.dto.OutGoingItemDTO;
import org.example.servlet.mapper.ItemDTOMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.math.BigDecimal;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ShowItemServletTest {
    @Mock
    private Service itemService;
    @Mock
    private OutGoingItemDTO outGoingItemDTO;

    @Mock
    private ItemDTOMapper itemDTOMapper;

    @Mock
    private ObjectMapper objectMapper;

    @InjectMocks
    private ShowItemServlet servlet;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Test
    public void testDoGet() throws Exception {
        when(request.getParameter("id")).thenReturn("13");

        Item item = new Item(2, "choco", new BigDecimal(7));
        when(itemService.findById(13)).thenReturn(item);

        when(itemDTOMapper.map(item)).thenReturn(outGoingItemDTO);

        String expectedJson = "{\"id\":2,\"name\":\"choco\",\"price\":7}";
        when(objectMapper.writeValueAsString(outGoingItemDTO)).thenReturn(expectedJson);

        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(printWriter);

        servlet.doGet(request, response);

        verify(response).setContentType("application/json");

        String jsonResponse = stringWriter.toString().trim();
        assert jsonResponse.equals(expectedJson);
    }
}
