package com.peppone.dam.comment.service.impliments;

import static com.peppone.dam.exception.ErrorCode.COMMENT_NOT_FOUND;
import static com.peppone.dam.exception.ErrorCode.POST_NOT_FOUND;

import com.peppone.dam.comment.domain.CommentEntity;
import com.peppone.dam.comment.dto.CreateCommentDto;
import com.peppone.dam.comment.dto.EditCommentDto;
import com.peppone.dam.comment.dto.ReadCommentDto;
import com.peppone.dam.comment.repository.CommentRepository;
import com.peppone.dam.comment.service.CommentService;
import com.peppone.dam.exception.CustomException;
import com.peppone.dam.post.domain.PostEntity;
import com.peppone.dam.post.repository.PostRepository;
import com.peppone.dam.response.CommonResponse;
import com.peppone.dam.response.ResponseService;
import com.peppone.dam.user.domain.UserEntity;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

  private final CommentRepository commentRepository;
  private final ResponseService responseService;
  private final PostRepository postRepository;

  @Transactional
  @Override
  public CommonResponse createComment(CreateCommentDto createCommentDto, UserEntity user) {

    PostEntity post = postRepository.findById(createCommentDto.getPostId())
        .orElseThrow(() -> new CustomException(POST_NOT_FOUND));

    addCommentNumber(post);

    CommentEntity comment = CommentEntity.builder()
        .userId(user)
        .postId(post)
        .content(createCommentDto.getContent())
        .createdDate(LocalDateTime.now())
        .like(0)
        .dislike(0)
        .build();

    post.setCommentNumbers(post.getCommentNumbers() + 1);

    commentRepository.save(comment);

    postRepository.save(post);

    return responseService.getSingleResponse(ReadCommentDto.from(comment));
  }

  @Transactional
  @Override
  public CommonResponse createComment(CreateCommentDto createCommentDto, UserEntity user,
      long id) {

    if (!commentRepository.existsById(id)) {
      throw new CustomException(COMMENT_NOT_FOUND);
    }

    PostEntity post = postRepository.findById(createCommentDto.getPostId())
        .orElseThrow(() -> new CustomException(POST_NOT_FOUND));

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

    post.setCommentNumbers(post.getCommentNumbers() + 1);

    commentRepository.save(comment);

    postRepository.save(post);

    return responseService.getSingleResponse(ReadCommentDto.from(comment));
  }

  @Transactional
  @Override
  public CommonResponse editComment(EditCommentDto editCommentDto, UserEntity user, long id) {
    CommentEntity comment = commentRepository.findById(id)
        .orElseThrow(() -> new CustomException(COMMENT_NOT_FOUND));

    comment.setContent(editCommentDto.getContent());

    commentRepository.save(comment);

    return responseService.getSingleResponse(ReadCommentDto.from(comment));
  }

  @Transactional
  @Override
  public CommonResponse deleteComment(long id, UserEntity user) {
    CommentEntity comment = commentRepository.findById(id)
        .orElseThrow(() -> new CustomException(COMMENT_NOT_FOUND));


    comment.setDeleteDate(LocalDateTime.now());

    commentRepository.save(comment);

    return responseService.getSingleResponse(id + "번 게시글 삭제!");
  }

  private void addCommentNumber(PostEntity post) {
    post.setCommentNumbers(post.getCommentNumbers() + 1);
    post.setLatestCommentTime(LocalDateTime.now());
    postRepository.save(post);
  }
}
