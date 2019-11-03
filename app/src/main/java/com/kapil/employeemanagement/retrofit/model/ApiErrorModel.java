package com.kapil.employeemanagement.retrofit.model;


import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ApiErrorModel extends ApiResponseBaseModel implements Serializable {

  @SerializedName("message")
  private String msg;

  public ApiErrorModel(String msg) {
    super(-1);
    this.msg = msg;
  }

  public ApiErrorModel(String msg, int code) {
    super(code);
    this.msg = msg;
  }

  public String getMsg() {
    return msg;
  }

  public static ApiErrorModel buildErrorObject(Throwable t) {
    String errMsg;
    if (t.getMessage() != null) {
      errMsg = t.getMessage();
    } else {
      errMsg = "ERROR";
    }
    return new ApiErrorModel(errMsg);
  }
}