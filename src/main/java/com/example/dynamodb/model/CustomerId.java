package com.example.dynamodb.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CustomerId implements Serializable {

  private static final long serialVersionUID = 1L;

  @DynamoDBHashKey
  private String id;

  @DynamoDBRangeKey
  private long operationDate;
}
