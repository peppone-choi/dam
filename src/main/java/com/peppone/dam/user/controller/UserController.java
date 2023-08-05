package com.peppone.dam.user.controller;

import com.peppone.dam.response.CommonResponse;
import com.peppone.dam.token.TokenService;
import com.peppone.dam.user.domain.UserEntity;
import com.peppone.dam.user.dto.ChangeUserDetailDto;
import com.peppone.dam.user.dto.LoginDto;
import com.peppone.dam.user.dto.SignInDto;
import com.peppone.dam.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

  private final TokenService tokenService;
  private final UserService userService;

  @PostMapping("/api/user")
  public CommonResponse signIn(@RequestBody @Valid SignInDto signIn) {
    return userService.signIn(signIn);
  }

  @PostMapping("/api/admin")
  public CommonResponse signInAdmin(@RequestBody @Valid SignInDto signIn) {
    return userService.signInAdmin(signIn);
  }

  @PostMapping("/api/admin/{id}")
  public CommonResponse promoteAdmin(@PathVariable long id,
      @RequestHeader(name = "Authorization") String token) {
    return userService.promoteAdmin(id);
  }

  @DeleteMapping("/api/user")
  public CommonResponse signOut(@RequestHeader(name = "Authorization") String token) {
    return userService.signOut(token);
  }

  @GetMapping("/api/auth")
  public CommonResponse logIn(@RequestBody @Valid LoginDto login) {
    return userService.logIn(login);
  }

  @PostMapping("/api/user/{id}")
  public CommonResponse changeUserDetail(
      @RequestHeader(name = "Authorization") String token,
      @PathVariable long id,
      @RequestBody @Valid ChangeUserDetailDto changeUserDetailDto) {
    UserEntity user = tokenService.tokenValidation(token);
    return userService.changeUserDetail(id, user, changeUserDetailDto);
  }

  @DeleteMapping("/api/user/{id}")
  public CommonResponse deleteUser(long id, @RequestHeader(name = "Authorization") String token) {
    UserEntity user = tokenService.tokenValidation(token);
    return userService.deleteUser(id, user);
  }
}
