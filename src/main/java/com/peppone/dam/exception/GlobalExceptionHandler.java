package com.peppone.dam.exception;

import com.peppone.dam.response.ResponseService;
import com.peppone.dam.response.SingleResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

  private final ResponseService responseService;

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public SingleResponse<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
    return responseService.getSingleResponse(e.getStatusCode());
  }
}
