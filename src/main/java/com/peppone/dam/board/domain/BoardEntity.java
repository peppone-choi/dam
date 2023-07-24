package com.peppone.dam.board.domain;

import com.peppone.dam.user.domain.UserEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "board")
@Builder
public class BoardEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  private String name;

  private String url;

  @Enumerated(EnumType.STRING)
  private BoardType boardType;

  @ManyToOne
  @JoinColumn(name = "admin_id")
  private UserEntity adminId;

  @OneToMany
  @JoinColumn(name = "sub_admin_id")
  private List<UserEntity> subAdminId;
}
