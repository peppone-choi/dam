package com.peppone.dam.comment.domain;

import com.peppone.dam.post.domain.PostEntity;
import com.peppone.dam.user.domain.UserEntity;
import jakarta.persistence.Entity;
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
@Table(name = "comment")
@Builder
public class CommentEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private UserEntity userId;

  @ManyToOne
  @JoinColumn(name = "post_id")
  private PostEntity postId;

  private String content;

  private LocalDateTime createdDate;

  private LocalDateTime modifiedDate;

  private LocalDateTime deleteDate;

  private long parentComment;

  private long like;

  private long dislike;

}
