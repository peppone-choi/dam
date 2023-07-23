package com.peppone.dam.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginDto {

  @Email(message = "이메일 양식을 지켜주세요.")
  private String userEmail;

  @NotBlank(message = "패스워드를 입력 해 주세요.")
  private String password;
}
