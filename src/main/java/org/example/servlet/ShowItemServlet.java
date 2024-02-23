package org.example.servlet;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.model.Item;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(name = "GetItemServlet", value = "/getItem")
public class ShowItemServlet extends GenericServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String id = req.getParameter("id");
        Item item;
        try {
            item = (Item) itemService.findById(Integer.valueOf(id));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        resp.setContentType("application/json");
        outGoingItemDTO = itemDTOMapper.map(item);
        jsonCustomer = objectMapper.writeValueAsString(outGoingItemDTO);
        PrintWriter out = resp.getWriter();
        out.println(jsonCustomer);

    }
}
