package com.peppone.dam.exception;

import com.peppone.dam.response.ErrorResponse;
import com.peppone.dam.response.ResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

  private final ResponseService responseService;

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ErrorResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
    return responseService.getErrorResponse(e.getStatusCode().value(), e.getMessage());
  }
}
