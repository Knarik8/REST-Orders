package org.example.servlet;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "DeleteOrderServlet", value = "/deleteOrder")

public class DeleteOrderServlet extends GenericServlet {

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) {
        String id = req.getParameter("id");
        orderService.deleteById(Integer.valueOf(id));

    }
}
