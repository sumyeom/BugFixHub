package com.example.bugfixhub.dto.comments;

import lombok.Getter;

@Getter
public class CommentReqDto {
    private String contents;
    private Long postId;
}