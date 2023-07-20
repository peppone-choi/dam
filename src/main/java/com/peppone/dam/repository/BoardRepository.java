package com.peppone.dam.repository;

import com.peppone.dam.domain.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<BoardEntity, Long> {
  BoardEntity findById(long id);

  boolean existsByName(String name);

  boolean existsByUrl(String url);
}
