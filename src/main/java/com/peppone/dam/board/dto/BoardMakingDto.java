package com.peppone.dam.board.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BoardMakingDto {

  @NotBlank(message = "게시판 이름은 필수사항입니다.")
  private String name;

  @NotBlank(message = "게시판 id는 필수사항입니다.")
  private String url;
}
