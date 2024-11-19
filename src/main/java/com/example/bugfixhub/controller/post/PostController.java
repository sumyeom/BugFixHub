package com.example.bugfixhub.controller.post;

import com.example.bugfixhub.dto.post.PostReqDto;
import com.example.bugfixhub.dto.post.PostResDto;
import com.example.bugfixhub.dto.user.UserResDto;
import com.example.bugfixhub.service.post.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    /**
     * 게시글 등록
     *
     * @param postReqDto
     * @param loginUser
     * @return
     */
    @PostMapping
    public ResponseEntity<PostResDto> create(
            @RequestBody PostReqDto postReqDto,
            @SessionAttribute UserResDto loginUser
    ) {
        PostResDto postResDto = postService.create(loginUser.getId(), postReqDto);

        return new ResponseEntity<>(postResDto, HttpStatus.CREATED);
    }

    /**
     * 게시글 삭제
     *
     * @param loginUser
     * @return
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<PostResDto> delete(
            @PathVariable Long id,
            @SessionAttribute UserResDto loginUser
    ) {
        postService.delete(id, loginUser.getId());

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
