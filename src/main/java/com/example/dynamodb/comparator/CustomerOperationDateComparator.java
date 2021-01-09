package com.example.dynamodb.comparator;

import com.example.dynamodb.dto.CustomerDto;
import java.util.Comparator;

public class CustomerOperationDateComparator implements Comparator<CustomerDto> {

  public static final CustomerOperationDateComparator ASCENDING_COMPARATOR =
      new CustomerOperationDateComparator(SortOrder.ASCENDING);

  public static final CustomerOperationDateComparator DESCENDING_COMPARATOR =
      new CustomerOperationDateComparator(SortOrder.DESCENDING);

  private final SortOrder sortOrder;

  public CustomerOperationDateComparator(SortOrder sortOrder) {
    this.sortOrder = sortOrder;
  }

  @Override
  public int compare(CustomerDto o1, CustomerDto o2) {
    Long operationDate1 = o1.getOperationDate();
    Long operationDate2 = o2.getOperationDate();

    if (SortOrder.ASCENDING.equals(sortOrder)) {
      return operationDate1.compareTo(operationDate2);
    } else {
      return operationDate2.compareTo(operationDate1);
    }
  }
}
