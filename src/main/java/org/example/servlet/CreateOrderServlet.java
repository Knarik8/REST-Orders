package org.example.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.model.Customer;
import org.example.model.Order;

import java.io.IOException;
import java.sql.SQLException;


@WebServlet(name = "CreateOrderServlet", value = "/createOrder")
public class CreateOrderServlet extends GenericServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String customerId = req.getParameter("customer_id");
        Customer customer;
        try {
            customer = (Customer) customerService.findById(Integer.valueOf(customerId));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        Order order = new Order(customer);
        orderService.save(order);

    }
}
