package com.peppone.dam.service;

import com.peppone.dam.domain.UserEntity;
import com.peppone.dam.dto.LoginDto;
import com.peppone.dam.dto.SignInDto;
import com.peppone.dam.response.CommonResponse;

public interface UserService {

  CommonResponse signIn(SignInDto signInDto);

  CommonResponse logIn(LoginDto login);

  CommonResponse signOut(String token);
}
