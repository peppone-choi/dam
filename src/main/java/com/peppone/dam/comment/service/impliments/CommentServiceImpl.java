package com.peppone.dam.comment.service.impliments;

import static com.peppone.dam.exception.ErrorCode.COMMENT_NOT_FOUND;
import static com.peppone.dam.exception.ErrorCode.POST_NOT_FOUND;

import com.peppone.dam.comment.domain.CommentEntity;
import com.peppone.dam.comment.dto.CreateCommentDto;
import com.peppone.dam.comment.dto.ReadCommentDto;
import com.peppone.dam.comment.repository.CommentRepository;
import com.peppone.dam.comment.service.CommentService;
import com.peppone.dam.post.domain.PostEntity;
import com.peppone.dam.post.repository.PostRepository;
import com.peppone.dam.response.CommonResponse;
import com.peppone.dam.response.ResponseService;
import com.peppone.dam.user.domain.UserEntity;
import java.time.LocalDateTime;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

  private final CommentRepository commentRepository;
  private final ResponseService responseService;
  private final PostRepository postRepository;

  @Override
  public CommonResponse createComment(CreateCommentDto createCommentDto, UserEntity user) {

    if (!postRepository.existsById(createCommentDto.getPostId())) {
      responseService.ErrorResponse(POST_NOT_FOUND);
    }

    PostEntity post = postRepository.findById(createCommentDto.getPostId()).orElseThrow();

    addCommentNumber(post);

    CommentEntity comment = CommentEntity.builder()
        .userId(user)
        .postId(post)
        .content(createCommentDto.getContent())
        .createdDate(LocalDateTime.now())
        .like(0)
        .dislike(0)
        .build();

    commentRepository.save(comment);

    return responseService.getSingleResponse(ReadCommentDto.from(comment));
  }

  @Override
  public CommonResponse createComment(CreateCommentDto createCommentDto, UserEntity user,
      long id) {
    if (!postRepository.existsById(createCommentDto.getPostId())) {
      responseService.ErrorResponse(POST_NOT_FOUND);
    }

    if (!commentRepository.existsById(id)) {
      responseService.ErrorResponse(COMMENT_NOT_FOUND);
    }

    PostEntity post = postRepository.findById(createCommentDto.getPostId()).orElseThrow();

    addCommentNumber(post);

    CommentEntity comment = CommentEntity.builder()
        .userId(user)
        .postId(post)
        .content(createCommentDto.getContent())
        .createdDate(LocalDateTime.now())
        .parentComment(id)
        .like(0)
        .dislike(0)
        .build();

    commentRepository.save(comment);

    return responseService.getSingleResponse(ReadCommentDto.from(comment));
  }

  private void addCommentNumber(PostEntity post) {
    post.setCommentNumbers(post.getCommentNumbers() + 1);
    post.setLatestCommentTime(LocalDateTime.now());
    postRepository.save(post);
  }
}
