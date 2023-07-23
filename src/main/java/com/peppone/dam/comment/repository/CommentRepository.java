package com.peppone.dam.comment.repository;

import com.peppone.dam.comment.domain.CommentEntity;
import com.peppone.dam.post.domain.PostEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, Long> {

  Page<CommentEntity> findAllByPostId(PostEntity postId, Pageable pageable);
}
