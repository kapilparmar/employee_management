package com.kapil.employeemanagement.view;

import com.kapil.employeemanagement.retrofit.model.EmpDetailModel;

public interface IEmpAccountView {
    void setEmpDetails(EmpDetailModel empDetailModel);
    void onAccountCreated(EmpDetailModel empDetailModel);
    void onDetailsUpdated(EmpDetailModel empDetailModel);
    void setProgressBarVisibility(boolean show);
}
