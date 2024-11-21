package com.example.bugfixhub.dto.likes;

import lombok.*;

@Data
public class PostLikeResDto {

    private final Long id;
    private final Long userId;
    private final String title;
    private final String content;
    private final String type;
    private final Long likeCount;     //좋아요 수
    private final boolean isLiked;    // 사용자가 좋아요 했는지 확인

    public PostLikeResDto(Long id, Long userId, String title, String content, String type, Long likeCount, boolean isLiked) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.content = content;
        this.type = type;
        this.likeCount = likeCount;
        this.isLiked = isLiked;

    }


}
