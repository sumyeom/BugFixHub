package com.example.bugfixhub.service.user;

import com.example.bugfixhub.config.PasswordEncoder;
import com.example.bugfixhub.dto.user.UpdateUserReqDto;
import com.example.bugfixhub.dto.user.UserResDto;
import com.example.bugfixhub.entity.user.User;
import com.example.bugfixhub.repository.post.PostRepository;
import com.example.bugfixhub.repository.user.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {
    @Mock
    private UserRepository userRepository;
    @Mock
    private PostRepository postRepository;
    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    @DisplayName("Dto에 비밀번호 값들 없음")
    void update_oldpwnull_newpwnull() {
        // given
        UpdateUserReqDto dto = new UpdateUserReqDto("testName",null,null);
        Long userId = 1L;
        User mockUser = new User("testName","qwer","1234");
        when(userRepository.findByIdOrElseThrow(eq(userId))).thenReturn(mockUser);

        // when
        UserResDto resultDto = userService.update(userId,dto);

        // then
        assertNotNull(resultDto);
        assertEquals("testName",resultDto.getName());
        verify(userRepository,times(1))
                .findByIdOrElseThrow(eq(userId));

    }

    @Test
    @DisplayName("new pw 없음")
    void update_dto_newpwnull() {
        // given
        UpdateUserReqDto dto = new UpdateUserReqDto("testName","qwer",null);
        Long userId = 1L;
        User mockUser = new User("testName","qwer","1234");
        when(userRepository.findByIdOrElseThrow(eq(userId))).thenReturn(mockUser);

        // when
        //UserResDto resultDto = userService.update(userId,dto);
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, ()->
                userService.update(userId,dto));

        // then
        assertEquals(HttpStatus.BAD_REQUEST,exception.getStatusCode());

    }

    @Test
    @DisplayName("old pw 없음")
    void update_dto_oldpwnull() {
        // given
        UpdateUserReqDto dto = new UpdateUserReqDto("testName",null,"1234");
        Long userId = 1L;
        User mockUser = new User("testName","qwer","1234");
        when(userRepository.findByIdOrElseThrow(eq(userId))).thenReturn(mockUser);

        // when
        //UserResDto resultDto = userService.update(userId,dto);
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, ()->
                userService.update(userId,dto));

        // then
        assertEquals(HttpStatus.BAD_REQUEST,exception.getStatusCode());

    }

    @Test
    @DisplayName("old 비밀번호 틀림")
    void update_unmatch_oldpw() {
        // given
        UpdateUserReqDto dto = new UpdateUserReqDto("testName","qwer","1234");
        Long userId = 1L;
        User mockUser = new User("testName","qwer123","1234");
        when(userRepository.findByIdOrElseThrow(eq(userId))).thenReturn(mockUser);
        when(passwordEncoder.matches(anyString(), anyString())).thenReturn(true);

        // when
        UserResDto resultDto = userService.update(userId,dto);

        // then
        assertNotNull(resultDto);
        assertEquals("testName",resultDto.getName());
        verify(userRepository,times(1))
                .findByIdOrElseThrow(eq(userId));

    }

    @Test
    @DisplayName("old pw와 new pw 같음")
    void update_oldpw_equal_newpw() {
        // given
        UpdateUserReqDto dto = new UpdateUserReqDto("testName","qwer","qwer");
        Long userId = 1L;
        User mockUser = new User("testName","qwer","1234");
        when(userRepository.findByIdOrElseThrow(eq(userId))).thenReturn(mockUser);
        when(passwordEncoder.matches(anyString(), anyString())).thenReturn(true);

        // when
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, ()->
                userService.update(userId,dto));

        // then
        assertEquals(HttpStatus.BAD_REQUEST,exception.getStatusCode());

    }

    @Test
    @DisplayName("업데이트 성공")
    void update_success() {
        // given
        UpdateUserReqDto dto = new UpdateUserReqDto("testName","qwer","1234");
        Long userId = 1L;
        User mockUser = new User("testName","qwer","1234");
        when(userRepository.findByIdOrElseThrow(eq(userId))).thenReturn(mockUser);
        when(passwordEncoder.matches(anyString(), anyString())).thenReturn(true);

        // when
        UserResDto resultDto = userService.update(userId,dto);

        // then
        assertNotNull(resultDto);
        assertEquals("testName",resultDto.getName());
        verify(userRepository,times(1))
                .findByIdOrElseThrow(eq(userId));

    }
}