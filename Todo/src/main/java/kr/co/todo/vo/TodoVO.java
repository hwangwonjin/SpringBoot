package kr.co.todo.vo;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class TodoVO {

    private int no;
    private String content;
    private int status;
    private String rdate;

}
