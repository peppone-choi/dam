package com.peppone.dam.user.service;

import com.peppone.dam.user.dto.LoginDto;
import com.peppone.dam.user.dto.SignInDto;
import com.peppone.dam.response.CommonResponse;

public interface UserService {

  CommonResponse signIn(SignInDto signInDto);

  CommonResponse logIn(LoginDto login);

  CommonResponse signOut(String token);
}
