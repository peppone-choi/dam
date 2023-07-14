package com.peppone.dam.service;

import com.peppone.dam.dto.SignInDto;
import org.springframework.validation.Errors;

public interface UserService {

  public String signIn(SignInDto signInDto, Errors errors);
}
