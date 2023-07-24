package com.peppone.dam.board.controller;

import com.peppone.dam.board.domain.BoardType;
import com.peppone.dam.board.dto.BoardEditDto;
import com.peppone.dam.board.dto.BoardMakingDto;
import com.peppone.dam.board.service.BoardService;
import com.peppone.dam.post.domain.OrderType;
import com.peppone.dam.response.CommonResponse;
import com.peppone.dam.token.TokenService;
import com.peppone.dam.user.domain.UserEntity;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
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

  @PostMapping("/api/board")
  public CommonResponse makeBoard(@Valid @RequestBody BoardMakingDto boardMakingDto,
      @RequestHeader("Authorization") String token) {
    UserEntity user = tokenService.tokenValidation(token);
    return boardService.makeBoard(boardMakingDto, user);
  }

  @PatchMapping("/api/board/")
  public CommonResponse editBoard(@RequestBody BoardEditDto boardEditDto,
      @RequestHeader("Authorization") String token) {
    UserEntity user = tokenService.tokenValidation(token);
    return boardService.editBoard(boardEditDto, user);
  }

  @GetMapping("/api/board")
  public CommonResponse getBoardList() {
    return boardService.getBoardList();
  }

  @GetMapping("/api/board/{type}")
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

  @GetMapping("/api/board/{id}")
  public CommonResponse getBoardPostList(@PathVariable String id,
      @RequestParam(name = "page", defaultValue = "0") long page,
      @RequestParam(name = "size", defaultValue = "10") long size,
      @RequestParam(name = "order", defaultValue = "id") OrderType order,
      @RequestParam(name= "order_direction", defaultValue = "true") boolean orderDirection,
      Pageable pageable) {
    return boardService.getBoardPostList(id, page, size, order, orderDirection, pageable);
  }

  @GetMapping("/api/board/{id}/pinned")
  public CommonResponse getBoardPinnedPostList(@PathVariable String id) {
    return boardService.getBoardPinnedPostList(id);
  }

  @GetMapping("/api/board/{id}/notice")
  public CommonResponse getBoardNoticePostList(@PathVariable String id) {
    return boardService.getBoardNoticePostList(id);
  }

}