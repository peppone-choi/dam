package com.peppone.dam.service;

import com.peppone.dam.domain.UserEntity;
import com.peppone.dam.dto.CreatePostDto;
import com.peppone.dam.response.CommonResponse;

public interface PostService {

  CommonResponse createPost(UserEntity user, CreatePostDto createPostDto);
}
