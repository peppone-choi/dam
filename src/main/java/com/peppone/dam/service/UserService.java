package com.peppone.dam.service;

import com.peppone.dam.dto.SignInDto;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;

public interface UserService {

  ResponseEntity signIn(SignInDto signInDto, Errors errors);
}
