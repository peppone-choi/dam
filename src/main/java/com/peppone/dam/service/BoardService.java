package com.peppone.dam.service;

import com.peppone.dam.domain.UserEntity;
import com.peppone.dam.dto.BoardMakingDto;
import com.peppone.dam.response.CommonResponse;

public interface BoardService {

  CommonResponse makeBoard(BoardMakingDto boardMakingDto, UserEntity user);
}