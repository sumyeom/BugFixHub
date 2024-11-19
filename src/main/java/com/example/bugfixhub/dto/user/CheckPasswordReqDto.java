package com.example.bugfixhub.dto.user;

import com.fasterxml.jackson.annotation.JsonCreator;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class CheckPasswordReqDto {

    @NotNull(message = "비밀번호를 입력해주세요.")
    private final String password;

    @JsonCreator
    public CheckPasswordReqDto(String password) {
        this.password = password;
    }
}
