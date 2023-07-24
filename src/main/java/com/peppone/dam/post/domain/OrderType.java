package com.peppone.dam.post.domain;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum OrderType {
  ORDER_TYPE_ID("id"),
  ORDER_TYPE_COMMENT_NUMBER("comment_numbers"),
  ORDER_TYPE_LATEST_COMMENT_TIME("latest_comment_time"),
  ORDER_TYPE_LIKE_RATIO("like_ratio");

  String orderType;
}
