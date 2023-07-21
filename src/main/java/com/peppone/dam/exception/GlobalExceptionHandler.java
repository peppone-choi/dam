package com.peppone.dam.exception;

import com.peppone.dam.response.ErrorResponse;
import com.peppone.dam.response.ResponseService;
import com.peppone.dam.response.SingleResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler extends RuntimeException {

  private final ResponseService responseService;

  @ExceptionHandler(Exception.class)
  public ErrorResponse Exception(Exception e) {
    return responseService.ErrorResponse(e);
  }


}
