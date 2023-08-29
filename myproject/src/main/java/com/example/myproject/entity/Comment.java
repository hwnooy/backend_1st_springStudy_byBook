package com.example.myproject.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class Comment {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY) // DB가 자동으로 1씩 증가
    private Long id; // 대표 키

    @ManyToOne // Comment 엔티티와 Article 엔티티를 다대일 관계로 설정. 댓글입장에선 다대일 관계
    @JoinColumn(name="article_id")
    private Article article;  // 해당 댓글의 부모 게시글

    @Column
     // 외래키 생성, Article 엔티티의 id와 매핑
    private String nickname; // 댓글을 단 사람

    @Column
    private String body; // 댓글 본문





}
