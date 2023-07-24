package com.peppone.dam.comment.controller;

import com.peppone.dam.comment.dto.CreateCommentDto;
import com.peppone.dam.comment.service.CommentService;
import com.peppone.dam.response.CommonResponse;
import com.peppone.dam.token.TokenService;
import com.peppone.dam.user.domain.UserEntity;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CommentController {

  private final TokenService tokenService;
  private final CommentService commentService;

  @PostMapping("/api/comment")
  CommonResponse createComment(@RequestHeader("Authorization") String token,
      @RequestBody @Valid CreateCommentDto createCommentDto) {
    UserEntity user = tokenService.tokenValidation(token);
    CommonResponse createCommentResponse = commentService.createComment(createCommentDto, user);
    return createCommentResponse;
  }

  @PutMapping("/api/comment/{id}")
  CommonResponse createCommentonComment(@RequestHeader("Authorization") String token,
      @RequestBody @Valid CreateCommentDto createCommentDto,
      @PathVariable long id) {
    UserEntity user = tokenService.tokenValidation(token);
    CommonResponse createCommentResponse = commentService.createComment(createCommentDto, user, id);
    return createCommentResponse;
  }

}
