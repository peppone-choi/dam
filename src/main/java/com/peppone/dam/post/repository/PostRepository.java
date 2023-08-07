package com.peppone.dam.post.repository;

import com.peppone.dam.board.domain.BoardEntity;
import com.peppone.dam.post.domain.PostEntity;
import com.peppone.dam.post.domain.PostType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<PostEntity, Long> {
  Page<PostEntity> findAllByBoardIdAndPostTypeAndAccessTrueAndDeletedTimeIsEmpty(BoardEntity id, Pageable pageable, PostType postType);

  Page<PostEntity> findAllByBoardTypeBoardTypeMajorAndPostTypePostTypeGeneralAndAccessIsTrueAndDeletedTimeIsEmpty(Pageable pageable);
}
