package com.example.myproject.api;

import com.example.myproject.dto.CommentDto;
import com.example.myproject.repository.ArticleRepository;
import com.example.myproject.service.CommentService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CommentApiController {
    @Autowired
    private CommentService commentService;
    @Autowired
    private ArticleRepository articleRepository
    // 1. 댓글 조회
    @GetMapping("/api/articles/{articleId}/comments")
    public ResponseEntity<List<CommentDto>> comments(@)

    // 2. 댓글 생성

    // 3. 댓글 수정

    // 4. 댓글 삭제
    @Autowired
    private ArticleRepository articleRepository;

}
