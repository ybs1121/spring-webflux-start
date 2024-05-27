package com.study.springwebfluxstart;



import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("posts")
@NoArgsConstructor
@Setter
@ToString
public class Post {

    @Id
    private Long id;
    private String title;
    private String content;

}
