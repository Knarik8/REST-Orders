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
import java.io.StringWriter;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ShowCustomerServletTest {
    @Mock
    HttpServletResponse response;
    @Mock
    HttpServletRequest request;
    @Mock
    private Service customerService;
    @Mock
    private OutGoingCustomerDTO outGoingCustomerDTO;
    @Mock
    private CustomerDTOMapper customerDTOMapper;
    @Mock
    private ObjectMapper objectMapper;
    @InjectMocks
    private ShowCustomerServlet servlet;


    @Test
    public void testDoGet() throws Exception {

        when(request.getParameter("id")).thenReturn("33");
        Customer customer = new Customer();
        when(customerService.findById(33)).thenReturn(customer);
        when(customerDTOMapper.map(customer)).thenReturn(outGoingCustomerDTO);

        String expectedJson = "{\"id\":1,\"firstName\":\"Ivan\",\"lastName\":\"Ivanov\",\"email\":\"ivan@mail.ru\"}";
        when(objectMapper.writeValueAsString(outGoingCustomerDTO)).thenReturn(expectedJson);

        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(printWriter);

        servlet.doGet(request, response);

        String jsonResponse = stringWriter.toString().trim(); //{"id":1,"firstName":"Ivan","lastName":"Ivanov","email":"ivan@mail.ru"}
        assert jsonResponse.equals(expectedJson);
        verify(response).setContentType("application/json");

        verify(customerService).findById(eq(33));
    }

}
