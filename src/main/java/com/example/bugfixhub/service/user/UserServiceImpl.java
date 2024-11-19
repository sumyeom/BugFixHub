package com.example.bugfixhub.service.user;

import com.example.bugfixhub.config.PasswordEncoder;
import com.example.bugfixhub.dto.user.CreateUserReqDto;
import com.example.bugfixhub.dto.user.LoginReqDto;
import com.example.bugfixhub.dto.user.UserDetailResDto;
import com.example.bugfixhub.dto.user.UserResDto;
import com.example.bugfixhub.entity.user.User;
import com.example.bugfixhub.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserResDto signUp(CreateUserReqDto dto) {

        Optional<User> findUser = userRepository.findByEmail(dto.getEmail());

        if (findUser.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "이미 존재하는 이메일입니다.");
        }

        String password = passwordEncoder.encode(dto.getPassword());

        User saveUser = new User(dto.getName(), dto.getEmail(), password);

        return new UserResDto(userRepository.save(saveUser));
    }

    @Override
    public UserResDto login(LoginReqDto dto) {
        User findUser = userRepository.findByEmailOrElseThrow(dto.getEmail());

        if (!passwordEncoder.matches(dto.getPassword(), findUser.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "비밀번호를 확인해주세요.");
        }

        return new UserResDto(findUser);
    }

    @Override
    public UserDetailResDto findById(Long id, Long myId) {
        User findUser = userRepository.findByIdOrElseThrow(id);

        if (isDeleted(findUser)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "탈퇴된 회원입니다.");
        }

        return new UserDetailResDto(findUser, myId);
    }

    private boolean isDeleted(User user) {
        return user.isDeleted();
    }
}
