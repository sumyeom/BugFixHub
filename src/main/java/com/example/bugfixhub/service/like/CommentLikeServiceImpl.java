package com.example.bugfixhub.service.like;

import com.example.bugfixhub.dto.likes.CommentLikeResDto;
import com.example.bugfixhub.entity.comment.Comment;
import com.example.bugfixhub.entity.like.CommentLike;
import com.example.bugfixhub.entity.user.User;
import com.example.bugfixhub.repository.comment.CommentRepository;
import com.example.bugfixhub.repository.like.CommentLikeRepository;
import com.example.bugfixhub.repository.user.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class CommentLikeServiceImpl implements CommentLikeService {

    private final CommentLikeRepository commentLikeRepository;
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;

    public CommentLikeServiceImpl(CommentLikeRepository commentLikeRepository,
                                  CommentRepository commentRepository,
                                  UserRepository userRepository) {
        this.commentLikeRepository = commentLikeRepository;
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
    }


    /**
     * 1. 댓글 좋아요:
     * 댓글 및 본인 여부 확인 -> 중복 확인 -> 좋아요
     */
    @Transactional
    public CommentLikeResDto likeComment(Long commentId, Long userId) {


        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "댓글을 찾을 수 없습니다."));


        if (comment.getUser().getId().equals(userId)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "본인 댓글에는 좋아요를 남길 수 없습니다");
        }


        if (commentLikeRepository.existsByCommentIdAndUserId(commentId, userId)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "이미 좋아요를 남긴 댓글입니다.");
        }


        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "사용자를 찾을 수 없습니다."));



        CommentLike commentLike = new CommentLike(user, comment);
        commentLikeRepository.save(commentLike);

        return new CommentLikeResDto(commentLike);

    }

    /**
     * 2. 댓글 좋아요 취소
     */
    @Transactional
    @Override
    public void deleteLike(Long commentId, Long userId) {

        if (!commentLikeRepository.existsByCommentIdAndUserId(commentId, userId)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "좋아요를 남기지 않은 댓글입니다.");
        }

        commentLikeRepository.deleteByCommentIdAndUserId(commentId, userId);

    }

}