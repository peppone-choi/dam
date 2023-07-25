package com.peppone.dam.like.controller;

import com.peppone.dam.like.dto.CommentCancelLikeDto;
import com.peppone.dam.like.dto.CommentLikeDto;
import com.peppone.dam.like.dto.PostCancelLikeDto;
import com.peppone.dam.like.dto.PostLikeDto;
import com.peppone.dam.like.service.LikeService;
import com.peppone.dam.response.CommonResponse;
import com.peppone.dam.token.TokenService;
import com.peppone.dam.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LikeController {

  private final LikeService likeService;
  private final TokenService tokenService;

  @PostMapping("/api/like/post")
  private CommonResponse postLike(@RequestHeader(name = "Authorization") String token,
      @RequestBody PostLikeDto postLikeDto) {
    UserEntity user = tokenService.tokenValidation(token);
    return likeService.postLike(user, postLikeDto);
  }

  @DeleteMapping("/api/like/post")
  private CommonResponse cancelPostLike(@RequestHeader(name = "Authorization") String token,
      @RequestBody PostCancelLikeDto postCancelLikeDto) {
    UserEntity user = tokenService.tokenValidation(token);
    return likeService.cancelPostLike(user, postCancelLikeDto);
  }

  @PostMapping("/api/like/post")
  private CommonResponse commentLike(@RequestHeader(name = "Authorization") String token,
      @RequestBody CommentLikeDto commentLikeDto) {
    UserEntity user = tokenService.tokenValidation(token);
    return likeService.commentLike(user, commentLikeDto);
  }

  @DeleteMapping("/api/like/post")
  private CommonResponse cancelCommentLike(@RequestHeader(name = "Authorization") String token,
      @RequestBody CommentCancelLikeDto commentCancelLikeDto) {
    UserEntity user = tokenService.tokenValidation(token);
    return likeService.cancelCommentLike(user, commentCancelLikeDto);
  }
}
