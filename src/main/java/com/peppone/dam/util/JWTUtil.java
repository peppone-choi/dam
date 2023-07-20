package com.peppone.dam.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.RequiredArgsConstructor;
import lombok.experimental.UtilityClass;

@UtilityClass
public class JWTUtil {

  private static final String KEY = "plazadeladignidad";

  public static String getIssuer(String token) {
    String issuer = JWT.require(Algorithm.HMAC512(KEY.getBytes()))
        .build()
        .verify(token)
        .getIssuer();
    return issuer;
  }
}