package org.example.servlet;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.example.model.Order;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "OrderServlet", value = "/getAllOrders")
public class ShowAllOrdersServlet extends GenericServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        List<Order> orders = orderService.findAll();
        for (Order order : orders) {
            outGoingOrderDTO = orderDTOMapper.map(order);
            jsonOrder = objectMapper.writeValueAsString(outGoingOrderDTO);

            PrintWriter out = resp.getWriter();
            out.println(jsonOrder);
        }
    }
}


