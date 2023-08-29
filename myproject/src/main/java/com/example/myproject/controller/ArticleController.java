package com.example.myproject.controller;

import com.example.myproject.dto.ArticleForm;
import com.example.myproject.entity.Article;
import com.example.myproject.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;

@Slf4j
@Controller
public class ArticleController {
    @Autowired
    private ArticleRepository articleRepository;
    @GetMapping("/articles/new")
    public String newArticleForm(){
        return "articles/new";
    }

    @PostMapping("/articles/create")
    public String createArticle(ArticleForm form){ // 메서드 생성 및 반환값 작성
        log.info(form.toString());
        // 1. DTO를 엔티티로 변환 : form 객체의 toEntitiy() 메서드 호출해서 반환값을 저장.
        Article article = form.toEntity();
        log.info(form.toString());

        //  2. 리파지터리를 이용해 엔티티를 DB에 저장하기.
        Article saved= articleRepository.save(article); // 1번에서 만든 article 엔티티 저장해 saved 객체에 반환.
        log.info(saved.toString());

        return "redirect:/articles/"+saved.getId();
    }

    @GetMapping("/articles/{id}")
    public String show(@PathVariable Long id, Model model){
        log.info("id = "+id);
        // 1. id를 조회해 DB에서 해당 데이터 가져오기
        Article articleEntity = articleRepository.findById(id).orElse(null);
        // 2. 모델에 데이터 등록하기
        model.addAttribute("article", articleEntity);
        // 3. 뷰 페이지 반환하기
        return "articles/show";
    }

    // Create 목록
    @GetMapping("/articles")
    public String index(Model model){
        // 1. 모든 데이터 가져오기
        ArrayList<Article> articleEntityList = articleRepository.findAll();
        // 2. 모델에 데이터 등록하기
        model.addAttribute("articleList", articleEntityList);
        return "articles/index";
    }

    // Edit data
    @GetMapping("/articles/{id}/edit")
    public String edit(@PathVariable Long id, Model model){
        // 수정할 데이터 가져오기
        Article articleEntity = articleRepository.findById(id).orElse(null);
        // 모델에 데이터 등록하기
        model.addAttribute("article", articleEntity);
        // 뷰 페이지 설정하기
        return "articles/edit";
    }

    @PostMapping("/articles/update")
    public String update(ArticleForm form){
        Article articleEntity = form.toEntity();
        //log.info(articleEntity.toString());
        // 엔티티를 DB에 저장하기
        Article target = articleRepository.findById(articleEntity.getId()).orElse(null);
        // 기존 데이터 값을 갱신하기
        if (target != null){ // 기존 데이터가 있다면
            articleRepository.save(articleEntity);
        }

        return "redirect:/articles/"+articleEntity.getId();
    }

    @GetMapping("/articles/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes rttr){
        // 삭제할 대상 가져오기
        log.info("삭제 요청이 들어왔습니다.");
        Article target = articleRepository.findById(id).orElse(null);
        // 대상 엔티티 삭제하기
        log.info(target.toString());
        if(target!=null){
            articleRepository.delete(target);
        }
        rttr.addFlashAttribute("msg", "삭제됐습니다!");
        // 결과 페이지로 리다이렉트하기
        return "redirect:/articles";
    }



}