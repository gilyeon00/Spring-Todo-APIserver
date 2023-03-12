package com.gilyeon.todoAPIserver.service;

import com.gilyeon.todoAPIserver.model.TodoEntity;
import com.gilyeon.todoAPIserver.model.TodoRequest;
import com.gilyeon.todoAPIserver.model.TodoResponse;
import com.gilyeon.todoAPIserver.repository.TodoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TodoService {
    // 우리가 구현을 해야할 구체적인 기능을 포함
    // 기능 명세표 보면서 개발
    private  final TodoRepository todoRepository;


    public TodoEntity addTodo(TodoRequest request){
        return null;
    }

    public TodoEntity searchByID(Long id) {
        return null;
    }

    public List<TodoEntity> searchAll() {
        return null;
    }

    public TodoEntity updateById(Long id) {
        return null;
    }

    public void deleteById(Long id) {

    }

    public void deleteAll() {

    }



}
