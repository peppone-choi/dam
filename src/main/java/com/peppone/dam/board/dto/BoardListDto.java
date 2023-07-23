package com.peppone.dam.board.dto;

import com.peppone.dam.board.domain.BoardEntity;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BoardListDto {

  private long id;

  private String name;

  private String url;

  private String boardType;

  public static BoardListDto from(BoardEntity boardEntity) {
    return BoardListDto.builder()
        .id(boardEntity.getId())
        .name(boardEntity.getName())
        .url(boardEntity.getUrl())
        .boardType(boardEntity.getBoardType().toString())
        .build();
  }

}
