package com.peppone.dam.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreatePostDto {

  private long boardId;

  @NotBlank(message = "제목을 입력해 주세요.")
  private String subject;

  @NotBlank(message = "내용을 입력해 주세요.")
  private String content;

}
