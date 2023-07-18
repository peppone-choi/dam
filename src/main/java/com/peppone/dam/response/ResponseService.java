package com.peppone.dam.response;

import java.util.List;
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

  public ErrorResponse getErrorResponse(int code, String message) {
    ErrorResponse errorResponse = new ErrorResponse();
    errorResponse.success = false;
    errorResponse.code = code;
    errorResponse.message = message;

    return errorResponse;
  }

  void setSuccessResponse(CommonResponse response) {
    response.code = 0;
    response.success = true;
    response.message = "SUCCESS";
  }

}
