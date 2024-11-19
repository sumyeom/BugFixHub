package com.example.bugfixhub.controller.user;

import com.example.bugfixhub.dto.user.CreateUserReqDto;
import com.example.bugfixhub.dto.user.LoginReqDto;
import com.example.bugfixhub.dto.user.UserResDto;
import com.example.bugfixhub.service.user.UserService;
import com.example.bugfixhub.session.Const;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<UserResDto> signUp(@Valid @RequestBody CreateUserReqDto dto) {
        return new ResponseEntity<>(userService.signUp(dto), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<UserResDto> login(
            @Valid @RequestBody LoginReqDto dto,
            HttpServletRequest request
    ) {
        UserResDto findUser = userService.login(dto);

        HttpSession session = request.getSession();

        session.setAttribute(Const.LOGIN_USER, findUser);

        return new ResponseEntity<>(findUser, HttpStatus.OK);
    }
}
