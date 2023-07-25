package com.peppone.dam.like.repository;

import com.peppone.dam.like.domain.LikeEntity;
import com.peppone.dam.post.domain.PostEntity;
import com.peppone.dam.user.domain.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeRepository extends JpaRepository<LikeEntity, Long> {
  boolean existsByUserIdAndPostId(UserEntity user, PostEntity post);
}
