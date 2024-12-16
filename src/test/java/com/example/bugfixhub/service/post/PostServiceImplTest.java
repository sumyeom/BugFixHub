package com.example.bugfixhub.service.post;

import com.example.bugfixhub.dto.post.GetAllPostResDataDto;
import com.example.bugfixhub.dto.post.GetAllPostResDto;
import com.example.bugfixhub.entity.comment.Comment;
import com.example.bugfixhub.entity.like.PostLike;
import com.example.bugfixhub.entity.post.Post;
import com.example.bugfixhub.entity.user.User;
import com.example.bugfixhub.enums.PostType;
import com.example.bugfixhub.repository.comment.CommentRepository;
import com.example.bugfixhub.repository.post.PostRepository;
import com.example.bugfixhub.repository.user.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PostServiceImplTest {
    @Mock
    private PostRepository postRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private CommentRepository commentRepository;

    @InjectMocks
     private PostServiceImpl postService;

    // Post, User 객체 생성 및 설정
    Post TEST_POST = new Post("testTitle", "testContent", PostType.INFO);
    User TEST_USER = new User("name", "test@gmail.com", "1234");

    @Test
    @DisplayName("type이 follow로 성공")
    void getAllPosts_follow_success() {
        //given
        String type = "follow";
        String title = "title";
        int page = 0;
        int limit = 10;
        String filter = "createdAt";
        LocalDateTime startDate = LocalDateTime.now().minusDays(7);
        LocalDateTime endDate = LocalDateTime.now().plusDays(7);
        Long userId = 1L;

        Pageable pageable = PageRequest.of(page, limit, Sort.by(filter).descending());

        Page<Object[]> mockResultPage = new PageImpl<>(List.<Object[]>of(
                new Object[]{1L, 1L, "testUser", "testTitle", "info", 5, Timestamp.valueOf(startDate), Timestamp.valueOf(endDate), 10, 1}
        ), pageable, 1);

        // when
        when(postRepository.findPostsWithPagination(userId, title, filter, startDate, endDate, pageable))
                .thenReturn(mockResultPage);

        //then
        GetAllPostResDto resultDto = postService.getAllPosts(type, title,page,limit,filter,startDate,endDate,userId);

        assertNotNull(resultDto);
        assertEquals(1, resultDto.getData().size());
        assertEquals(1L, resultDto.getData().get(0).getId());
        assertEquals("testTitle", resultDto.getData().get(0).getTitle());
        verify(postRepository,times(1))
                .findPostsWithPagination(userId, title,filter,startDate,endDate,pageable);
    }

    @Test
    @DisplayName("type이 info로 성공")
    void getAllPosts_info_success() {
        // given
        String type = "info";
        String title = "title";
        int page = 0;
        int limit = 10;
        String filter = "createdAt";
        LocalDateTime startDate = LocalDateTime.now().minusDays(7);
        LocalDateTime endDate = LocalDateTime.now().plusDays(7);
        Long userId = 1L;

        Pageable pageable = PageRequest.of(page, limit, Sort.by(filter).descending());

        // Post, User 객체 생성 및 설정
        Post post = new Post("testTitle", "testContent", PostType.INFO);
        User user = new User("name", "test@gmail.com", "1234");
        //post.setId(1L); // ID 설정
        post.setUser(user);
        post.updateDelete(false);

        Page<Post> mockPage = new PageImpl<>(List.of(post), pageable, 1);

        // Stub 설정
        when(postRepository.findByCreatedAtBetweenAndDeletedFalse(
                startDate, endDate, pageable))
                .thenReturn(mockPage);
        when(postRepository.findByTypeAndTitleLikeAndCreatedAtBetweenAndDeletedFalse(
                eq(PostType.INFO), eq("%title%"), eq(startDate), eq(endDate), eq(pageable)))
                .thenReturn(mockPage);

        // when
        GetAllPostResDto resultDto = postService.getAllPosts(type, title, page, limit, filter, startDate, endDate, userId);

        // then
        assertNotNull(resultDto);
        assertEquals(1, resultDto.getData().size());
        //assertEquals(1L, resultDto.getData().get(0).getId());
        assertEquals("testTitle", resultDto.getData().get(0).getTitle());
        assertEquals("info", resultDto.getData().get(0).getType());
            verify(postRepository, times(1))
                .findByCreatedAtBetweenAndDeletedFalse(eq(startDate), eq(endDate), eq(pageable));
        verify(postRepository, times(1))
                .findByTypeAndTitleLikeAndCreatedAtBetweenAndDeletedFalse(
                        eq(PostType.INFO), eq("%title%"), eq(startDate), eq(endDate), eq(pageable));
    }

    @Test
    @DisplayName("type invalid")
    void getAllPosts_type_invalid() {
        // given
        String type = "invalid";
        String title = "title";
        int page = 0;
        int limit = 10;
        String filter = "createdAt";
        LocalDateTime startDate = LocalDateTime.now().minusDays(7);
        LocalDateTime endDate = LocalDateTime.now().plusDays(7);
        Long userId = 1L;

        Pageable pageable = PageRequest.of(page, limit, Sort.by(filter).descending());

        TEST_POST.setUser(TEST_USER);
        TEST_POST.updateDelete(false);

        Page<Post> mockPage = new PageImpl<>(List.of(TEST_POST), pageable, 1);

        when(postRepository.findByCreatedAtBetweenAndDeletedFalse(eq(startDate),eq(endDate),eq(pageable))).thenReturn(mockPage);

        //when
        //GetAllPostResDto resultDto = postService.getAllPosts(type, title, page, limit, filter, startDate, endDate, userId);
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, ()->
                postService.getAllPosts(type, title, page, limit, filter, startDate, endDate, userId));
        //then
        assertEquals(HttpStatus.BAD_REQUEST,exception.getStatusCode());

    }


    @Test
    @DisplayName("type이 null일 때")
    void getAllPosts_type_null() {
        //given
        String type = null;
        String title = "title";
        int page = 0;
        int limit = 10;
        String filter = "createdAt";
        LocalDateTime startDate = LocalDateTime.now().minusDays(7);
        LocalDateTime endDate = LocalDateTime.now().plusDays(7);
        Long userId = 1L;

        Pageable pageable = PageRequest.of(page, limit, Sort.by(filter).descending());

        // Post, User 객체 생성 및 설정
        Post post = new Post("testTitle", "testContent", PostType.INFO);
        User user = new User("name", "test@gmail.com", "1234");
        //post.setId(1L); // ID 설정
        post.setUser(user);
        post.updateDelete(false);

        Page<Post> mockPage = new PageImpl<>(List.of(post), pageable, 1);


        when(postRepository.findByCreatedAtBetweenAndDeletedFalse(
                startDate, endDate, pageable))
                .thenReturn(mockPage);
        when(postRepository.findByTitleLikeAndCreatedAtBetweenAndDeletedFalse(
                eq("%title%"),
                eq(startDate),
                eq(endDate),
                eq(pageable))).
                thenReturn(mockPage);

        // when
        GetAllPostResDto resultDto = postService.getAllPosts(type, title, page, limit, filter, startDate, endDate, userId);

        // then
        assertNotNull(resultDto);
        assertEquals(1, resultDto.getData().size());
        //assertEquals(1L, resultDto.getData().get(0).getId());
        assertEquals("testTitle", resultDto.getData().get(0).getTitle());
        verify(postRepository, times(1))
                .findByCreatedAtBetweenAndDeletedFalse(eq(startDate), eq(endDate), eq(pageable));
        verify(postRepository, times(1))
                .findByTitleLikeAndCreatedAtBetweenAndDeletedFalse(
                        eq("%title%"),
                        eq(startDate),
                        eq(endDate),
                        eq(pageable));
    }

    @Test
    @DisplayName("")
    void getAllPosts_PageOutOfBounds() {
        // given
        String type = "info";
        String title = "title";
        int page = 100;
        int limit = 10;
        String filter = "createdAt";
        LocalDateTime startDate = LocalDateTime.now().minusDays(7);
        LocalDateTime endDate = LocalDateTime.now().plusDays(7);
        Long userId = 1L;

        TEST_POST.setUser(TEST_USER);
        TEST_POST.updateDelete(false);

        Page<Post> mockPage = new PageImpl<>(Collections.emptyList(), PageRequest.of(0,limit), 10);

        when(postRepository.findByTypeAndTitleLikeAndCreatedAtBetweenAndDeletedFalse(
                eq(PostType.INFO), eq("%title%"), eq(startDate), eq(endDate), any()))
                .thenReturn(mockPage);

        //when
        //GetAllPostResDto resultDto = postService.getAllPosts(type, title, page, limit, filter, startDate, endDate, userId);
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, ()->
                postService.getAllPosts(type, title, page, limit, filter, startDate, endDate, userId));
        //then
        assertEquals(HttpStatus.NOT_FOUND,exception.getStatusCode());

    }
}

