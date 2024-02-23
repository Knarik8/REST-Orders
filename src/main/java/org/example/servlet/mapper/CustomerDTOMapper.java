package org.example.servlet.mapper;

import org.example.model.Customer;
import org.example.servlet.dto.IncomingCustomerDTO;
import org.example.servlet.dto.OutGoingCustomerDTO;
import org.mapstruct.Mapper;

@Mapper
public interface CustomerDTOMapper {
    Customer map(IncomingCustomerDTO incomingDto);

    OutGoingCustomerDTO map(Customer customer);
}
