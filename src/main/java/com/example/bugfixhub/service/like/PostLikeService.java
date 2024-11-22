package com.example.bugfixhub.service.like;


import com.example.bugfixhub.dto.likes.PostLikeResDto;
import com.example.bugfixhub.entity.like.PostLike;

public interface PostLikeService {
    PostLikeResDto addOrCancleLike(Long postId, Long userId);
}