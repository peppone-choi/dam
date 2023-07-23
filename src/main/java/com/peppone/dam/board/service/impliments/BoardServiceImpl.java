package com.peppone.dam.board.service.impliments;

import com.peppone.dam.board.domain.BoardEntity;
import com.peppone.dam.board.domain.BoardType;
import com.peppone.dam.board.dto.BoardListDto;
import com.peppone.dam.board.dto.BoardMakingDto;
import com.peppone.dam.board.repository.BoardRepository;
import com.peppone.dam.board.service.BoardService;
import com.peppone.dam.exception.ErrorCode;
import com.peppone.dam.response.CommonResponse;
import com.peppone.dam.response.ResponseService;
import com.peppone.dam.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

  private final BoardRepository boardRepository;
  private final ResponseService responseService;

  @Override
  public CommonResponse makeBoard(BoardMakingDto boardMakingDto, UserEntity user) {
    boolean boardExistByName = boardRepository.existsByName(boardMakingDto.getName());
    boolean boardExistByUrl = boardRepository.existsByUrl(boardMakingDto.getUrl());

    if (boardExistByName) {
      return responseService.ErrorResponse(ErrorCode.BOARD_NAME_DUPLICATED);
    }

    if (boardExistByUrl) {
      return responseService.ErrorResponse(ErrorCode.BOARD_URL_DUPLICATED);
    }

    if (user == null) {
      return responseService.ErrorResponse(ErrorCode.USER_NOT_FOUND);
    }

    BoardEntity makeBoard = BoardEntity.builder()
        .name(boardMakingDto.getName())
        .url(boardMakingDto.getUrl())
        .boardType(BoardType.BOARD_TYPE_MINI)
        .adminId(user)
        .build();

    boardRepository.save(makeBoard);

    return responseService.getSingleResponse(BoardListDto.from(makeBoard));
  }
}