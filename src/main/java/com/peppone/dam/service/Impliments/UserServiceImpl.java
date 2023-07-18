package com.peppone.dam.service.Impliments;

import static com.peppone.dam.exception.ErrorCode.EMAIL_DUPLICATED;
import static com.peppone.dam.exception.ErrorCode.PASSWORD_NOT_MATCH;
import static com.peppone.dam.exception.ErrorCode.USER_NOT_FOUND;

import com.peppone.dam.domain.UserEntity;
import com.peppone.dam.dto.SignInDto;
import com.peppone.dam.dto.SignOutDto;
import com.peppone.dam.repository.UserRepository;
import com.peppone.dam.response.CommonResponse;
import com.peppone.dam.response.ResponseService;
import com.peppone.dam.service.UserService;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final ResponseService responseService;

  @Override
  public CommonResponse signIn(SignInDto signInDto) {

    UserEntity findUser = userRepository.findByUserEmail(signInDto.getUserEmail());

    if (findUser.getRemovedDate() != null) {
      userRepository.delete(findUser);
    }

    if (findUser != null) {
      responseService.getErrorResponse(EMAIL_DUPLICATED.getErrorCode(),
          EMAIL_DUPLICATED.getMessage());
    }

    UserEntity user = UserEntity.builder()
        .userEmail(signInDto.getUserEmail())
        .password(passwordEncoder.encode(signInDto.getPassword()))
        .nickname(signInDto.getNickname())
        .createdDate(LocalDateTime.now())
        .role("USER")
        .build();

    userRepository.save(user);

    return responseService.getSingleResponse("회원 가입이 완료 되었습니다.");
  }

  @Override
  public CommonResponse signOut(SignOutDto signOut) {
    UserEntity user = userRepository.findByUserEmail(signOut.getUserEmail());

    if (user == null) {
      return responseService.getErrorResponse(USER_NOT_FOUND.getErrorCode(),
          USER_NOT_FOUND.getMessage());
    }

    if (!passwordEncoder.matches(signOut.getPassword(), user.getPassword())) {
      return responseService.getErrorResponse(PASSWORD_NOT_MATCH.getErrorCode(),
          PASSWORD_NOT_MATCH.getMessage());
    }

    user.setRemovedDate(LocalDateTime.now());

    userRepository.save(user);

    return responseService.getSingleResponse("회원 삭제가 완료 되었습니다.");
  }
}
