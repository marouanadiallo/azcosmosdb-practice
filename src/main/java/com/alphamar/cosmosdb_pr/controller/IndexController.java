package com.alphamar.cosmosdb_pr.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.alphamar.cosmosdb_pr.entity.Todo;
import com.alphamar.cosmosdb_pr.repository.TodoRepository;
import com.azure.cosmos.models.PartitionKey;

import lombok.Getter;
import reactor.core.publisher.Flux;


@Controller
public class IndexController {

    @Getter
    private final TodoRepository todoRepository;

    IndexController(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @GetMapping("/")
    String appIndex(Model model) {
        Flux<Todo> todos = todoRepository.findAll(new PartitionKey("tasks"));
        model.addAttribute("todos", todos.collectList().block());
        return "index";
    }
    
}
