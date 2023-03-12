package com.gilyeon.todoAPIserver.controller;

import com.gilyeon.todoAPIserver.model.TodoResponse;
import com.gilyeon.todoAPIserver.service.TodoService;
import lombok.AllArgsConstructor;
import lombok.experimental.StandardException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@AllArgsConstructor
@RequestMapping("/api/") // Base URl
public class TodoController {
    // API spec 보면서 개발

    private TodoService todoService;

    @PostMapping
    public ResponseEntity<TodoResponse> create(){
        System.out.println("Create");
        return null;
    }

    @GetMapping("{id}")
    public ResponseEntity<TodoResponse> readOne(){
        System.out.println("Read One");
        return null;
    }

    @GetMapping
    public ResponseEntity<TodoResponse> readAll(){
        System.out.println("Read ALl");
        return null;
    }

    @PatchMapping("{id}")
    public ResponseEntity<TodoResponse> update(){
        System.out.println("Update");
        return null;
    }

    @DeleteMapping("{id")
    public ResponseEntity<TodoResponse> deleteOne(){
        System.out.println("Delete One");
        return null;
    }

    @DeleteMapping
    public ResponseEntity<TodoResponse> deleteAll(){
        System.out.println("Delete All");
        return null;
    }

}
