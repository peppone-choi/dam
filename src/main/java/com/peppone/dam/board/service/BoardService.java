package com.peppone.dam.board.service;

import com.peppone.dam.board.dto.BoardMakingDto;
import com.peppone.dam.response.CommonResponse;
import com.peppone.dam.user.domain.UserEntity;

public interface BoardService {

  CommonResponse makeBoard(BoardMakingDto boardMakingDto, UserEntity user);
}