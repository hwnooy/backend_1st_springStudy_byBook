package com.example.myproject.repository;

import com.example.myproject.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface CommentRepository extends JpaRepository<Comment, Long> {

    // 특정 게시글의 모든 댓글 조회

    // nativeQuery값을 true로 하면 sql문으로 코딩 가능
    // value 속성에 실행하려는 쿼리 작성
    @Query(value="SELECT * FROM comment WHERE article_id = :articleId", nativeQuery = true)
    List<Comment> findByArticleId(Long articleId);
    // 특정 닉네임의 모든 댓글 조회


    List<Comment> findByNickName(String nickName);

}
