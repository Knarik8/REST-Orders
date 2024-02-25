package org.example.servlet;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.model.Order;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(name = "GetOrderServlet", value = "/getOrder")
public class ShowOrderServlet extends GenericServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String id = req.getParameter("order_id");
        Order order;
        try {
            order = (Order) orderService.findById(Integer.valueOf(id));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        resp.setContentType("application/json");

        outGoingOrderDTO = orderDTOMapper.map(order);
        jsonOrder = objectMapper.writeValueAsString(outGoingOrderDTO);
        PrintWriter out = resp.getWriter();
        out.println(jsonOrder);

    }
}
