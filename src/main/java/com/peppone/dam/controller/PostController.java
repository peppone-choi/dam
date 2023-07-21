package com.peppone.dam.controller;

import com.peppone.dam.domain.UserEntity;
import com.peppone.dam.dto.CreatePostDto;
import com.peppone.dam.response.CommonResponse;
import com.peppone.dam.service.PostService;
import com.peppone.dam.service.TokenService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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

}
