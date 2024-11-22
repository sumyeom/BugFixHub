package com.example.bugfixhub.service.like;

import com.example.bugfixhub.dto.likes.PostLikeResDto;
import com.example.bugfixhub.entity.like.PostLike;
import com.example.bugfixhub.entity.post.Post;
import com.example.bugfixhub.entity.user.User;
import com.example.bugfixhub.repository.like.PostLikeRepository;
import com.example.bugfixhub.repository.post.PostRepository;
import com.example.bugfixhub.repository.user.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class PostLikeServiceImpl implements PostLikeService {

    private final PostLikeRepository postLikeRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Transactional
    @Override
    public PostLikeResDto addOrCancleLike(Long postId, Long userId) {

        Post post = postRepository.findById(postId).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "없는 게시물입니다.")
        );

        User user = userRepository.findById(userId).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "사용자를 찾을 수 없습니다."));

        if (post.isDeleted()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "삭제된 게시글입니다.");
        }

        PostLike existingLike = postLikeRepository.findByPostAndUser(post, user).orElse(null);

        if (existingLike != null) {
            postLikeRepository.delete(existingLike);
            return new PostLikeResDto(existingLike);
        } else {
            PostLike newPostLike = new PostLike(user, post);
            PostLike savedPostLike = postLikeRepository.save(newPostLike);
            return new PostLikeResDto(savedPostLike);
        }
    }
}
