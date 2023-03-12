package com.gilyeon.todoAPIserver.repository;

import com.gilyeon.todoAPIserver.model.TodoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<TodoEntity, Long> {


}
