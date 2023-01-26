package kr.co.farmstory.vo;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class FileVO {

    private int fno;
    private int parent;
    private String newName;
    private String oriName;
    private int download;

}
