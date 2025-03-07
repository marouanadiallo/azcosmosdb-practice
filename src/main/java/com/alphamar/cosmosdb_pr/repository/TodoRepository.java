package com.alphamar.cosmosdb_pr.repository;

import com.alphamar.cosmosdb_pr.entity.Todo;
import com.azure.spring.data.cosmos.repository.ReactiveCosmosRepository;

import reactor.core.publisher.Flux;

public interface TodoRepository extends ReactiveCosmosRepository<Todo, Long> {
    Flux<Todo> findByName(String firstName);
}
