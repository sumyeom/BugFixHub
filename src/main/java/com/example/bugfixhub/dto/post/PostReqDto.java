package com.example.bugfixhub.dto.post;

import lombok.Getter;

@Getter
public class PostReqDto {
    private final String title;
    private final String contents;
    private final String type;

    public PostReqDto(String title, String contents, String type) {
        this.title = title;
        this.contents = contents;
        this.type = type;
    }
}
