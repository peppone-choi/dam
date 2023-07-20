package com.peppone.dam.controller;

import com.peppone.dam.domain.UserEntity;
import com.peppone.dam.dto.BoardMakingDto;
import com.peppone.dam.response.CommonResponse;
import com.peppone.dam.service.BoardService;
import com.peppone.dam.service.TokenService;
import com.peppone.dam.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BoardController {

  private final BoardService boardService;
  private final UserService userService;
  private final TokenService tokenService;


  @GetMapping("/api/board/make")
  public CommonResponse makeBoard(@Valid @RequestBody BoardMakingDto boardMakingDto,
      @RequestHeader("Authorization") String token) {
    UserEntity user = tokenService.tokenValidation(token);
    CommonResponse makeBoardResponse = boardService.makeBoard(boardMakingDto, user);
    return makeBoardResponse;
  }

}
