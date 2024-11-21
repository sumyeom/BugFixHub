package com.example.bugfixhub.dto.likes;

import com.example.bugfixhub.entity.post.Post;
import lombok.*;

@Data
public class PostLikeResDto {


    private final Long id;

    private final Long userId;

    private final String postId;


    public PostLikeResDto(Post post) {
        this.id = post.getId();
        this.userId = post.getUser().getId();
        this.postId = post.getId().toString();

    }

}
