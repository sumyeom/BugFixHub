package com.example.bugfixhub.controller.like;

import com.example.bugfixhub.dto.likes.CommentLikeResDto;
import com.example.bugfixhub.dto.user.UserResDto;
import com.example.bugfixhub.service.like.CommentLikeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/posts/{postId}/comments")
public class CommentLikeController {

    private final CommentLikeService commentLikeService;


    public CommentLikeController(CommentLikeService commentLikeService) {
        this.commentLikeService = commentLikeService;
    }

    /**
     * 1. 댓글 좋아요
     */
    @PostMapping("/{commentId}/like")
    public ResponseEntity<CommentLikeResDto> likeComment(
            @PathVariable Long postId,
            @PathVariable Long commentId,
            @SessionAttribute UserResDto loginUser){

        CommentLikeResDto commentLikeResDto = commentLikeService.likeComment(postId, commentId);

        return ResponseEntity.status(HttpStatus.CREATED).body(commentLikeResDto);

    }


    /**
     * 2. 댓글 좋아요 취소
     */
    @DeleteMapping("/{commentId}/like")
    public ResponseEntity<Void> unlikeComment(
            @PathVariable Long postId,
            @PathVariable Long commentId,
            @SessionAttribute UserResDto loginUser){

        commentLikeService.deleteLike(postId,commentId);

        return ResponseEntity.noContent().build();
    }
}