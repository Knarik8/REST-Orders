package org.example.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.model.Customer;

import java.io.IOException;

@WebServlet(name = "CreateCustomerServlet", value = "/createCustomer")
public class CreateCustomerServlet extends GenericServlet{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String email = req.getParameter("email");
        Customer customer = new Customer(firstName, lastName, email);
        customerService.save(customer);

    }
}
