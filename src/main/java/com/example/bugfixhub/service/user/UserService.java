package com.example.bugfixhub.service.user;

import com.example.bugfixhub.dto.post.GetAllPostResDto;
import com.example.bugfixhub.dto.user.CreateUserReqDto;
import com.example.bugfixhub.dto.user.LoginReqDto;
import com.example.bugfixhub.dto.user.UpdateUserReqDto;
import com.example.bugfixhub.dto.user.UserDetailResDto;
import com.example.bugfixhub.dto.user.UserResDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public interface UserService {

    UserResDto signUp(CreateUserReqDto dto);

    UserResDto login(LoginReqDto dto);

    UserDetailResDto findById(Long id, Long myId);

    UserResDto update(Long id, UpdateUserReqDto dto);

    void checkPassword(String password, Long id);

    void delete(Long id);

    GetAllPostResDto findAllUserPost(Long id, int page, int limit);
}
