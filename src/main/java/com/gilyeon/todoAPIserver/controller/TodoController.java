package com.gilyeon.todoAPIserver.controller;

import com.gilyeon.todoAPIserver.model.TodoEntity;
import com.gilyeon.todoAPIserver.model.TodoRequest;
import com.gilyeon.todoAPIserver.model.TodoResponse;
import com.gilyeon.todoAPIserver.service.TodoService;
import lombok.AllArgsConstructor;
import lombok.experimental.StandardException;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@AllArgsConstructor
@RequestMapping("/api") // Base URl
public class TodoController {
    // API spec 보면서 개발

    private TodoService service;

    @PostMapping("/create-todo")
    public ResponseEntity<TodoResponse> create(@RequestBody TodoRequest request){
        System.out.println("Create");
        // title 이 없으면 잘못된 request
        if(ObjectUtils.isEmpty(request.getTitle()))
            return ResponseEntity.badRequest().build();

        // order 와 complete 가 없다면 default 값으로 넣어줌
        if (ObjectUtils.isEmpty(request.getOrder()))
            request.setOrder(0L);
        if (ObjectUtils.isEmpty(request.getCompleted()))
            request.setCompleted(false);

        TodoEntity result = this.service.addTodo(request);

        return ResponseEntity.ok(new TodoResponse(result)); // 받은 result를 TodoResponse에 매핑해서 내려줌
    }

    @GetMapping("/todo-item/{id}")
    public ResponseEntity<TodoResponse> readOne(@PathVariable Long id){     // 경로로 받은 값을 쓰기위해서
        System.out.println("Read One");
        TodoEntity result = this.service.searchByID(id);

        return ResponseEntity.ok(new TodoResponse(result));
    }

    @GetMapping("/all-todo/")
    public ResponseEntity<TodoResponse> readAll(){
        System.out.println("Read ALl");
//        List<TodoEntity> todoList = this.service.searchAll();
//        List<TodoResponse> response = todoList.stream().map(TodoResponse::new).collect(Collectors.toList());
//        return ResponseEntity.ok(response);
        return null;
    }

    @PatchMapping("/update-todo/{id}")
    public ResponseEntity<TodoResponse> update(@PathVariable Long id, @RequestBody TodoRequest request){
        System.out.println("Update");
        TodoEntity result = this.service.updateById(id, request);
        return ResponseEntity.ok(new TodoResponse(result));
    }

    @DeleteMapping("/delete-todo/{id}")
    public ResponseEntity<TodoResponse> deleteOne(@PathVariable Long id){
        System.out.println("Delete One");
        this.service.deleteById(id);      // return 이 따로 없으므로 TodoEntity 에 안넣어도 됨
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete-all")
    public ResponseEntity<TodoResponse> deleteAll(){
        System.out.println("Delete All");
        this.service.deleteAll();
        return ResponseEntity.ok().build();
    }

}
