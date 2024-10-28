package com.example.myproject.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {


    public List<CommentDto> comments(Long articleId){
        //댓글 조회
        List<Comment> comments = commentRepository.findByArticleId(articleId);
        // 엔티티 -> DTO 변환
        List<CommentDto> dtos = new ArrayList<CommentDto>();

        for (int i=0; i<comments.size(); i++){
            Comment c = comments.get(i);
            CommentDto dto = CommentDto.createCommentDto(c); // entitiy to dto - 받아올 땐 entity니까 변환 필수
            dtos.add(dto);
        }
        // 결과 변환
        return dtos;


    }






}
