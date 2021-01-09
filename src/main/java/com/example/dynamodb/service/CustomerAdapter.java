package com.example.dynamodb.service;

import com.example.dynamodb.dto.CustomerDto;
import com.example.dynamodb.model.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerAdapter {

  public CustomerDto convert(Customer customer) {
    CustomerDto customerDto = new CustomerDto();
    customerDto.setId(customer.getId());
    customerDto.setOperationDate(customer.getOperationDate());
    customerDto.setFirstName(customer.getFirstName());
    customerDto.setLastName(customer.getLastName());

    return customerDto;
  }
}
