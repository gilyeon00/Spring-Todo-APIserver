package com.gilyeon.todoAPIserver.service;

import com.gilyeon.todoAPIserver.model.TodoEntity;
import com.gilyeon.todoAPIserver.model.TodoRequest;
import com.gilyeon.todoAPIserver.repository.TodoRepository;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.AdditionalAnswers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class TodoServiceTest extends TestCase {

    @Mock   // 1. 외부시스템에 의존하지 않고 자체적으로 실행 (네트워크 ,DB와 무관) 2. 실제 데이터베이스를 사용하지 않기때문
    private TodoRepository todoRepository;

    @InjectMocks    // Mock을 주입받아서 사용할 서비스
    private TodoService todoService;

    @Test
    public void testAddTodo() {
        // todoRepository가 save메서드를 호출해서, todoEntity값을 받으면, 받은 Entity값을 받도록 설정
        when(this.todoRepository.save(any(TodoEntity.class)))
                .then(AdditionalAnswers.returnsFirstArg());

        TodoRequest expected = new TodoRequest();
        expected.setTitle("Test Title");

        TodoEntity actual = this.todoService.addTodo(expected);

        assertEquals(expected.getTitle(), actual.getTitle());
    }

    public void testSearchByID() {
    }
}