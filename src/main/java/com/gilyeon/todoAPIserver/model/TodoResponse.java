package com.gilyeon.todoAPIserver.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TodoResponse {
    // response 는 모든걸 다 내려줘야하기 때문에, Entity 에 있는 거 모두 + url 을 선언해준다.
    private long id;
    private String title;
    private long order;
    private boolean completed;
    private String url;


    // 이후 코드작성을 편리하게 하기 위해, TodoEntity 를 파라미터로 하는 생성자 생성
    public TodoResponse(TodoEntity todoEntity){
        this.id = todoEntity.getId();
        this.title = todoEntity.getTitle();
        this.order = todoEntity.getOrder();
        this.completed = todoEntity.getCompleted();

        this.url = "http://localhost:8080/" + this.id; // TodoEntity 에 없는 필드이므로 임의로 baseURl 작성
        // 이렇게 하드코딩하면 config 파일이나, property 에 넣는데 지금은 일단 간단하게 하기위해 작성
    }
}
