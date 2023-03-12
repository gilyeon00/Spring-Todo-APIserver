package com.gilyeon.todoAPIserver.service;

import com.gilyeon.todoAPIserver.model.TodoEntity;
import com.gilyeon.todoAPIserver.model.TodoRequest;
import com.gilyeon.todoAPIserver.model.TodoResponse;
import com.gilyeon.todoAPIserver.repository.TodoRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@AllArgsConstructor
public class TodoService {
    // 우리가 구현을 해야할 구체적인 기능을 포함
    // 기능 명세표 보면서 개발

    private  final TodoRepository todoRepository;


    public TodoEntity addTodo(TodoRequest request){
        TodoEntity todoEntity = new TodoEntity();
        todoEntity.setTitle(request.getTitle());
        todoEntity.setOrder(request.getOrder());
        todoEntity.setCompleted(request.getCompleted());

        // TodoEntity 에 값 넣기
        // TodoEntity entity = this.todoRepository.save(todoEntity);   // save는 제네딕으로 받은 타입을 반환 -> todoEntity 반환 -> 바로 리턴가능
        return this.todoRepository.save(todoEntity);
    }

    public TodoEntity searchByID(Long id) {
        this.todoRepository.findById(id)
                .orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND) );    // id를 못찾으면
        return null;
    }

    public List<TodoEntity> searchAll() {
        return this.todoRepository.findAll();
    }

    public TodoEntity updateById(Long id, TodoRequest request) {
        TodoEntity todoEntity = this.searchByID(id);
        if(request.getTitle() != null)
            todoEntity.setTitle(request.getTitle());
        if(request.getOrder() != null)
            todoEntity.setOrder(request.getOrder());
        if(request.getCompleted() != null)
            todoEntity.setCompleted(request.getCompleted());

        return this.todoRepository.save(todoEntity);
    }

    public void deleteById(Long id) {
        this.todoRepository.deleteById(id);

    }

    public void deleteAll() {
        this.todoRepository.deleteAll();
    }



}
