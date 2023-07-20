package com.peppone.dam.controller;

import com.peppone.dam.dto.SignInDto;
import com.peppone.dam.dto.SignOutDto;
import com.peppone.dam.response.CommonResponse;
import com.peppone.dam.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;

  @PostMapping("/api/sign-in")
  public CommonResponse signIn(@RequestBody @Valid SignInDto signIn, boolean emailCheck) {
    CommonResponse signInResponse = userService.signIn(signIn);
    return signInResponse;
  }

  @PatchMapping("/api/sign-out")
  public CommonResponse signOut(@RequestBody @Valid SignOutDto signOut) {
    CommonResponse signOutResponse = userService.signOut(signOut);
    return signOutResponse;
  }
}
