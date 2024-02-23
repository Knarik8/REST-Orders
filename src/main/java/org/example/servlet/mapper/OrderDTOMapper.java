package org.example.servlet.mapper;

import org.example.model.Order;
import org.example.servlet.dto.IncomingOrderDTO;
import org.example.servlet.dto.OutGoingOrderDTO;
import org.mapstruct.Mapper;

@Mapper
public interface OrderDTOMapper {
    Order map(IncomingOrderDTO incomingDto);

    OutGoingOrderDTO map(Order order);
}
