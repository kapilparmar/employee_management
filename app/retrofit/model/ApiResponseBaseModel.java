package com.kapil.employeemanagement.retrofit.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ApiResponseBaseModel implements Serializable {

  @SerializedName("httpCode")
  private int code;

  public ApiResponseBaseModel(int code) {
    this.code = code;
  }

  public int getCode() {
    return code;
  }
}
