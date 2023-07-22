package com.peppone.dam.response;

import com.peppone.dam.exception.ErrorCode;
import lombok.Getter;

@Getter
public class ErrorResponse extends CommonResponse {

  public ErrorResponse(ErrorCode errorCode) {
    this.code = errorCode.getErrorCode();
    this.message = errorCode.getMessage();
    this.success = false;
  }

  public ErrorResponse(Exception e) {
    this.code = e.getMessage().indexOf(0);
    this.message = e.getLocalizedMessage();
    this.success = false;
  }

}
