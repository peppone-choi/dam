package com.peppone.dam.user.repository;

import com.peppone.dam.user.domain.UserEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

  UserEntity findByUserEmail(String email);

  List<UserEntity> findAll();

  long countByUserEmailAndRemovedDateIsNull(String userEmail);
}
