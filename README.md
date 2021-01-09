This is a sample project on experimenting CRUD operations using spring-data-dynamodb.

## Setup:
1. Download DynamoDB local
   from https://docs.aws.amazon.com/amazondynamodb/latest/developerguide/DynamoDBLocal.DownloadingAndRunning.html
   Following alias can be useful,
   ```
   alias dynamodb_local='cd ~/Downloads/dynamodb_local_latest; java -Djava.library.path=./DynamoDBLocal_lib -jar DynamoDBLocal.jar -sharedDb'
   ```
2. Download Maven from https://maven.apache.org/download.cgi
   ```
   tar xzvf apache-maven-<version>-bin.tar.gz
   export PATH=<path>/apache-maven-<version>/bin:$PATH
   ```
3. Download JDK 8 from Oracle website.

## Build and Execute Steps:
1. cd SpringDataDynamoDB
2. mvn clean package
3. java -jar target/spring-data-dynamodb-0.0.1-SNAPSHOT.jar
4. Run DynamoDB command on a separate tab, and later make HTTP requests from browser.

## Few HTTP calls,
* http://localhost:8080/delete
* http://localhost:8080/save
* http://localhost:8080/findAll
* http://localhost:8080/findById?id=1
* http://localhost:8080/findByLastName?lastName=Smith2
* http://localhost:8080/findByCustomerIdAndLastNameNot?id=2&lastName=Smith2
* http://localhost:8080/findTopByCustomerIdOrderByOperationDateDesc?id=2
* http://localhost:8080/findByCustomerIdAndLastNameNotOrderByOperationDateDesc?id=2&lastName=Smith2
