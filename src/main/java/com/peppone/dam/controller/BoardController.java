package com.peppone.dam.controller;

import com.peppone.dam.dto.BoardMakingDto;
import com.peppone.dam.response.CommonResponse;
import com.peppone.dam.service.BoardService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BoardController {

  private final BoardService boardService;

  @GetMapping("/api/board/make")
  public CommonResponse makeBoard(@Valid @RequestBody BoardMakingDto boardMakingDto) {
    CommonResponse makeBoardResponse = boardService.makeBoard(boardMakingDto);
    return makeBoardResponse;
  }

}
