package com.example.dynamodb;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.Projection;
import com.amazonaws.services.dynamodbv2.model.ProjectionType;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.amazonaws.services.dynamodbv2.util.TableUtils;
import com.example.dynamodb.model.Customer;
import java.util.Arrays;
import java.util.List;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DynamoDbTableManager {

  private static final List<Class> TABLES = Arrays.asList(Customer.class);

  private final AmazonDynamoDB amazonDynamoDB;

  private final DynamoDBMapper dynamoDbMapper;

  @Autowired
  public DynamoDbTableManager(AmazonDynamoDB amazonDynamoDB, DynamoDBMapper dynamoDbMapper) {
    this.amazonDynamoDB = amazonDynamoDB;
    this.dynamoDbMapper = dynamoDbMapper;
  }

  /** Initialize tables. */
  @PostConstruct
  public void initializeTables() {
    ProvisionedThroughput provisionedThroughput = new ProvisionedThroughput(5L, 5L);

    TABLES.forEach(
        c -> {
          CreateTableRequest tableRequest = dynamoDbMapper.generateCreateTableRequest(c);
          tableRequest.setProvisionedThroughput(provisionedThroughput);
          if (tableRequest.getGlobalSecondaryIndexes() != null) {
            tableRequest
                .getGlobalSecondaryIndexes()
                .forEach(
                    v -> {
                      v.setProvisionedThroughput(provisionedThroughput);
                      v.setProjection(new Projection().withProjectionType(ProjectionType.ALL));
                    });
          }
          TableUtils.createTableIfNotExists(amazonDynamoDB, tableRequest);
        });
  }

  /** Clean up tables. */
  // @PreDestroy
  public void cleanUpTables() {
    TABLES.forEach(
        c ->
            TableUtils.deleteTableIfExists(
                amazonDynamoDB, dynamoDbMapper.generateDeleteTableRequest(c)));
  }
}
