package com.peppone.dam.user.dto;

import com.peppone.dam.user.domain.UserEntity;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserInfoDto {

  private String userEmail;
  private String nickname;
  private LocalDateTime createdDate;
  private List<String> role;

  public static UserInfoDto from(UserEntity userEntity) {
    return UserInfoDto.builder()
        .userEmail(userEntity.getUserEmail())
        .nickname(userEntity.getNickname())
        .role(userEntity.getRole())
        .createdDate(userEntity.getCreatedDate())
        .build();
  }
}
