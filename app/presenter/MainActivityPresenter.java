package com.kapil.employeemanagement.presenter;

import android.content.Context;

import com.kapil.employeemanagement.retrofit.model.EmpDetailModel;

public interface MainActivityPresenter {
    void searchSingleEmp(String id, Context context);
    void searchAllEmp(Context context);
    void createNewAccountClicked(Context context);

}
