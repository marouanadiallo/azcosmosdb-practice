package com.alphamar.cosmosdb_pr;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.azure.cosmos.CosmosClient;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class CosmosdbPrApplication implements CommandLineRunner{

	@Getter
	private final CosmosClient cosmosClient;

	public CosmosdbPrApplication(CosmosClient cosmosClient) {
		this.cosmosClient = cosmosClient;
	}

	public static void main(String[] args) {
		SpringApplication.run(CosmosdbPrApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		log.info("CosmosDB Pr Application started");
		this.cosmosClient.createDatabaseIfNotExists("ToDoList");
		this.cosmosClient.getDatabase("TodoList").createContainerIfNotExists("items", "/category");
	}
}
