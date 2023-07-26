package com.peppone.dam.like.service.impliments;

import static com.peppone.dam.exception.ErrorCode.COMMENT_NOT_FOUND;
import static com.peppone.dam.exception.ErrorCode.LIKED_COMMENT;
import static com.peppone.dam.exception.ErrorCode.LIKED_POST;
import static com.peppone.dam.exception.ErrorCode.LIKE_NOT_FOUND;
import static com.peppone.dam.exception.ErrorCode.NOT_ALLOWED;
import static com.peppone.dam.exception.ErrorCode.POST_NOT_FOUND;

import com.peppone.dam.comment.domain.CommentEntity;
import com.peppone.dam.comment.dto.ReadCommentDto;
import com.peppone.dam.comment.repository.CommentRepository;
import com.peppone.dam.exception.CustomException;
import com.peppone.dam.exception.ErrorCode;
import com.peppone.dam.like.domain.LikeEntity;
import com.peppone.dam.like.domain.LikeType;
import com.peppone.dam.like.dto.CommentCancelLikeDto;
import com.peppone.dam.like.dto.CommentLikeDto;
import com.peppone.dam.like.dto.PostCancelLikeDto;
import com.peppone.dam.like.dto.PostLikeDto;
import com.peppone.dam.like.repository.LikeRepository;
import com.peppone.dam.like.service.LikeService;
import com.peppone.dam.post.domain.PostEntity;
import com.peppone.dam.post.dto.ReadPostDto;
import com.peppone.dam.post.repository.PostRepository;
import com.peppone.dam.response.CommonResponse;
import com.peppone.dam.response.ResponseService;
import com.peppone.dam.user.domain.UserEntity;
import com.peppone.dam.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LikeServiceImpl implements LikeService {

  private final UserRepository userRepository;
  private final PostRepository postRepository;
  private final LikeRepository likeRepository;
  private final CommentRepository commentRepository;
  private final ResponseService responseService;


  @Transactional
  @Override
  public CommonResponse postLike(UserEntity user, PostLikeDto likeDto) {

    UserEntity likeUser = userRepository.findById(user.getId())
        .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

    PostEntity post = postRepository.findById(likeDto.getPostId())
        .orElseThrow(() -> new CustomException(POST_NOT_FOUND));

    if (likeRepository.existsByUserIdAndPostId(likeUser, post)) {
      throw new CustomException(LIKED_POST);
    }

    postLiker(post, likeUser, likeDto.getLikeType());

    return responseService.getSingleResponse(ReadPostDto.from(post));
  }

  @Transactional
  @Override
  public CommonResponse cancelPostLike(UserEntity user, PostCancelLikeDto postCancelLikeDto) {
    UserEntity likeUser = userRepository.findById(user.getId())
        .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

    LikeEntity like = likeRepository.findById(postCancelLikeDto.getId())
        .orElseThrow(() -> new CustomException(LIKE_NOT_FOUND));

    if (likeUser.getId() != like.getUserId().getId() && likeUser.getRole().get(0)
        .equals("ROLE_ADMIN")) {
      throw new CustomException(NOT_ALLOWED);
    }

    postLikeCanceler(like.getId());

    return responseService.getSingleResponse(ReadPostDto.from(
        postRepository.findById(postCancelLikeDto.getId())
            .orElseThrow(() -> new CustomException(POST_NOT_FOUND))));
  }

  @Transactional
  @Override
  public CommonResponse commentLike(UserEntity user, CommentLikeDto commentLikeDto) {

    UserEntity likeUser = userRepository.findById(user.getId())
        .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

    CommentEntity comment = commentRepository.findById(commentLikeDto.getCommentId())
        .orElseThrow(() -> new CustomException(COMMENT_NOT_FOUND));

    if (likeRepository.existsByUserIdAndCommentId(likeUser, comment)) {
      throw new CustomException(LIKED_COMMENT);
    }

    commentLiker(comment, likeUser, commentLikeDto.getLikeType());

    return responseService.getSingleResponse(ReadCommentDto.from(comment));
  }

  @Transactional
  @Override
  public CommonResponse cancelCommentLike(UserEntity user,
      CommentCancelLikeDto commentCancelLikeDto) {
    UserEntity likeUser = userRepository.findById(user.getId())
        .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

    LikeEntity like = likeRepository.findById(commentCancelLikeDto.getId())
        .orElseThrow(() -> new CustomException(LIKE_NOT_FOUND));

    if (likeUser.getId() != like.getUserId().getId() && likeUser.getRole().get(0)
        .equals("ROLE_ADMIN")) {
      throw new CustomException(NOT_ALLOWED);
    }

    commentLikeCanceler(like.getId());

    return responseService.getSingleResponse(ReadCommentDto.from(
        commentRepository.findById(commentCancelLikeDto.getId())
            .orElseThrow(() -> new CustomException(COMMENT_NOT_FOUND))));
  }


  private void postLiker(PostEntity post, UserEntity user, LikeType likeType) {
    if (likeType == LikeType.LIKE) {
      post.setLike(post.getLike() + 1);
    }

    if (likeType == LikeType.DISLIKE) {
      post.setDislike(post.getDislike() + 1);
    }

    postRepository.save(post);
    likeRepository.save(LikeEntity.builder()
        .userId(user)
        .postId(post)
        .likeType(likeType)
        .build());
  }

  private void postLikeCanceler(long likeId) {

    LikeEntity like = likeRepository.findById(likeId)
        .orElseThrow(() -> new CustomException(LIKE_NOT_FOUND));
    PostEntity likedPost = postRepository.findById(like.getPostId().getId())
        .orElseThrow(() -> new CustomException(POST_NOT_FOUND));
    if (like.getLikeType() == LikeType.LIKE) {
      likedPost.setLike(likedPost.getLike() - 1);
    }

    if (like.getLikeType() == LikeType.DISLIKE) {
      likedPost.setDislike(likedPost.getDislike() + 1);
    }
    postRepository.save(likedPost);
    likeRepository.delete(like);
  }


  private void commentLiker(CommentEntity comment, UserEntity user, LikeType likeType) {
    if (likeType == LikeType.LIKE) {
      comment.setLike(comment.getLike() + 1);
    }

    if (likeType == LikeType.DISLIKE) {
      comment.setDislike(comment.getDislike() + 1);
    }

    commentRepository.save(comment);
    likeRepository.save(LikeEntity.builder()
        .userId(user)
        .commentId(comment)
        .likeType(likeType)
        .build());
  }

  private void commentLikeCanceler(long likeId) {

    LikeEntity like = likeRepository.findById(likeId)
        .orElseThrow(() -> new CustomException(LIKE_NOT_FOUND));
    CommentEntity likedComment = commentRepository.findById(like.getCommentId().getId())
        .orElseThrow(() -> new CustomException(COMMENT_NOT_FOUND));
    if (like.getLikeType() == LikeType.LIKE) {
      likedComment.setLike(likedComment.getLike() - 1);
    }

    if (like.getLikeType() == LikeType.DISLIKE) {
      likedComment.setDislike(likedComment.getDislike() + 1);
    }
    commentRepository.save(likedComment);
    likeRepository.delete(like);
  }

}
