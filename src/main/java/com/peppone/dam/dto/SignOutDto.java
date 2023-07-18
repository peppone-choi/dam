package com.peppone.dam.dto;

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
public class SignOutDto {

  @Email(message = "이메일을 입력 해 주세요.")
  private String userEmail;

  @NotNull(message = "패스워드를 입력 해 주세요.")
  @NotBlank(message = "패스워드를 입력 해 주세요.")
  private String password;
}
