package com.example.bugfixhub.controller.like;

import com.example.bugfixhub.dto.likes.PostLikeResDto;
import com.example.bugfixhub.dto.user.UserResDto;
import com.example.bugfixhub.service.like.PostLikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/post")
public class PostLikeController {

    private final PostLikeService postLikeService;

    @PostMapping("/{postId}/like")
    public ResponseEntity<PostLikeResDto> postLike(@PathVariable Long postId,
                                                   @SessionAttribute UserResDto loginUser) {
        PostLikeResDto postLikeResDto = postLikeService.addOrCancleLike(postId, loginUser.getId());

        return new ResponseEntity<>(postLikeResDto, HttpStatus.CREATED);
    }
}
