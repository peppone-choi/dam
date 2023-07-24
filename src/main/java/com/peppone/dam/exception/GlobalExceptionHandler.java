package com.peppone.dam.exception;

import com.peppone.dam.response.ErrorResponse;
import com.peppone.dam.response.ResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

  private final ResponseService responseService;

  @ExceptionHandler({CustomException.class})
  protected ErrorResponse handleCustomException(CustomException e) {
    return responseService.ErrorResponse(e);
  }

  @ExceptionHandler({Exception.class})
  protected ErrorResponse handleServerException(Exception e) {
    return responseService.ErrorResponse(e);
  }

}
