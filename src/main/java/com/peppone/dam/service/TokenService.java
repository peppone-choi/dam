package com.peppone.dam.service;

import com.peppone.dam.domain.UserEntity;

public interface TokenService {
  UserEntity tokenValidation(String token);
  String tokenIssuer(UserEntity user);
}
