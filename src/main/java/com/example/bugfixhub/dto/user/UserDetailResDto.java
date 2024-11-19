package com.example.bugfixhub.dto.user;

import com.example.bugfixhub.entity.user.User;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
public class UserDetailResDto {

    private final Long id;
    private final String email;
    private final String name;
    private final int friends;
    private final boolean isFriend;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    public UserDetailResDto(Long id, String email, String name, int friends, boolean isFriend, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.friends = friends;
        this.isFriend = isFriend;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public UserDetailResDto(User user, Long myId) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.name = user.getName();
        this.friends = user.getFollowers().stream().filter(f -> f.getType().equals("accepted")).toList().size();
        this.isFriend = Objects.equals(myId, user.getId());
        this.createdAt = user.getCreatedAt();
        this.updatedAt = user.getUpdatedAt();
    }
}
