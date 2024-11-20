package com.example.bugfixhub.dto.post;

import lombok.Getter;

import java.util.List;

@Getter
public class GetAllPostResDto {
    private final Long totalPages;
    private final Long totalElements;
    private final List<GetAllPostResDataDto> data;

    public GetAllPostResDto(Long totalPages, Long totalElements, List<GetAllPostResDataDto> getAllPostResDto) {
        this.totalPages = totalPages;
        this.totalElements = totalElements;
        this.data = getAllPostResDto;
    }
}
