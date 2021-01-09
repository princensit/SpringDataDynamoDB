package com.example.dynamodb.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CustomerDto {

  private String id;
  private long operationDate;
  private String firstName;
  private String lastName;
}
