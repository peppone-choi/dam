package com.peppone.dam.comment.service;

import com.peppone.dam.comment.dto.CreateCommentDto;
import com.peppone.dam.comment.dto.EditCommentDto;
import com.peppone.dam.response.CommonResponse;
import com.peppone.dam.user.domain.UserEntity;

public interface CommentService {

  CommonResponse createComment(CreateCommentDto createCommentDto, UserEntity user);

  CommonResponse createComment(CreateCommentDto createCommentDto, UserEntity user, long id);

  CommonResponse editComment(EditCommentDto editCommentDto, UserEntity user, long id);

  CommonResponse deleteComment(long id, UserEntity user);
}
