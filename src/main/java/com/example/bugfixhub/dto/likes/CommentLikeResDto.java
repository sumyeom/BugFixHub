package com.example.bugfixhub.dto.likes;

import lombok.*;

@Data
public class CommentLikeResDto {

    private final Long id;
    private final Long userId;
    private final String content;     // 댓글 내용
    private final Long likeCount;     // 좋아요 수
    private final boolean isLiked;    // 사용자가 좋아요 했는지 확인

    private CommentLikeResDto(Long id, Long userId, String content, Long likeCount, boolean isLiked) {
        this.id = id;
        this.userId = userId;
        this.content = content;
        this.likeCount = likeCount;
        this.isLiked = isLiked;

    }

}
