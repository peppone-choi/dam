package com.peppone.dam.post.service;

import com.peppone.dam.user.domain.UserEntity;
import com.peppone.dam.post.dto.CreatePostDto;
import com.peppone.dam.response.CommonResponse;

public interface PostService {

  CommonResponse createPost(UserEntity user, CreatePostDto createPostDto);
}