package com.example.bugfixhub.service.post;

import com.example.bugfixhub.dto.post.*;

public interface PostService {
    PostResDto create(Long userId, PostReqDto postReqDto);

    GetIdPostResDto getPostById(Long id);

    void delete(Long id, Long userId);
}
