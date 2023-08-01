package com.peppone.dam.user.service;

import com.peppone.dam.user.domain.UserEntity;
import com.peppone.dam.user.dto.ChangeUserDetailDto;
import com.peppone.dam.user.dto.LoginDto;
import com.peppone.dam.user.dto.SignInDto;
import com.peppone.dam.response.CommonResponse;

public interface UserService {

  CommonResponse signIn(SignInDto signInDto);

  CommonResponse logIn(LoginDto login);

  CommonResponse signOut(String token);

  CommonResponse signInAdmin(SignInDto signIn);

  CommonResponse promoteAdmin(long id);

  CommonResponse changeUserDetail(long id, UserEntity user, ChangeUserDetailDto changeUserDetailDto);

  CommonResponse deleteUser(long id, UserEntity user);
}
