package com.peppone.dam.board.controller;

import com.peppone.dam.board.domain.BoardType;
import com.peppone.dam.board.dto.BoardMakingDto;
import com.peppone.dam.board.service.BoardService;
import com.peppone.dam.response.CommonResponse;
import com.peppone.dam.token.TokenService;
import com.peppone.dam.user.domain.UserEntity;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BoardController {

  private final BoardService boardService;
  private final TokenService tokenService;


  @PostMapping("/api/board/make")
  public CommonResponse makeBoard(@Valid @RequestBody BoardMakingDto boardMakingDto,
      @RequestHeader("Authorization") String token) {
    UserEntity user = tokenService.tokenValidation(token);
    return boardService.makeBoard(boardMakingDto, user);
  }

  @GetMapping("/api/board/list")
  public CommonResponse getBoardList() {
    return boardService.getBoardList();
  }


  @GetMapping("/api/board/list/{type}")
  public CommonResponse boardListByType(@PathVariable String type) {

    BoardType boardType = BoardType.BOARD_TYPE_MINI;

    if (type.equals("major")) {
      boardType = BoardType.BOARD_TYPE_MAJOR;
    }

    if (type.equals("minor")) {
      boardType = BoardType.BOARD_TYPE_MINOR;
    }

    return boardService.getBoardListByType(boardType);
  }

}