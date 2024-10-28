//package com.example.myproject.service;
//
//import com.example.myproject.entity.Article;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//@SpringBootTest
//class ArticleServiceTest {
//    @Autowired
//    ArticleService articleService;
//    @Test
//    void index() {
//        // 예상데이터
//        Article a = new Article(1L, "1111", "first content");
//        Article b = new Article(2L, "2222", "second content");
//        Article c = new Article(3L, "3333", "third content");
//
//        List<Article> expected = new ArrayList<Article>(Arrays.asList(a,b,c));
//        // 실제 데이터
//        List<Article> articles = articleService.index();
//        // 비교 및 검증
//        assertEquals(expected.toString(), articles.toString());
//
//    }
//}
//
