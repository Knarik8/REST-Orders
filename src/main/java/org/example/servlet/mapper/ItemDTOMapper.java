package org.example.servlet.mapper;

import org.example.model.Item;
import org.example.servlet.dto.IncomingItemDTO;
import org.example.servlet.dto.OutGoingItemDTO;
import org.mapstruct.Mapper;

@Mapper
public interface ItemDTOMapper {
    Item map(IncomingItemDTO incomingDto);

    OutGoingItemDTO map(Item item);
}
