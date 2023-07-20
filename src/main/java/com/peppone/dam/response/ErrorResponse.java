package com.peppone.dam.response;

import com.peppone.dam.exception.ErrorCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ErrorResponse extends CommonResponse {

  public ErrorResponse(ErrorCode errorCode) {
    this.code = errorCode.getErrorCode();
    this.message = errorCode.getMessage();
    this.success = false;
  }

  public ErrorResponse(HttpStatus httpStatus) {
    this.code = httpStatus.value();
    this.message = httpStatus.name();
    this.success = false;
  }
}
