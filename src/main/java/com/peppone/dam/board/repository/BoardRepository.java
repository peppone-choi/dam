package com.peppone.dam.board.repository;

import com.peppone.dam.board.domain.BoardEntity;
import com.peppone.dam.board.domain.BoardType;
import com.peppone.dam.board.dto.BoardListDto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<BoardEntity, Long> {

  BoardEntity findByUrl(String url);

  boolean existsByName(String name);

  boolean existsByUrl(String url);

  List<BoardEntity> findAllByBoardType(BoardType boardType);
}
