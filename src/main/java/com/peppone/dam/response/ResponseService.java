package com.peppone.dam.response;

import com.peppone.dam.exception.ErrorCode;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class ResponseService {

  public <T> SingleResponse<T> getSingleResponse(T data) {
    SingleResponse singleResponse = new SingleResponse();
    singleResponse.data = data;
    setSuccessResponse(singleResponse);

    return singleResponse;
  }

  public <T> ListResponse<T> getListResponse(List<T> dataList) {
    ListResponse listResponse = new ListResponse();
    listResponse.dataList = dataList;
    setSuccessResponse(listResponse);

    return listResponse;
  }

  void setSuccessResponse(CommonResponse response) {
    response.code = 20000;
    response.success = true;
    response.message = "SUCCESS";
  }

  public CommonResponse ErrorResponse(ErrorCode errorCode) {
    CommonResponse response = new CommonResponse();
    response.success = false;
    response.code = errorCode.getErrorCode();
    response.message = errorCode.getMessage();
    return response;
  }

  public CommonResponse ErrorResponse(HttpStatus httpStatus) {
    CommonResponse response = new CommonResponse();
    response.success = false;
    response.code = httpStatus.value();
    response.message = httpStatus.name();
    return response;
  }

}
