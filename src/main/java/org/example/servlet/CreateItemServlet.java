package org.example.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.model.Item;

import java.io.IOException;
import java.math.BigDecimal;

@WebServlet(name = "CreateItemServlet", value = "/createItem")

public class CreateItemServlet extends GenericServlet{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        BigDecimal price = BigDecimal.valueOf(Long.parseLong(req.getParameter("price")));
        Item item = new Item(name, price);
        itemService.save(item);
    }
}
