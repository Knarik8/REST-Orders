package org.example.servlet;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "DeleteItemServlet", value = "/deleteItem")

public class DeleteItemServlet extends GenericServlet {

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) {
        String id = req.getParameter("id");
        itemService.deleteById(Integer.valueOf(id));

    }
}
