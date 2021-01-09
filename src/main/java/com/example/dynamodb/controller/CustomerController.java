package com.example.dynamodb.controller;

import com.example.dynamodb.dto.CustomerDto;
import com.example.dynamodb.service.CustomerService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController {

  private final CustomerService customerService;

  @Autowired
  public CustomerController(CustomerService customerService) {
    this.customerService = customerService;
  }

  @RequestMapping("/delete")
  public String delete() {
    return customerService.delete();
  }

  @RequestMapping("/save")
  public String save() {
    return customerService.save();
  }

  @RequestMapping("/findAll")
  public List<CustomerDto> findAll() {
    return customerService.findAll();
  }

  @RequestMapping("/findById")
  public List<CustomerDto> findById(@RequestParam("id") String id) {
    return customerService.findById(id);
  }

  @RequestMapping("/findByLastName")
  public List<CustomerDto> findByLastName(@RequestParam("lastName") String lastName) {
    return customerService.findByLastName(lastName);
  }

  @RequestMapping("/findByCustomerIdAndLastNameNot")
  public List<CustomerDto> findByCustomerIdAndLastNameNot(
      @RequestParam("id") String id, @RequestParam("lastName") String lastName) {
    return customerService.findByCustomerIdAndLastNameNot(id, lastName);
  }

  @RequestMapping("/findTopByCustomerIdOrderByOperationDateDesc")
  public CustomerDto findTopByCustomerIdOrderByOperationDateDesc(@RequestParam("id") String id) {
    return customerService.findTopByCustomerIdOrderByOperationDateDesc(id);
  }

  @RequestMapping("/findByCustomerIdAndLastNameNotOrderByOperationDateDesc")
  public List<CustomerDto> findByCustomerIdAndLastNameNotOrderByOperationDateDesc(
      @RequestParam("id") String id, @RequestParam("lastName") String lastName) {
    return customerService.findByCustomerIdAndLastNameNotOrderByOperationDateDesc(id, lastName);
  }
}
