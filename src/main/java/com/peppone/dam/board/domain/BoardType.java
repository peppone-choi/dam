package com.peppone.dam.board.domain;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum BoardType {
  BOARD_TYPE_MINI("mini"),
  BOARD_TYPE_MINOR("minor"),
  BOARD_TYPE_MAJOR("major");

  String string;
}
