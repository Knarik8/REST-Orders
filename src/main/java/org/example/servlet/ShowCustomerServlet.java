package org.example.servlet;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.model.Customer;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(name = "GetCustomerServlet", value = "/getCustomer")
public class ShowCustomerServlet extends GenericServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String id = req.getParameter("id");
        Customer customer;
        try {
            customer = (Customer) customerService.findById(Integer.valueOf(id));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        outGoingCustomerDTO = customerDTOMapper.map(customer);
        jsonCustomer = objectMapper.writeValueAsString(outGoingCustomerDTO);
        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();
        out.println(jsonCustomer);

    }
}
