package com.gilyeon.todoAPIserver.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

import java.util.Optional;

import com.gilyeon.todoAPIserver.model.TodoEntity;
import com.gilyeon.todoAPIserver.model.TodoRequest;
import com.gilyeon.todoAPIserver.repository.TodoRepository;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.AdditionalAnswers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;


@ExtendWith(MockitoExtension.class)
public class TodoServiceTest {

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

    @Test
    public void testSearchByID() {
        TodoEntity entity = new TodoEntity();
        entity.setId(123L);
        entity.setTitle("Title");
        entity.setOrder(0L);
        entity.setCompleted(false);
        Optional<TodoEntity> optional = Optional.of(entity); // findById의 return type이 Optional이기 때문

        given(this.todoRepository.findById(anyLong()))
                .willReturn(optional);  // 어떤값이던 id값이 주어졌을 때, optional값을 return

        TodoEntity actual = this.todoService.searchByID(123L);  // service에서 searchById했을 때 실제값나옴

        TodoEntity expected = optional.get();

        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getTitle(), actual.getTitle());
        assertEquals(expected.getOrder(), actual.getOrder());
        assertEquals(expected.getCompleted(), actual.getCompleted());
    }

    @Test
    public void searchByIdFailed() {
        given(this.todoRepository.findById((anyLong())))
                .willReturn(Optional.empty());
        // 빈값을 넣었을 때 잘 오류가 나는 지 확인
        assertThrows(ResponseStatusException.class, () -> {
            this.todoService.searchByID(123L);
        });
    }

}