package com.example.dynamodb.config;

import com.amazonaws.client.builder.AwsClientBuilder.EndpointConfiguration;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
@EnableDynamoDBRepositories(basePackages = "com.example.dynamodb.repo")
public class DynamoDBConfig {

  @Value("${amazon.dynamodb.endpoint}")
  private String dynamoDbEndpoint;

  @Bean
  public AmazonDynamoDB amazonDynamoDB() {
    return AmazonDynamoDBClientBuilder.standard()
        .withEndpointConfiguration(
            new EndpointConfiguration(dynamoDbEndpoint, Regions.US_WEST_2.getName()))
        .build();
  }

  /**
   * Create DynamoDB mapper bean.
   *
   * @param amazonDynamoDB DynamoDB.
   */
  @Bean
  @Primary
  public DynamoDBMapper dynamoDbMapper(AmazonDynamoDB amazonDynamoDB) {
    return new DynamoDBMapper(amazonDynamoDB);
  }
}
