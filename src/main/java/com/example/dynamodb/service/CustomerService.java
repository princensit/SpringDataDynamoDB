package com.example.dynamodb.service;

import com.example.dynamodb.dto.CustomerDto;
import java.util.List;

public interface CustomerService {

  String delete();

  String save();

  List<CustomerDto> findAll();

  List<CustomerDto> findById(String id);

  List<CustomerDto> findByLastName(String lastName);

  List<CustomerDto> findByCustomerIdAndLastNameNot(String id, String lastName);

  CustomerDto findTopByCustomerIdOrderByOperationDateDesc(String id);

  List<CustomerDto> findByCustomerIdAndLastNameNotOrderByOperationDateDesc(
      String id, String lastName);
}
