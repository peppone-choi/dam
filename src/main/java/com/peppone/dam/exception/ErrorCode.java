package com.peppone.dam.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

  /*
   * 비밀번호 틀림.
   */
  PASSWORD_NOT_MATCH(10000, "비밀번호가 틀렸습니다."),

  /*
   * 유저를 찾지 못함
   */
  USER_NOT_FOUND(10001, "해당 유저가 존재하지 않습니다."),

  /*
   * 이메일 중복
   */
  EMAIL_DUPLICATED(10002, "해당 이메일이 이미 존재 합니다."),

  /*
   * 차단된 유저
   */
  BLOCKED_USER(10003, "해당 유저는 차단 되었습니다."),

  /*
   * 게시판 이름 중복
   */
  BOARD_NAME_DUPLICATED(10010, "해당 이름의 게시판이 이미 존재합니다."),

  /*
   * 게시판 ID(URL) 중복
   */
  BOARD_URL_DUPLICATED(10011, "해당 ID의 게시판이 이미 존재합니다."),

  /*
   * 해당 게시판이 존재하지 않음
   */
  BOARD_NOT_FOUND(10012, "해당 게시판이 존재하지 않습니다."),

  /*
   * 해당 게시글이 존재하지 않음
   */
  POST_NOT_FOUND(10020, "해당 게시글이 존재하지 않습니다."),

  /*
   * 해당 댓글이 존재하지 않음
   */
  COMMENT_NOT_FOUND(10030, "해당 댓글이 존재하지 않습니다."),

  /*
   * 접근 제한 된 게시물임
   */
  POST_ACCESS_IS_DENIED(10040, "접근 제한된 게시물입니다."),

  /*
   * 권한이 없음
   */
  NOT_ALLOWED(10050, "권한이 없습니다."),

  /*
   * 이미 좋아요나 싫어요를 누름
   */
  LIKED_POST(10060, "이미 추천 / 비추천을 누른 게시물입니다."),

  /*
   * 추천 / 비추천을 찾을 수 없음.
   */
  LIKE_NOT_FOUND(10061, "추천 / 비추천을 찾을 수 없습니다.");

  private final int errorCode;
  private final String message;

}
