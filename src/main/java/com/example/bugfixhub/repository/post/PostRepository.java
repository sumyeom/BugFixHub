package com.example.bugfixhub.repository.post;

import com.example.bugfixhub.entity.post.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public interface PostRepository extends JpaRepository<Post, Long> {
    default Post findByIdOrThrow(Long id) {
        return findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "없는(또는 삭제된) 게시글 입니다."));
    }

    Page<Post> findByUserIdAndDeletedFalse(Long userId, Pageable pageable);
}
