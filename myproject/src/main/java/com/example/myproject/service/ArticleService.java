package com.example.myproject.service;

import com.example.myproject.dto.ArticleForm;
import com.example.myproject.entity.Article;
import com.example.myproject.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service  // 서비스 객체 생성
public class ArticleService {

    @Autowired
    // 라피지터리와 협업할 수 있게 추가.
    private ArticleRepository articleRepository;

    public ArrayList<Article> index() {
        ArrayList<Article> all = articleRepository.findAll();
        return all;
    }

    public Article show(Long id) {
        return articleRepository.findById(id).orElse(null);
    }

    public Article create(ArticleForm dto) {
        // dto -> 엔티티로 변환 후 article에 저장
        Article article = dto.toEntity();
        // id는 DB가 알아서 생성하기 때문에 article객체에 id가 있으면 null 반환
        if (article.getId() != null){
            return null;
        }
        // article을 DB에 저장한다.
        return articleRepository.save(article);
    }

    public Article update(Long id, ArticleForm dto) {

        // 1. DTO -> 엔티티 변환하기
        Article article = dto.toEntity();
        log.info("id:{}, article:{}", id, article.toString());
        // 2. 타깃 조회하기
        Article target = articleRepository.findById(id).orElse(null);
        // 3. 잘못된 요청 처리하기
        if (target==null || id !=article.getId()) {
            log.info("잘못된 요청! id:{}, article:{}", id, article.toString());
            return null;
        }
        // 4. 업데이트 및 정상 응답하기
        target.patch(article);  // 기존 데이터에 새 데이터 patch(붙이기), 반환값을 받지 않아 void로
        Article updated = articleRepository.save(target);  // 수정 내용 DB에 최종 저장
        return updated;

    }

    public Article delete(Long id) {
        Article target = articleRepository.findById(id).orElse(null);
        if (target==null) return null;
        articleRepository.delete(target);
        return target;

    }

    @Transactional
    public List<Article> createArticles(List<ArticleForm> dtos) {
        // 1. dto 묶음을 엔티티 묶음으로 변환하기
        List<Article> articleList = dtos.stream()
                .map(dto -> dto.toEntity())
                .collect(Collectors.toList());
        // 2. 엔티티 묶음을 DB에 저장하기
        articleList.stream()
                .forEach(article ->articleRepository.save(article));

        // 3. 강제 예외 발생시키기
        articleRepository.findById(-1L)
                .orElseThrow(()->new IllegalArgumentException("결제 실패!"));

        // 4. 결과 값 반환하기
        return articleList;
    }
}