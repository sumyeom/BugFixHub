package com.example.bugfixhub.service.user;

import com.example.bugfixhub.dto.post.GetAllUserPostResDto;
import com.example.bugfixhub.dto.user.*;

public interface UserService {

    UserResDto signUp(CreateUserReqDto dto);

    UserResDto login(LoginReqDto dto);

    UserDetailResDto findById(Long id, Long myId);

    UserResDto update(Long id, UpdateUserReqDto dto);

    void checkPassword(String password, Long id);

    void delete(Long id);

    GetAllUserPostResDto findAllUserPost(Long id, int page, int limit);
}
