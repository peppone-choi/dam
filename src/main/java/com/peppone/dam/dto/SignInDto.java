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
public class SignInDto {

  @Email
  private String userEmail;

  @NotNull
  @NotBlank
  private String password;

  @NotNull
  @NotBlank
  private String nickname;
}
