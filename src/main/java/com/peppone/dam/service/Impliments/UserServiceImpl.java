package com.peppone.dam.service.Impliments;

import com.peppone.dam.domain.UserEntity;
import com.peppone.dam.dto.SignInDto;
import com.peppone.dam.repository.UserRepository;
import com.peppone.dam.service.UserService;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  @Override
  public ResponseEntity signIn(SignInDto signInDto, Errors errors) {

    if (errors.hasErrors()) {
      return ResponseEntity.internalServerError().body("회원가입에 문제가 있습니다.");
    }

    if (userRepository.findByUserEmail(signInDto.getUserEmail()) != null) {
      return ResponseEntity.badRequest().body("이미 가입된 이메일입니다.");
    }

    UserEntity user = UserEntity.builder()
        .userEmail(signInDto.getUserEmail())
        .password(passwordEncoder.encode(signInDto.getPassword()))
        .nickname(signInDto.getNickname())
        .createdDate(LocalDateTime.now())
        .role("USER")
        .build();

    userRepository.save(user);

    return ResponseEntity.ok().body("회원 가입이 완료되었습니다.");
  }
}
