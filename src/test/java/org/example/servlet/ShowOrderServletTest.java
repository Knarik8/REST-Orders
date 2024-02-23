package org.example.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.model.Order;
import org.example.service.Service;
import org.example.servlet.dto.OutGoingOrderDTO;
import org.example.servlet.mapper.OrderDTOMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ShowOrderServletTest {
    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private Service orderService;
    @Mock
    private OutGoingOrderDTO outGoingOrderDTO;

    @Mock
    private OrderDTOMapper orderDTOMapper;
    @Mock
    private Order order;

    @Mock
    private ObjectMapper objectMapper;

    @InjectMocks
    private ShowOrderServlet servlet;

    @Test
    void testDoGet() throws SQLException, IOException {
        PrintWriter writerMock = mock(PrintWriter.class);

        when(request.getParameter("order_id")).thenReturn("1");
        when(orderService.findById(1)).thenReturn(order);
        when(orderDTOMapper.map(order)).thenReturn(outGoingOrderDTO);

        String jsonCustomer = "test";
        when(objectMapper.writeValueAsString(outGoingOrderDTO)).thenReturn(jsonCustomer);
        when(response.getWriter()).thenReturn(writerMock);

        servlet.doGet(request, response);

        verify(response.getWriter()).println(jsonCustomer);

    }
}
