package org.example.servlet;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.model.Customer;
import org.example.model.Order;
import org.example.service.impl.CustomerServiceImpl;
import org.example.service.impl.OrderServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CreateOrderServletTest {

    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private CustomerServiceImpl customerService;
    @Mock
    private OrderServiceImpl orderService;
    @InjectMocks
    private CreateOrderServlet servlet;


    @Test
    void testDoPost() throws Exception {
        when(request.getParameter("customer_id")).thenReturn("1");
        Customer customer = new Customer(1, "Ivan", "Ivanov", "vanya@mail.ru");
        when(customerService.findById(1)).thenReturn(customer);

        servlet.doPost(request, response);
        verify(orderService).save(new Order(customer));
    }
}
