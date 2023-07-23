package com.peppone.dam.post.dto;

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
}
