package com.peppone.dam.token;

import com.peppone.dam.user.domain.UserEntity;

public interface TokenService {

  UserEntity tokenValidation(String token);

  String tokenIssuer(UserEntity user);
}
