package org.example.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.model.Customer;
import org.example.service.Service;
import org.example.servlet.dto.OutGoingCustomerDTO;
import org.example.servlet.mapper.CustomerDTOMapper;
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
public class ShowAllCustomersServletTest {
    @Mock
    private Service customerService;
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private PrintWriter writer;
    @Mock
    private OutGoingCustomerDTO outGoingCustomerDTO;
    @Mock
    private CustomerDTOMapper customerDTOMapper;
    @Mock
    private ObjectMapper objectMapper;

    @InjectMocks
    private ShowAllCustomersServlet servlet;

    @Test
    void testDoGet() throws Exception {
        List<Customer> customers = new ArrayList<>();
        customers.add(new Customer());
        customers.add(new Customer());

        when(customerService.findAll()).thenReturn(customers);
        when(customerDTOMapper.map(any(Customer.class))).thenReturn(outGoingCustomerDTO);

        String jsonCustomer = "test";
        when(objectMapper.writeValueAsString(outGoingCustomerDTO)).thenReturn(jsonCustomer);
        when(response.getWriter()).thenReturn(writer);

        servlet.doGet(request, response);

        verify(response.getWriter(), times(2)).println(jsonCustomer);
    }

}
