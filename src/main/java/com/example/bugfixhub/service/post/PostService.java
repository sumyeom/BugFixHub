package com.example.bugfixhub.service.post;

import com.example.bugfixhub.dto.post.*;

public interface PostService {
    PostResDto create(Long userId, PostReqDto postReqDto);
}
