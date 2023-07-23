package com.peppone.dam.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class JwtProvider {

  private String secretKey = "llshlllshlllshlllshlasdfsdafrhwethbsafewaewtygesjhfgjgxzedfagsdgjyrejxs";

  private Long tokenValidMillisecond = 60 * 60 * 1000L; // 1시간

  private final UserDetailsService userDetailsService;

  @PostConstruct
  protected void init() {
    secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
  }

  // Jwt 생성

  public String createToken(String userPk, List<String> roles) {
    // claims에 User PK (id) 넣어 줌
    Claims claims = Jwts.claims().setSubject(userPk);
    claims.put("roles", roles);

    // 생성날짜, 만료 날짜.
    Date now = new Date();

    return Jwts.builder().setClaims(claims).setIssuedAt(now)
        .setExpiration(new Date(now.getTime() + tokenValidMillisecond))
        .signWith(SignatureAlgorithm.HS512, secretKey).compact();
  }

  // Jwt로 인증정보 조회
  public Authentication getAuthentication(String token) {
    UserDetails userDetails = userDetailsService.loadUserByUsername(this.getUserPK(token));
    return new UsernamePasswordAuthenticationToken(userDetails, "",
        userDetails.getAuthorities());
  }

  // jwt에서 회원 구분(id, PK) 추출
  public String getUserPK(String token) {
    return Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token).getBody()
        .getSubject();
  }

  // 토큰의 Header에서 토큰 파싱
  public String resolveToken(HttpServletRequest request) {
    return request.getHeader("X-AUTH-TOKEN");
  }

  //JWT 유효성, 만료일자 확인
  public boolean validationToken(String token) {
    try {
      Jws<Claims> claimsJws = Jwts.parserBuilder().setSigningKey(secretKey).build()
          .parseClaimsJws(token);

      return !claimsJws.getBody().getExpiration().before(new Date()); // 만료일자가 현재보다 이전일 경우
    } catch (Exception e) {
      return false;
    }
  }
}
