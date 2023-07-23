package com.peppone.dam.post.domain;

import com.peppone.dam.board.domain.BoardEntity;
import com.peppone.dam.user.domain.UserEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
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
@Table(name = "post")
@Builder
public class PostEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private UserEntity userId;

  @ManyToOne
  @JoinColumn(name = "board_id")
  private BoardEntity boardId;

  private String subject;

  private String content;

  private LocalDateTime createdTime;

  private LocalDateTime modifiedTime;

  private LocalDateTime deletedTime;

  private LocalDateTime latestCommentTime;

  private long like;

  private long dislike;

  private double likeRatio;

  private boolean access;

  private long commentNumbers;

  @Enumerated(EnumType.STRING)
  private PostType postType;

}
