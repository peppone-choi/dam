package com.peppone.dam.response;

import com.peppone.dam.exception.ErrorCode;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class ResponseService {

  public <T> SingleResponse<T> getSingleResponse(T data) {
    SingleResponse singleResponse = new SingleResponse(data);
    setSuccessResponse(singleResponse);

    return singleResponse;
  }

  public <T> ListResponse<T> getListResponse(List<T> dataList) {
    ListResponse listResponse = new ListResponse(dataList);
    setSuccessResponse(listResponse);

    return listResponse;
  }

  void setSuccessResponse(CommonResponse response) {
    response.code = 20000;
    response.success = true;
    response.message = "SUCCESS";
  }

  public CommonResponse ErrorResponse(ErrorCode errorCode) {
    ErrorResponse response = new ErrorResponse(errorCode);
    return response;
  }

  public CommonResponse ErrorResponse(HttpStatus httpStatus) {
    ErrorResponse response = new ErrorResponse(httpStatus);
    return response;
  }

}
