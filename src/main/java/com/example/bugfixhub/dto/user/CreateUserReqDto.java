package com.example.bugfixhub.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;

@Getter
public class CreateUserReqDto {

    @NotNull(message = "닉네임을 입력해주세요.")
    private final String name;

    @NotNull(message = "이메일을 입력해주세요.")
    @Email(message = "이메일 형식을 확인해주세요.")
    private final String email;

    @NotNull(message = "비밀번호를 입력해주세요.")
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&#])[A-Za-z\\d@$!%*?&#]{8,}$",
            message = "비밀번호는 최소 8자 이상이며, 대문자, 소문자, 숫자, 특수문자를 최소 1개씩 포함해야 합니다."
    )
    private final String password;

    public CreateUserReqDto(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }
}
