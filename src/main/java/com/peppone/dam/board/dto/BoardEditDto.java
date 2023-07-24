package com.peppone.dam.board.dto;

import com.peppone.dam.board.domain.BoardType;
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
public class BoardEditDto {

  @NotBlank(message = "게시판 번호는 필수사항입니다.")
  private long id;

  private String name;

  private String url;

  private BoardType boardType;
}
