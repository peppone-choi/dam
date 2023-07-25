package com.peppone.dam.like.domain;

import com.peppone.dam.comment.domain.CommentEntity;
import com.peppone.dam.post.domain.PostEntity;
import com.peppone.dam.user.domain.UserEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
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
@Table(name = "like")
@Builder
public class LikeEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private UserEntity userId;

  @ManyToOne
  @JoinColumn(name = "post_id")
  private PostEntity postId;

  @ManyToOne
  @JoinColumn(name = "comment_id")
  private CommentEntity commentId;

  private LikeType likeType;

}
