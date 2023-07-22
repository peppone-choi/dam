package com.peppone.dam.user.dto;

import com.peppone.dam.user.domain.UserEntity;
import java.util.List;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class LoginResponseDto {

  private String userEmail;

  private List<String> role;

  public LoginResponseDto(UserEntity user) {
    this.userEmail = user.getUserEmail();
    this.role = user.getRole();
  }
}
