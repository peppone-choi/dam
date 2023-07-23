package com.peppone.dam.post.controller;

import com.peppone.dam.user.domain.UserEntity;
import com.peppone.dam.post.dto.CreatePostDto;
import com.peppone.dam.response.CommonResponse;
import com.peppone.dam.post.service.PostService;
import com.peppone.dam.token.TokenService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PostController {

  private final TokenService tokenService;
  private final PostService postService;


  @PostMapping(value = "/api/board/create")
  public CommonResponse createPost(@RequestHeader(name = "Authorization") String token, @Valid @RequestBody CreatePostDto createPostDto) {
    UserEntity user = tokenService.tokenValidation(token);
    CommonResponse createPostResponse = postService.createPost(user, createPostDto);
    return createPostResponse;
  }

  @GetMapping("/api/board/{id}")
  public CommonResponse readPost(@PathVariable long id) {
    CommonResponse readPostResponse = postService.readPost(id);
    return readPostResponse;
  }
  @GetMapping("/api/board/{id}/comment")
  public CommonResponse readPostComment(@PathVariable long id, Pageable pageable) {
    CommonResponse readPostCommentResponse = postService.readPostComment(id, pageable);
    return readPostCommentResponse;
  }

}
