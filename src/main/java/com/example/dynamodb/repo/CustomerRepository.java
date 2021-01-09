package com.example.dynamodb.repo;

import com.example.dynamodb.model.Customer;
import com.example.dynamodb.model.CustomerId;
import java.util.List;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

@EnableScan
public interface CustomerRepository extends CrudRepository<Customer, CustomerId> {

  List<Customer> findByCustomerId_Id(String id);

  List<Customer> findByLastName(String lastName);

  List<Customer> findByCustomerId_IdAndLastNameNot(String id, String lastNames);

  Customer findTopByCustomerId_IdOrderByOperationDateDesc(String id);

  // sorting not supported for scan operations
  Customer findTopByCustomerId_IdAndLastNameNotOrderByOperationDateDesc(
      String id, String lastNames);
}
