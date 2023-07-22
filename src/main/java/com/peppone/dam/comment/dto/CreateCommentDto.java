package com.peppone.dam.comment.dto;

import com.peppone.dam.post.domain.PostEntity;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateCommentDto {

  private long postId;

  @NotBlank(message = "댓글 내용을 입력하세요")
  private String content;

}
