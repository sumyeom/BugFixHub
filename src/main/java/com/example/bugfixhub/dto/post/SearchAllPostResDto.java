package com.example.bugfixhub.dto.post;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class SearchAllPostResDto {
    private final Long id;
    private final Long userId;
    private final String userName;
    private final String title;
    private final String type;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    public SearchAllPostResDto(Long id, Long userId, String UserName, String title, String type, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.userId = userId;
        this.userName = UserName;
        this.title = title;
        this.type = type;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
