package com.peppone.dam.dto;

import com.peppone.dam.domain.UserEntity;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
