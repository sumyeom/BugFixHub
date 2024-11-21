package com.example.bugfixhub.dto.likes;

import com.example.bugfixhub.entity.comment.Comment;
import lombok.*;

@Data
public class CommentLikeResDto {


    private final Long id;

    private final Long userId;

    private final Long commentId;


    public CommentLikeResDto(Comment comment) {
        this.id = comment.getId();
        this.userId = comment.getUser().getId();
        this.commentId = comment.getId();

    }

}
