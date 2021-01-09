package com.example.dynamodb.service;

import com.example.dynamodb.comparator.CustomerOperationDateComparator;
import com.example.dynamodb.dto.CustomerDto;
import com.example.dynamodb.model.Customer;
import com.example.dynamodb.repo.CustomerRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

  private static final Random random = new Random();
  private final CustomerRepository repository;
  private final CustomerAdapter customerAdapter;

  @Autowired
  public CustomerServiceImpl(CustomerRepository repository, CustomerAdapter customerAdapter) {
    this.repository = repository;
    this.customerAdapter = customerAdapter;
  }

  @Override
  public String delete() {
    repository.deleteAll();
    return "Done";
  }

  @Override
  public String save() {
    List<Customer> customers = new ArrayList<>();
    customers.add(getCustomer("1", "Adam1", "Johnson1"));
    customers.add(getCustomer("1", "Adam2", "Johnson2"));
    customers.add(getCustomer("1", "Adam3", "Johnson3"));
    customers.add(getCustomer("2", "Kim1", "Smith1"));
    customers.add(getCustomer("2", "Kim2", "Smith2"));
    customers.add(getCustomer("2", "Kim3", "Smith3"));
    customers.add(getCustomer("3", "David1", "Williams1"));
    customers.add(getCustomer("3", "David2", "Williams2"));
    customers.add(getCustomer("3", "David3", "Williams3"));
    customers.add(getCustomer("4", "Peter1", "Davis1"));
    customers.add(getCustomer("4", "Peter2", "Davis2"));
    customers.add(getCustomer("4", "Peter3", "Davis3"));

    repository.saveAll(customers);

    return "Done";
  }

  @Override
  public List<CustomerDto> findAll() {
    return getCustomerDtoList(repository.findAll());
  }

  @Override
  public List<CustomerDto> findById(String id) {
    return getCustomerDtoList(repository.findByCustomerId_Id(id));
  }

  @Override
  public List<CustomerDto> findByLastName(String lastName) {
    return getCustomerDtoList(repository.findByLastName(lastName));
  }

  @Override
  public List<CustomerDto> findByCustomerIdAndLastNameNot(String id, String lastName) {
    return getCustomerDtoList(repository.findByCustomerId_IdAndLastNameNot(id, lastName));
  }

  @Override
  public CustomerDto findTopByCustomerIdOrderByOperationDateDesc(String id) {
    return customerAdapter.convert(repository.findTopByCustomerId_IdOrderByOperationDateDesc(id));
  }

  @Override
  public List<CustomerDto> findByCustomerIdAndLastNameNotOrderByOperationDateDesc(
      String id, String lastName) {
    // sorting not supported for scan operations, so doing manual sorting
    List<CustomerDto> customerDtoList =
        getCustomerDtoList(repository.findByCustomerId_IdAndLastNameNot(id, lastName));
    customerDtoList.sort(CustomerOperationDateComparator.DESCENDING_COMPARATOR);

    return customerDtoList;
  }

  private List<CustomerDto> getCustomerDtoList(Iterable<Customer> customers) {
    List<CustomerDto> customerDtos = new ArrayList<>();
    for (Customer customer : customers) {
      customerDtos.add(customerAdapter.convert(customer));
    }

    return customerDtos;
  }

  private Customer getCustomer(String id, String firstName, String lastName) {
    return new Customer(id, random.nextInt(50), firstName, lastName);
  }
}
