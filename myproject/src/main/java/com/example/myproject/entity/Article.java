package com.example.myproject.entity;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Getter
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column  // DB에서 인식할 수 있게
    private String title;
    @Column
    private String content;

    public void patch(Article article) {  // 수정할 내용이 있을 때만 작동

        if(article.title != null) this.title = article.title;
        if (article.content != null) this.content = article.content;
    }
}
