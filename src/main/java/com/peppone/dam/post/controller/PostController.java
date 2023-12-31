package com.peppone.dam.post.controller;

import com.peppone.dam.post.dto.CreatePostDto;
import com.peppone.dam.post.dto.EditPostDto;
import com.peppone.dam.post.service.PostService;
import com.peppone.dam.response.CommonResponse;
import com.peppone.dam.token.TokenService;
import com.peppone.dam.user.domain.UserEntity;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PostController {

  private final TokenService tokenService;
  private final PostService postService;


  @PostMapping(value = "/api/post")
  public CommonResponse createPost(@RequestHeader(name = "Authorization") String token,
      @Valid @RequestBody CreatePostDto createPostDto) {
    UserEntity user = tokenService.tokenValidation(token);
    return postService.createPost(user, createPostDto);
  }

  @GetMapping("/api/post/{id}")
  public CommonResponse readPost(@PathVariable long id) {
    return postService.readPost(id);
  }

  @GetMapping("/api/post/{id}/comment")
  public CommonResponse readPostComment(@PathVariable long id,
      @RequestParam(name = "page", defaultValue = "0") long page,
      @RequestParam(name = "size", defaultValue = "5") long size,
      Pageable pageable) {
    return postService.readPostComment(id, page, size, pageable);
  }

  @PatchMapping("/api/post/{id}")
  public CommonResponse editPost(@PathVariable long id,
      @RequestHeader(name = "Authorization") String token,
      @Valid @RequestBody EditPostDto editPostDto) {
    UserEntity user = tokenService.tokenValidation(token);
    return postService.editPost(id, user, editPostDto);
  }

  @DeleteMapping("/api/post/{id}")
  public CommonResponse deletePost(@PathVariable long id,
      @RequestHeader(name = "Authorization") String token) {
    UserEntity user = tokenService.tokenValidation(token);
    return postService.deletePost(id, user);
  }

  @PatchMapping("/api/post/{id}/access/allow")
  public CommonResponse postAllowAccess(@PathVariable long id,
      @RequestHeader(name = "Authorization") String token) {
    UserEntity user = tokenService.tokenValidation(token);
    return postService.postAllowAccess(id, user);
  }

  @PatchMapping("/api/post/{id}/access/deny")
  public CommonResponse postDenyAccess(@PathVariable long id,
      @RequestHeader(name = "Authorization") String token) {
    UserEntity user = tokenService.tokenValidation(token);
    return postService.postDenyAccess(id, user);
  }

}
