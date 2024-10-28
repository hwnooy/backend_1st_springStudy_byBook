package com.example.myproject.dto;

import com.example.myproject.entity.Article;
import lombok.AllArgsConstructor;
import lombok.ToString;


@AllArgsConstructor
@ToString
public class ArticleForm { // 폼 데이터를 담아올 그릇 : DTO가 됨

    private Long id;
    private String title;
    private String content;

    public Article toEntity() {
        return new Article(id, title, content);
    }
}
