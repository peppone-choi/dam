package com.peppone.dam.user.service.impliments;

import static com.peppone.dam.exception.ErrorCode.BLOCKED_USER;
import static com.peppone.dam.exception.ErrorCode.EMAIL_DUPLICATED;
import static com.peppone.dam.exception.ErrorCode.PASSWORD_NOT_MATCH;
import static com.peppone.dam.exception.ErrorCode.USER_NOT_FOUND;

import com.peppone.dam.user.domain.UserEntity;
import com.peppone.dam.user.dto.LoginDto;
import com.peppone.dam.user.dto.SignInDto;
import com.peppone.dam.user.repository.UserRepository;
import com.peppone.dam.response.CommonResponse;
import com.peppone.dam.response.ResponseService;
import com.peppone.dam.token.TokenService;
import com.peppone.dam.user.service.UserService;
import java.time.LocalDateTime;
import java.util.Collections;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final ResponseService responseService;
  private final TokenService tokenService;

  @Override
  public CommonResponse signIn(SignInDto signInDto) {

    UserEntity findUser = userRepository.findByUserEmail(signInDto.getUserEmail());

    if (findUser != null && findUser.getRemovedDate() == null) {
      return responseService.ErrorResponse(EMAIL_DUPLICATED);
    }

    if (findUser != null && findUser.getRemovedDate() != null) {
      userRepository.delete(findUser);
    }

    UserEntity user = UserEntity.builder()
        .userEmail(signInDto.getUserEmail())
        .password(passwordEncoder.encode(signInDto.getPassword()))
        .nickname(signInDto.getNickname())
        .createdDate(LocalDateTime.now())
        .role(Collections.singletonList("ROLE_USER"))
        .build();

    userRepository.save(user);

    return responseService.getSingleResponse(user);
  }

  public boolean checkPassword(String encoded, String decoded) {
    if (passwordEncoder.matches(encoded, decoded)) {
      return true;
    }
    return false;
  }

  @Override
  public CommonResponse logIn(LoginDto login) {
    UserEntity loginUser = userRepository.findByUserEmail(login.getUserEmail());

    if (loginUser == null || loginUser.getRemovedDate() != null) {
      return responseService.ErrorResponse(USER_NOT_FOUND);
    }

    if (!checkPassword(login.getPassword(), loginUser.getPassword())) {
      return responseService.ErrorResponse(PASSWORD_NOT_MATCH);
    }

    boolean isBlocked = LocalDateTime.now()
        .isBefore(
            loginUser.getBlockedDate().plusDays(loginUser.getBlockDay()));

    if (isBlocked) {
      return responseService.ErrorResponse(BLOCKED_USER);
    }

    String token = tokenService.tokenIssuer(loginUser);
    return responseService.getSingleResponse(token);
  }

  @Transactional
  @Override
  public CommonResponse signOut(String token) {
    UserEntity user = userRepository.findByUserEmail(tokenService.tokenValidation(token).getUserEmail());

    if (user == null || user.getRemovedDate() != null) {
      return responseService.ErrorResponse(USER_NOT_FOUND);
    }

    user.setRemovedDate(LocalDateTime.now());

    userRepository.save(user);

    return responseService.getSingleResponse(user.getUserEmail());
  }

}
