package org.example.servlet;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.service.Service;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DeleteOrderServletTest {

    @Mock
    private Service orderService;
    @Mock
    private HttpServletResponse response;
    @Mock
    private HttpServletRequest request;
    @InjectMocks
    private DeleteOrderServlet servlet;

    @Test
    void doDeleteTest() {
        when(request.getParameter("id")).thenReturn("1");
        servlet.doDelete(request, response);
        verify(orderService, times(1)).deleteById(1);

    }

}
