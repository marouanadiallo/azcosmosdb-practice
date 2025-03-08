package com.alphamar.cosmosdb_pr;

import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import org.springframework.data.annotation.Id;

import com.azure.cosmos.CosmosClient;
import com.azure.cosmos.CosmosContainer;
import com.azure.cosmos.CosmosDatabase;
import com.azure.cosmos.models.CosmosItemRequestOptions;
import com.azure.cosmos.models.PartitionKey;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class LearnMS implements ApplicationRunner {
    private static final String DATABASE_NAME = "Customers";
    private static final String CONTAINER_ID = "profile";
    private static final String PARTITION_KEY = "/firstName";

    private final CosmosClient cosmosClient;

    public LearnMS(CosmosClient cosmosClient) {
        this.cosmosClient = cosmosClient;
    }

    @Override
    public void run(org.springframework.boot.ApplicationArguments args) throws Exception {
        var container = this.initializeCosmosDatabase();
        this.insertCustomer(container);
    }
    
    private CosmosContainer initializeCosmosDatabase() {

        // New instance of Database class referencing the server-side database
        log.info("Creating database...");
        this.cosmosClient.createDatabaseIfNotExists(DATABASE_NAME);

        // Read the database
        log.info("Reading database...");
        CosmosDatabase db = this.cosmosClient.getDatabase(DATABASE_NAME);
        //db.read();

        // Delete the database
        // log.info("Deleting database...");
        // this.cosmosClient.getDatabase(DATABASE_NAME).delete();

        // create a new container in the database, serveless mode, so no need to specify throughput
        log.info("Creating container...");
        db.createContainerIfNotExists(CONTAINER_ID, PARTITION_KEY);
      
        // Get container id and read the container
        log.info("Reading container...");
        return db.getContainer(CONTAINER_ID);
        //container.read();

        // Delete the container
        // log.info("Deleting container...");
        // container.delete();
    }

    record Customer( @Id Long id, String firstName, String lastName, String city, String country) {}
    private void insertCustomer(CosmosContainer container) {
        // insert a new customer into the container
        log.info("Inserting customer...");
        
        Customer newCustomer = new Customer(5L, "John", "Doe", "Seattle", "USA");
        container.createItem(newCustomer, new PartitionKey(newCustomer.firstName), new CosmosItemRequestOptions());
    }

    private void queryCustomers(CosmosContainer container) {
        // query all customers in the container
        log.info("Querying customers...");
        
    }
}
