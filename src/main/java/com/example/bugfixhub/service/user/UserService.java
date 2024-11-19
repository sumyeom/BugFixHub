package com.example.bugfixhub.service.user;

import com.example.bugfixhub.dto.user.CreateUserReqDto;
import com.example.bugfixhub.dto.user.LoginReqDto;
import com.example.bugfixhub.dto.user.UserResDto;
import jakarta.validation.Valid;

public interface UserService {

    UserResDto signUp(CreateUserReqDto dto);

    UserResDto login(LoginReqDto dto);
}
