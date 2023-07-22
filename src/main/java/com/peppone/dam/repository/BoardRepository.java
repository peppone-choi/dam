package com.peppone.dam.repository;

import com.peppone.dam.domain.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<BoardEntity, Long> {
  BoardEntity findByUrl(String url);

  boolean existsByName(String name);

  boolean existsByUrl(String url);



}
