package com.peppone.dam.like.dto;

import com.peppone.dam.like.domain.LikeType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostLikeDto {

  private long postId;
  private LikeType likeType;
}
