package com.peppone.dam.token.Impliments;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.peppone.dam.user.domain.UserEntity;
import com.peppone.dam.user.repository.UserRepository;
import com.peppone.dam.token.TokenService;
import com.peppone.dam.util.JWTUtil;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class TokenServiceImpl implements TokenService {
  private final UserRepository userRepository;

  @Override
  //토큰 발행(로그인 시)
  public String tokenIssuer(UserEntity user) {
    Date expiration = java.sql.Timestamp.valueOf(
        LocalDateTime.now().plusHours(2)
    );

    String token = JWT.create()
        .withExpiresAt(expiration)
        .withClaim("user_id",user.getId())
        .withSubject(user.getUserEmail())
        .withIssuer(user.getUserEmail())
        .sign(Algorithm.HMAC512("plazadeladignidad".getBytes()));

    return token;
  }

  @Override
  //토큰 받아 인증
  public UserEntity tokenValidation(String token) {
    String email = null;
    email = JWTUtil.getIssuer(token);
    Optional<UserEntity> optionalUser = Optional.ofNullable(userRepository.findByUserEmail(email));
    return optionalUser.get();
  }

}
