package com.example.bugfixhub.dto.post;

import lombok.Getter;

import java.util.List;

@Getter
public class GetAllUserPostResDto {
    private final Long totalPages;
    private final Long totalElements;
    private final List<GetAllUserPostResDataDto> data;

    public GetAllUserPostResDto(Long totalPages, Long totalElements, List<GetAllUserPostResDataDto> getAllUserPostResDto) {
        this.totalPages = totalPages;
        this.totalElements = totalElements;
        this.data = getAllUserPostResDto;
    }
}
