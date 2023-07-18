package com.peppone.dam.service;

import com.peppone.dam.dto.SignInDto;
import com.peppone.dam.dto.SignOutDto;
import com.peppone.dam.response.CommonResponse;

public interface UserService {

  CommonResponse signIn(SignInDto signInDto);

  CommonResponse signOut(SignOutDto signOut);
}
