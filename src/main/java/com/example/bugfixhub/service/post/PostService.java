package com.example.bugfixhub.service.post;

import com.example.bugfixhub.dto.post.*;

import java.util.List;

public interface PostService {
    PostResDto create(Long userId, PostReqDto postReqDto);

    GetAllPostResDto getAllPosts(String type, String title, int page, int limit, Long userId);

    GetIdPostResDto getPostById(Long id);

    PostResDto update(Long id, Long userId, PostReqDto postReqDto);

    List<PostCommentsResDto> getPostComments(Long id);

    void delete(Long id, Long userId);
}
