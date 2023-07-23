package com.peppone.dam.post.dto;

import com.peppone.dam.post.domain.PostEntity;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReadPostDto {
  private String subject;
  private String content;
  private long userId;
  private LocalDateTime createdTime;
  private long like;
  private long dislike;
  private long commentNumbers;

  public static ReadPostDto from(PostEntity postEntity) {
    return ReadPostDto.builder()
        .subject(postEntity.getSubject())
        .content(postEntity.getContent())
        .userId(postEntity.getUserId().getId())
        .createdTime(postEntity.getCreatedTime())
        .like(postEntity.getLike())
        .dislike(postEntity.getDislike())
        .commentNumbers(postEntity.getCommentNumbers())
        .build();
  }

}
