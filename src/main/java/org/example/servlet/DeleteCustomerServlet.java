package org.example.servlet;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "DeleteCustomerServlet", value = "/deleteCustomer")

public class DeleteCustomerServlet extends GenericServlet {

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) {
        String id = req.getParameter("id");
        customerService.deleteById(Integer.valueOf(id));

    }
}
