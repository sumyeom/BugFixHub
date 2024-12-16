package com.example.bugfixhub.service.friend;

import com.example.bugfixhub.dto.friend.FriendUserResDto;
import com.example.bugfixhub.entity.user.User;
import com.example.bugfixhub.repository.friend.FriendRepository;
import com.example.bugfixhub.repository.user.UserRepository;
import org.antlr.v4.runtime.atn.LL1Analyzer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FriendServiceImplTest {

    @Mock
    private FriendRepository friendRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private FriendServiceImpl friendService;

    @Test
    @DisplayName("모든 친구 리스트 찾기")
    void findAllFriends_success() {
        //given
        Long userId = 1L;
        User mockUser = new User();
        when(userRepository.findByIdOrElseThrow(userId)).thenReturn(mockUser);

        // when
        List<FriendUserResDto> resultDto = friendService.findAllFriends(userId);

        // then
        assertNotNull(resultDto);

    }
}