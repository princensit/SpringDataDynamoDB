package com.example.dynamodb.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;

@NoArgsConstructor
@ToString
@DynamoDBTable(tableName = "Customer")
public class Customer {

  @Id
  private CustomerId customerId = new CustomerId();

  @Getter
  @Setter
  @DynamoDBAttribute
  private String firstName;

  @Getter
  @Setter
  @DynamoDBAttribute
  private String lastName;

  public Customer(String id, long operationDate, String firstName, String lastName) {
    this.customerId.setId(id);
    this.customerId.setOperationDate(operationDate);
    this.firstName = firstName;
    this.lastName = lastName;
  }

  // Getter and setters are mandatory for hash and range key
  // See https://github.com/derjust/spring-data-dynamodb/wiki/Use-Hash-Range-keys
  @DynamoDBHashKey(attributeName = "id")
  public String getId() {
    return customerId.getId();
  }

  public void setId(String id) {
    customerId.setId(id);
  }

  @DynamoDBRangeKey(attributeName = "operationDate")
  public long getOperationDate() {
    return customerId.getOperationDate();
  }

  public void setOperationDate(long operationDate) {
    customerId.setOperationDate(operationDate);
  }
}
