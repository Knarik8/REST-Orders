package org.example.servlet;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.model.Item;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "ItemServlet", value = "/getAllItems")
public class ShowAllItemsServlet extends GenericServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        List<Item> items = itemService.findAll();
        for (Item item : items) {
            outGoingItemDTO = itemDTOMapper.map(item);
            jsonItem = objectMapper.writeValueAsString(outGoingItemDTO);

            PrintWriter out = resp.getWriter();
            out.println(jsonItem);
        }
    }
}


