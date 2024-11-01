package com.example.myproject.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString

public class CommentDto {
    private Long id;
    private Long articleId;
    private String nickname;
    private String body;

    public static CommentDto createCommentDto(Comment comment){
        return new CommentDto(
                comment.getId(),
                comment.getArticle().getId(),
                comment.getNickName(),
                comment.getBody()
        );
    }
}



