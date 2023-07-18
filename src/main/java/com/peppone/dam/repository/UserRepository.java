package com.peppone.dam.repository;

import com.peppone.dam.domain.UserEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

  UserEntity findByUserEmail(String email);

  List<UserEntity> findAll();
}
