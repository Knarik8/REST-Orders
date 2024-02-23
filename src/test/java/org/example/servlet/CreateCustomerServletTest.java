package org.example.servlet;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.model.Customer;
import org.example.service.Service;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class CreateCustomerServletTest {

    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private Service customerService;
    @InjectMocks
    private CreateCustomerServlet servlet;



    @Test
    void testDoPost() throws Exception {
        when(request.getParameter("firstName")).thenReturn("Inna");
        when(request.getParameter("lastName")).thenReturn("Petrova");
        when(request.getParameter("email")).thenReturn("inna@mail.ru");

        servlet.doPost(request, response);

        verify(customerService).save(new Customer());
    }
}
