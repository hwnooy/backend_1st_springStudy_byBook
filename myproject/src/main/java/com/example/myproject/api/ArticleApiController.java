package com.example.myproject.api;

import com.example.myproject.dto.ArticleForm;
import com.example.myproject.entity.Article;
import com.example.myproject.service.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class ArticleApiController {
    @Autowired
    private ArticleService articleService;

    // GET 방식

    @GetMapping("/api/articles")
    public List<Article> index(){
        return articleService.index();
    }

    @GetMapping("/api/articles/{id}")
    public Article show(@PathVariable Long id){
        return articleService.show(id);
    }

    // POST 방식
    @PostMapping("/api/articles")
    public ResponseEntity<Article> create(@RequestBody ArticleForm dto){
        Article created = articleService.create(dto);
        return (created!=null) ?
                ResponseEntity.status(HttpStatus.OK).body(created)
                : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    // PATCH 방식
    @PatchMapping("/api/articles/{id}")
    public ResponseEntity<Article> update(@PathVariable Long id, @RequestBody ArticleForm dto){
        // 서비스를 통해 게시글 수정
        Article updated = articleService.update(id, dto);
        // 수정되면 정상, 안되면 오류 응답
        return (updated!=null) ?
                ResponseEntity.status(HttpStatus.OK).body(updated) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    // DELETE 방식
    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Article> delete(@PathVariable Long id){
        Article deleted = articleService.delete(id);
        return (deleted != null) ?
                ResponseEntity.status(HttpStatus.NO_CONTENT).body(deleted) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

    }

    @PostMapping("/api/transaction-test")
    public ResponseEntity<List<Article>> transactionTest(@RequestBody List<ArticleForm> dtos){
        List<Article> createdList = articleService.createArticles(dtos);
        return (createdList!=null) ?
                ResponseEntity.status(HttpStatus.OK).body(createdList) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}