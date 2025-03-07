package com.alphamar.cosmosdb_pr.entity;

import org.springframework.data.annotation.Id;

import com.azure.spring.data.cosmos.core.mapping.Container;
import com.azure.spring.data.cosmos.core.mapping.PartitionKey;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Setter
@ToString
@AllArgsConstructor
@Container(containerName = "items")
public class Todo {

    @Id
    @Getter
    private final Long id;

    @Getter
    private String name;

    @PartitionKey("category")
    public String category;

    @Getter
    private String description;

    @Getter
    private boolean completed;

    public Todo() {
        this.id = System.currentTimeMillis();
        this.category = "tasks";
    }
}
