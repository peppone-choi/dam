package com.peppone.dam.user.service.impliments;

import static com.peppone.dam.exception.ErrorCode.BLOCKED_USER;
import static com.peppone.dam.exception.ErrorCode.EMAIL_DUPLICATED;
import static com.peppone.dam.exception.ErrorCode.PASSWORD_NOT_MATCH;
import static com.peppone.dam.exception.ErrorCode.USER_NOT_FOUND;

import com.peppone.dam.exception.CustomException;
import com.peppone.dam.response.CommonResponse;
import com.peppone.dam.response.ResponseService;
import com.peppone.dam.token.TokenService;
import com.peppone.dam.user.domain.UserEntity;
import com.peppone.dam.user.dto.LoginDto;
import com.peppone.dam.user.dto.SignInDto;
import com.peppone.dam.user.dto.UserInfoDto;
import com.peppone.dam.user.repository.UserRepository;
import com.peppone.dam.user.service.UserService;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
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

    if (userRepository.countByUserEmailAndRemovedDateIsNull(signInDto.getUserEmail()) > 0) {
      throw new CustomException(EMAIL_DUPLICATED);
    }

    UserEntity user = UserEntity.builder()
        .userEmail(signInDto.getUserEmail())
        .password(passwordEncoder.encode(signInDto.getPassword()))
        .nickname(signInDto.getNickname())
        .createdDate(LocalDateTime.now())
        .role(Collections.singletonList("ROLE_USER"))
        .build();

    userRepository.save(user);

    return responseService.getSingleResponse(UserInfoDto.from(user));
  }

  @Override
  public CommonResponse signInAdmin(SignInDto signIn) {

    if (userRepository.countByUserEmailAndRemovedDateIsNull(signIn.getUserEmail()) > 0) {
      throw new CustomException(EMAIL_DUPLICATED);
    }

    String[] adminRole = {"ROLE_ADMIN", "ROLE_USER"};

    UserEntity user = UserEntity.builder()
        .userEmail(signIn.getUserEmail())
        .password(passwordEncoder.encode(signIn.getPassword()))
        .nickname(signIn.getNickname())
        .createdDate(LocalDateTime.now())
        .role(Arrays.stream(adminRole).toList())
        .build();

    userRepository.save(user);

    return responseService.getSingleResponse(UserInfoDto.from(user));
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
    boolean isBlocked = false;

    if (loginUser == null || loginUser.getRemovedDate() != null) {
      throw new CustomException(USER_NOT_FOUND);
    }

    if (!checkPassword(login.getPassword(), loginUser.getPassword())) {
      throw new CustomException(PASSWORD_NOT_MATCH);
    }

    if (loginUser.getBlockedDate() != null) {
      isBlocked = LocalDateTime.now()
          .isBefore(loginUser.getBlockedDate().plusDays(loginUser.getBlockDay()));
    }

    if (isBlocked) {
      throw new CustomException(BLOCKED_USER);
    }

    String token = tokenService.tokenIssuer(loginUser);
    return responseService.getSingleResponse(token);
  }

  @Transactional
  @Override
  public CommonResponse signOut(String token) {
    UserEntity user = userRepository.findByUserEmail(
        tokenService.tokenValidation(token).getUserEmail());

    if (user == null || user.getRemovedDate() != null) {
      throw new CustomException(USER_NOT_FOUND);
    }

    user.setRemovedDate(LocalDateTime.now());

    userRepository.save(user);

    return responseService.getSingleResponse(user.getUserEmail());
  }


}
