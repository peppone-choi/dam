package com.peppone.dam.comment.dto;

import com.peppone.dam.comment.domain.CommentEntity;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class ReadCommentDto {

  private long commentId;

  private long userId;

  private String userNickname;

  private String content;

  private LocalDateTime commentCreatedTime;

  private LocalDateTime commentModifiedTime;

  private long like;

  private long dislike;

  private long parentComment;

  public static ReadCommentDto from(CommentEntity commentEntity) {
    return ReadCommentDto.builder()
        .commentId(commentEntity.getId())
        .parentComment(commentEntity.getParentComment())
        .userId(commentEntity.getUserId().getId())
        .userNickname(commentEntity.getUserId().getNickname())
        .content(commentEntity.getContent())
        .commentCreatedTime(commentEntity.getCreatedDate())
        .commentModifiedTime(commentEntity.getModifiedDate())
        .like(commentEntity.getLike())
        .dislike(commentEntity.getDislike())
        .build();
  }

}
