package com.peppone.dam.response;

import lombok.Getter;

@Getter
public class SingleResponse<T> extends CommonResponse {

  T data;

  public SingleResponse(T data) {
    this.data = data;
  }
}
