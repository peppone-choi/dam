package com.peppone.dam.post.service;

import com.peppone.dam.post.domain.OrderType;
import com.peppone.dam.post.dto.EditPostDto;
import com.peppone.dam.user.domain.UserEntity;
import com.peppone.dam.post.dto.CreatePostDto;
import com.peppone.dam.response.CommonResponse;
import org.springframework.data.domain.Pageable;

public interface PostService {

  CommonResponse createPost(UserEntity user, CreatePostDto createPostDto);

  CommonResponse readPost(long id);

  CommonResponse readPostComment(long id, long page, long size, Pageable pageable);

  CommonResponse editPost(long id, UserEntity user, EditPostDto editPostDto);

  CommonResponse deletePost(long id, UserEntity user);
}
