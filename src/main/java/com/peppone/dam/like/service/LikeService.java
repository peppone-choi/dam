package com.peppone.dam.like.service;

import com.peppone.dam.like.dto.CommentCancelLikeDto;
import com.peppone.dam.like.dto.CommentLikeDto;
import com.peppone.dam.like.dto.PostCancelLikeDto;
import com.peppone.dam.like.dto.PostLikeDto;
import com.peppone.dam.response.CommonResponse;
import com.peppone.dam.user.domain.UserEntity;

public interface LikeService {

  CommonResponse postLike(UserEntity user, PostLikeDto likeDto);

  CommonResponse cancelPostLike(UserEntity user, PostCancelLikeDto postCancelLikeDto);

  CommonResponse commentLike(UserEntity user, CommentLikeDto commentLikeDto);

  CommonResponse cancelCommentLike(UserEntity user, CommentCancelLikeDto commentCancelLikeDto);
}
