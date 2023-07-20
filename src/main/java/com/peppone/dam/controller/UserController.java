package com.peppone.dam.controller;

import com.peppone.dam.dto.LoginDto;
import com.peppone.dam.dto.SignInDto;
import com.peppone.dam.dto.SignOutDto;
import com.peppone.dam.response.CommonResponse;
import com.peppone.dam.response.ResponseService;
import com.peppone.dam.response.SingleResponse;
import com.peppone.dam.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;

  @PostMapping("/api/user/sign-in")
  public CommonResponse signIn(@RequestBody @Valid SignInDto signIn) {
    CommonResponse signInResponse = userService.signIn(signIn);
    return signInResponse;
  }

  @GetMapping("/api/user/login")
  public CommonResponse logIn(@RequestBody @Valid LoginDto login) {
    CommonResponse loginResponse = userService.logIn(login);
    return loginResponse;
  }

  @PatchMapping("/api/user/sign-out")
  public CommonResponse signOut(@RequestBody @Valid String token) {
    CommonResponse signOutResponse = userService.signOut(token);
    return signOutResponse;
  }
}
