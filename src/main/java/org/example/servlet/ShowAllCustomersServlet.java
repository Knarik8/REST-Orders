package org.example.servlet;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.model.Customer;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "CustomerServlet", value = "/getAllCustomers")
public class ShowAllCustomersServlet extends GenericServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        List<Customer> customers = customerService.findAll();
        for (Customer customer : customers) {
            outGoingCustomerDTO = customerDTOMapper.map(customer);
            jsonCustomer = objectMapper.writeValueAsString(outGoingCustomerDTO);

            PrintWriter out = resp.getWriter();
            out.println(jsonCustomer);
        }
    }
}


