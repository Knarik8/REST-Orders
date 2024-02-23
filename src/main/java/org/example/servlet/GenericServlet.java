package org.example.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServlet;
import org.example.service.Service;
import org.example.service.impl.CustomerServiceImpl;
import org.example.service.impl.ItemServiceImpl;
import org.example.service.impl.OrderServiceImpl;
import org.example.servlet.dto.OutGoingCustomerDTO;
import org.example.servlet.dto.OutGoingItemDTO;
import org.example.servlet.dto.OutGoingOrderDTO;
import org.example.servlet.mapper.CustomerDTOMapper;
import org.example.servlet.mapper.ItemDTOMapper;
import org.example.servlet.mapper.OrderDTOMapper;
import org.mapstruct.factory.Mappers;

public class GenericServlet extends HttpServlet {

    Service customerService;
    Service itemService;

    Service orderService;
    CustomerDTOMapper customerDTOMapper;

    ItemDTOMapper itemDTOMapper;
    OrderDTOMapper orderDTOMapper;
    ObjectMapper objectMapper;
    OutGoingCustomerDTO outGoingCustomerDTO;
    OutGoingItemDTO outGoingItemDTO;
    OutGoingOrderDTO outGoingOrderDTO;
    String jsonCustomer;
    String jsonItem;
    String jsonOrder;

    public GenericServlet() {
        customerService = CustomerServiceImpl.getInstance();
        itemService = ItemServiceImpl.getInstance();
        orderService = OrderServiceImpl.getInstance();
        customerDTOMapper = Mappers.getMapper(CustomerDTOMapper.class);
        itemDTOMapper = Mappers.getMapper(ItemDTOMapper.class);
        orderDTOMapper = Mappers.getMapper(OrderDTOMapper.class);
        objectMapper = new ObjectMapper();
    }
}
