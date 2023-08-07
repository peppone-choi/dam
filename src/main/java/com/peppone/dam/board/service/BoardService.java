package com.peppone.dam.board.service;

import com.peppone.dam.board.domain.BoardType;
import com.peppone.dam.board.dto.BoardEditDto;
import com.peppone.dam.board.dto.BoardMakingDto;
import com.peppone.dam.post.domain.OrderType;
import com.peppone.dam.response.CommonResponse;
import com.peppone.dam.user.domain.UserEntity;
import org.springframework.data.domain.Pageable;

public interface BoardService {

  CommonResponse makeBoard(BoardMakingDto boardMakingDto, UserEntity user);

  CommonResponse getBoardList();

  CommonResponse getBoardListByType(BoardType boardType);

  CommonResponse getBoardPostList(String id, long page, long size, OrderType order,
      boolean orderDirection, Pageable pageable);

  CommonResponse getBoardPinnedPostList(String id);

  CommonResponse getBoardNoticePostList(String id);

  CommonResponse editBoard(BoardEditDto boardEditDto, UserEntity user);

  CommonResponse getMainPostList(long page, OrderType order, boolean orderDirection, Pageable pageable);
}