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

  void setSuccessResponse(CommonResponse response) {
    response.code = 20000;
    response.success = true;
    response.message = "SUCCESS";
  }

  public CommonResponse ErrorResponse(int code, String message) {
    CommonResponse response = new CommonResponse();
    response.success = false;
    response.code = code;
    response.message = message;
    return response;
  }

}
