package com.example.bugfixhub.dto.user;

import com.example.bugfixhub.entity.user.User;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class UserResDto {

    private final Long id;

    private final String name;

    private final String email;

    private final LocalDateTime createdAt;

    private final LocalDateTime updatedAt;

    public UserResDto(Long id, String name, String email, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public UserResDto(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.createdAt = user.getCreatedAt();
        this.updatedAt = user.getUpdatedAt();
    }
}

