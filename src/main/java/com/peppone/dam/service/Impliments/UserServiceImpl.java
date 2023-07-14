package com.peppone.dam.service.Impliments;

import com.peppone.dam.domain.UserEntity;
import com.peppone.dam.dto.SignInDto;
import com.peppone.dam.repository.UserRepository;
import com.peppone.dam.service.UserService;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;

  @Override
  public String signIn(SignInDto signInDto, Errors errors) {

    if (errors.hasErrors()) {
      return "회원 가입에 문제가 있습니다";
    }

    if (userRepository.findByUserEmail(signInDto.getUserEmail()) != null) {
      return "이미 가입된 이메일입니다.";
    }

    UserEntity user = UserEntity.builder()
        .userEmail(signInDto.getUserEmail())
        .password(signInDto.getPassword())
        .nickname(signInDto.getNickname())
        .createdDate(LocalDateTime.now())
        .role("USER")
        .build();

    userRepository.save(user);

    return "회원 가입이 완료되었습니다.";
  }
}
