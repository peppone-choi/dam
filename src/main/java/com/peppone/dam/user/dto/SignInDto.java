package com.peppone.dam.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SignInDto {

  @Email(message = "이메일을 입력 해 주세요.")
  private String userEmail;

  @NotNull
  @NotBlank(message = "패스워드가 입력되지 않았습니다.")
  private String password;

  @NotNull
  @NotBlank(message = "닉네임이 입력되지 않았습니다.")
  private String nickname;
}
