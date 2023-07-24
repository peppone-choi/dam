package com.peppone.dam.user.controller;

import com.peppone.dam.response.CommonResponse;
import com.peppone.dam.user.dto.LoginDto;
import com.peppone.dam.user.dto.SignInDto;
import com.peppone.dam.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;

  @PostMapping("/api/user")
  public CommonResponse signIn(@RequestBody @Valid SignInDto signIn) {
    CommonResponse signInResponse = userService.signIn(signIn);
    return signInResponse;
  }

  @PostMapping("/api/admin")
  public CommonResponse signInAdmin(@RequestBody @Valid SignInDto signIn) {
    CommonResponse signInResponse = userService.signInAdmin(signIn);
    return signInResponse;
  }

  @DeleteMapping("/api/user")
  public CommonResponse signOut(@RequestHeader(name = "Authorization") String token) {
    CommonResponse signOutResponse = userService.signOut(token);
    return signOutResponse;
  }

  @GetMapping("/api/auth")
  public CommonResponse logIn(@RequestBody @Valid LoginDto login) {
    CommonResponse loginResponse = userService.logIn(login);
    return loginResponse;
  }
}
