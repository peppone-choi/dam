package com.peppone.dam.comment.controller;

import com.peppone.dam.comment.dto.CreateCommentDto;
import com.peppone.dam.comment.dto.EditCommentDto;
import com.peppone.dam.comment.service.CommentService;
import com.peppone.dam.response.CommonResponse;
import com.peppone.dam.token.TokenService;
import com.peppone.dam.user.domain.UserEntity;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CommentController {

  private final TokenService tokenService;
  private final CommentService commentService;

  @PostMapping("/api/comment")
  CommonResponse createComment(@RequestHeader(name = "Authorization") String token,
      @RequestBody @Valid CreateCommentDto createCommentDto) {
    UserEntity user = tokenService.tokenValidation(token);
    return commentService.createComment(createCommentDto, user);
  }

  @PostMapping("/api/comment/{id}")
  CommonResponse createCommentOnComment(@RequestHeader(name = "Authorization") String token,
      @RequestBody @Valid CreateCommentDto createCommentDto,
      @PathVariable long id) {
    UserEntity user = tokenService.tokenValidation(token);
    return commentService.createComment(createCommentDto, user, id);
  }

  @PatchMapping("/api/comment/{id}")
  CommonResponse editComment(@RequestHeader(name = "Authorization") String token,
      @PathVariable long id,
      @RequestBody @Valid EditCommentDto editCommentDto) {
    UserEntity user = tokenService.tokenValidation(token);
    return commentService.editComment(editCommentDto, user, id);
  }

}
