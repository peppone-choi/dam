package com.peppone.dam.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

  /*
   * 비밀번호 틀림.
   */
  PASSWORD_NOT_MATCH(0000, "비밀번호가 틀렸습니다."),

  /*
   * 유저를 찾지 못함
   */
  USER_NOT_FOUND(0001, "해당 유저가 존재하지 않습니다."),

  /*
   * 이메일 중복
   */
  EMAIL_DUPLICATED(0002, "해당 이메일이 이미 존재 합니다."),

  /*
   * 게시판 이름 중복
   */
  BOARD_NAME_DUPLICATED(0010, "해당 이름의 게시판이 이미 존재합니다."),


  /*
   * 게시판 ID(URL) 중복
   */
  BOARD_URL_DUPLICATED(0011, "해당 ID의 게시판이 이미 존재합니다.");

  private final int errorCode;
  private final String message;

}
