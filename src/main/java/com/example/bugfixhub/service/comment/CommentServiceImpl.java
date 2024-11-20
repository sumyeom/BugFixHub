package com.example.bugfixhub.service.comment;


import com.example.bugfixhub.dto.comments.CommentReqDto;
import com.example.bugfixhub.dto.comments.CommentResDto;
import com.example.bugfixhub.entity.comment.Comment;
import com.example.bugfixhub.entity.post.Post;
import com.example.bugfixhub.entity.user.User;
import com.example.bugfixhub.repository.comment.CommentRepository;
import com.example.bugfixhub.repository.post.PostRepository;
import com.example.bugfixhub.repository.user.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Override
    public CommentResDto createComment(Long postId, Long userId ,CommentReqDto commentReqDto) {

        Post post = postRepository.findById(postId).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "게시글을 찾을 수 없습니다."));

        User user = userRepository.findById(userId).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "사용자를 찾을 수 없습니다."));

        //댓글 생성
        Comment comment = new Comment();
        comment.setContents(commentReqDto.getContents());
        comment.setPost(post);
        comment.setUser(user);

        commentRepository.save(comment);

        return new CommentResDto(comment);
    }

    //댓글 수정
    @Override
    @Transactional
    public CommentResDto updateComment(Long commentId, Long userId, CommentReqDto commentReqDto) {
        Comment comment = commentRepository.findByIdAndDeletedFalse(commentId);
        if (comment == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "댓글을 찾을 수 없습니다.");
        }

        if (!comment.getUser().getId().equals(userId)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "본인이 작성한 댓글만 수정할 수 있습니다.");
        }

        comment.setContents(commentReqDto.getContents());
        commentRepository.save(comment);

        return new CommentResDto(comment);
    }

    @Override
    @Transactional
    public void deleteComment(Long commentId, Long userId) {
        Comment comment = commentRepository.findByIdAndDeletedFalse(commentId);

        if (comment == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "댓글을 찾을 수 없습니다.");
        }

        if (!comment.getUser().getId().equals(userId)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "본인이 작성한 댓글만 삭제할 수 있습니다.");
        }

        comment.setDeleted(true);
        commentRepository.save(comment);
    }
}