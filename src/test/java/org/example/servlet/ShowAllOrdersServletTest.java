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

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ShowAllOrdersServletTest {
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private PrintWriter writer;
    @Mock
    private OutGoingOrderDTO outGoingOrderDTO;
    @Mock
    private Service orderService;
    @Mock
    private OrderDTOMapper orderDTOMapper;
    @Mock
    private ObjectMapper objectMapper;
    @InjectMocks()
    private ShowAllOrdersServlet servlet;

    @Test
    void testDoGet() throws Exception {

        List<Order> orders = new ArrayList<>();
        orders.add(new Order());
        orders.add(new Order());

        when(orderService.findAll()).thenReturn(orders);

        when(orderDTOMapper.map(any(Order.class))).thenReturn(outGoingOrderDTO);

        String jsonCustomer = "test";
        when(objectMapper.writeValueAsString(outGoingOrderDTO)).thenReturn(jsonCustomer);
        when(response.getWriter()).thenReturn(writer);
        servlet.doGet(request, response);

        verify(response.getWriter(), times(2)).println(jsonCustomer);
    }
}
