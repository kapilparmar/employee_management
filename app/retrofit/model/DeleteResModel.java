package com.kapil.employeemanagement.retrofit.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DeleteResModel {

@SerializedName("success")
@Expose
private Success success;

public Success getSuccess() {
return success;
}

public void setSuccess(Success success) {
this.success = success;
}

}
