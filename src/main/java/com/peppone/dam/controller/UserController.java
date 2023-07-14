package com.peppone.dam.controller;

import com.peppone.dam.dto.SignInDto;
import com.peppone.dam.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;

  @PostMapping("/api/sign-in")
  public ResponseEntity<?> signIn(@RequestBody @Valid SignInDto signIn, Errors errors) {
    String register = userService.signIn(signIn, errors);
    return ResponseEntity.ok().body(register);
  }
}
