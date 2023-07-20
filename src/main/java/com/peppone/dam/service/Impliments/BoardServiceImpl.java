package com.peppone.dam.service.Impliments;

import com.peppone.dam.domain.BoardEntity;
import com.peppone.dam.domain.BoardType;
import com.peppone.dam.domain.UserEntity;
import com.peppone.dam.dto.BoardMakingDto;
import com.peppone.dam.exception.ErrorCode;
import com.peppone.dam.repository.BoardRepository;
import com.peppone.dam.repository.UserRepository;
import com.peppone.dam.response.CommonResponse;
import com.peppone.dam.response.ResponseService;
import com.peppone.dam.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

  private final BoardRepository boardRepository;
  private final ResponseService responseService;
  private final UserRepository userRepository;


  @Override
  public CommonResponse makeBoard(BoardMakingDto boardMakingDto) {
    boolean boardExistByName = boardRepository.existsByName(boardMakingDto.getName());
    boolean boardExistByUrl = boardRepository.existsByUrl(boardMakingDto.getUrl());

    UserEntity admin = userRepository.findById(userRepository.findById(
        boardMakingDto.getBoardMakerID()));

    if (boardExistByName) {
      return responseService.ErrorResponse(ErrorCode.BOARD_NAME_DUPLICATED);
    }

    if (boardExistByUrl) {
      return responseService.ErrorResponse(ErrorCode.BOARD_URL_DUPLICATED);
    }

    if (admin == null) {
      return responseService.ErrorResponse(ErrorCode.USER_NOT_FOUND);
    }

    BoardEntity makeBoard = BoardEntity.builder()
        .name(boardMakingDto.getName())
        .url(boardMakingDto.getUrl())
        .boardType(BoardType.BOARD_TYPE_MINI)
        .userEntity(admin)
        .build();

    return responseService.getSingleResponse(makeBoard);
  }
}