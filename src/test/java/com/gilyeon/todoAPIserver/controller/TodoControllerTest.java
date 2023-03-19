package com.gilyeon.todoAPIserver.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gilyeon.todoAPIserver.controller.TodoController;
import com.gilyeon.todoAPIserver.model.TodoEntity;
import com.gilyeon.todoAPIserver.model.TodoRequest;
import com.gilyeon.todoAPIserver.service.TodoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.server.ResponseStatusException;


@WebMvcTest(TodoController.class)
public class TodoControllerTest{
    @Autowired
    MockMvc mvc;

    @MockBean
    TodoService todoService;

    private TodoEntity expected;

    @BeforeEach     // 각 테스트 메서드가 실행되기 전에 expected 값을 초기화
    void setup() {
        this.expected = new TodoEntity();
        this.expected.setId(123L);
        this.expected.setTitle("Test title");
        this.expected.setOrder(0L);
        this.expected.setCompleted(false);
    }

    @Test
    public void testCreate() throws Exception {
        // any 로 todoRequest 를 받으면, 받은 request 를 기반으로해서 return new todoEntity 생성
        // entity 는 expect 의 모든걸 가져온다 <- entity 를 만들어준
        when(this.todoService.addTodo(any(TodoRequest.class)))
                .then((i) ->{
                    TodoRequest request = i.getArgument(0, TodoRequest.class);
                    return new TodoEntity(this.expected.getId(),
                            request.getTitle(),     // Title 만 request 로 들어온값을 반환
                            this.expected.getOrder(),
                            this.expected.getCompleted());
                });

        TodoRequest request = new TodoRequest();
        request.setTitle("Any Title");

        // 우리가 이렇게 작성한 request 는 request body 에 넣어줘야하는데, Object type 자체로는 넣을 수 없으므로
        // ObjectMapper 를 사용
        ObjectMapper mapper = new ObjectMapper();
        String content = mapper.writeValueAsString(request); // line47의 request 가 string 으로 바뀜, throw Exception 필요

        mvc.perform(post("/api/create-todo")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Any Title"));

    }

    public void testReadOne() {
    }
}