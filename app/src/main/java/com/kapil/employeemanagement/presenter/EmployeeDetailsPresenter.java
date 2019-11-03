package com.kapil.employeemanagement.presenter;

import android.content.Context;

import com.kapil.employeemanagement.retrofit.model.EmpDetailModel;
import com.kapil.employeemanagement.retrofit.model.PostEmployeeDetails;

public interface EmployeeDetailsPresenter {
    void updateEmp(String empId, PostEmployeeDetails empDetailModel, Context context);

    void createEmpAccount(PostEmployeeDetails empDetailModel, Context context);

    void deleteEmpAccount(String empId, Context context);

    void displayEmpDetails(EmpDetailModel empDetailModel);

    boolean setProgressBarVisibility(Boolean show);

}
