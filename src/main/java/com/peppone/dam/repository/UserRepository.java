package com.peppone.dam.repository;

import com.peppone.dam.domain.UserEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

  UserEntity findByUserEmail(String email);

  List<UserEntity> findAll();

  UserEntity findById(Optional<UserEntity> byId);
}
