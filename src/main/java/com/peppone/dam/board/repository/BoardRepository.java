package com.peppone.dam.board.repository;

import com.peppone.dam.board.domain.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<BoardEntity, Long> {
  BoardEntity findByUrl(String url);

  boolean existsByName(String name);

  boolean existsByUrl(String url);



}
