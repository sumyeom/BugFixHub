package com.example.bugfixhub.service.post;

import com.example.bugfixhub.dto.post.*;
import com.example.bugfixhub.entity.post.Post;
import com.example.bugfixhub.entity.user.User;
import com.example.bugfixhub.repository.post.PostRepository;
import com.example.bugfixhub.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Override
    public PostResDto create(Long userId, PostReqDto postReqDto) {
        Post post = new Post(postReqDto.getTitle(), postReqDto.getContents(), postReqDto.getType());
        User user = userRepository.findByIdOrElseThrow(userId);

        if (post.getTitle() == null || post.getContents() == null || post.getType() == null) {
            String errorMessage = inputErrorMessage(post);

            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, errorMessage + "을 입력해 주세요.");
        }

        if (!(post.getType().equals("info") || post.getType().equals("ask"))) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "잘못된 타입입니다.");
        }

        post.setUser(user);
        postRepository.save(post);

        return new PostResDto(post);
    }

    @Override
    public void delete(Long id, Long userId) {
        Post findPost = postRepository.findByIdOrThrow(id);
        User user = userRepository.findByIdOrElseThrow(userId);

        if (!user.getId().equals(findPost.getUser().getId())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "본인이 쓴 게시글만 삭제할 수 있습니다.");
        }

        postRepository.delete(findPost);
    }

    private static String inputErrorMessage(Post post) {
        Map<String, String> nullCheckMap = new HashMap<>();
        String errorMessage = "";

        nullCheckMap.put("제목", post.getTitle());
        nullCheckMap.put("내용", post.getContents());
        nullCheckMap.put("타입", post.getType());

        for (String key : nullCheckMap.keySet()) {
            if (nullCheckMap.get(key) == null) {
                if (!errorMessage.isEmpty()) {
                    errorMessage += ", ";
                }

                errorMessage += key;
            }
        }
        return errorMessage;
    }
}
