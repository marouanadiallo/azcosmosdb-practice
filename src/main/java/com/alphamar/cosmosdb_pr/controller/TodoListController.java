package com.alphamar.cosmosdb_pr.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.alphamar.cosmosdb_pr.entity.Todo;
import com.alphamar.cosmosdb_pr.repository.TodoRepository;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;


@Controller
@Slf4j
public class TodoListController {

    private final TodoRepository todoRepository;

    TodoListController(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @GetMapping("/new-todo")
    String newTodo(Model model) {
        Todo newTodo = new Todo();
        model.addAttribute("newTodo", newTodo);

        return "new-todo";
    }

    @PostMapping("/new-todo")
    String saveTodo(@ModelAttribute() Todo newTodo) {

        final Mono<Todo> newTodoSaved =  todoRepository.save(newTodo);
        newTodoSaved.subscribe(response -> {
            log.info("New Todo saved: " + response.toString());
        });

        return "redirect:/";
    }
    
}
