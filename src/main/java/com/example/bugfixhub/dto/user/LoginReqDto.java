package com.example.bugfixhub.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class LoginReqDto {

    @NotNull(message = "이메일을 입력해주세요.")
    @Email(message = "enter in email format")
    private final String email;

    @NotNull(message = "비밀번호를 입력해주세요.")
    private final String password;

    public LoginReqDto(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
