package com.example.bugfixhub.dto.user;

import jakarta.validation.constraints.Pattern;
import lombok.Getter;

@Getter
public class UpdateUserReqDto {

    private final String name;
    private final String oldPassword;

    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&#])[A-Za-z\\d@$!%*?&#]{8,}$",
            message = "비밀번호는 최소 8자 이상이며, 대문자, 소문자, 숫자, 특수문자를 최소 1개씩 포함해야 합니다."
    )
    private final String newPassword;

    public UpdateUserReqDto(String name, String oldPassword, String newPassword) {
        this.name = name;
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
    }
}
