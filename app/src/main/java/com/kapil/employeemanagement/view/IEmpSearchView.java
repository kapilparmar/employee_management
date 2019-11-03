package com.kapil.employeemanagement.view;

import com.kapil.employeemanagement.retrofit.model.EmpDetailModel;

import java.util.LinkedList;

public interface IEmpSearchView {
    void onSearchEmpIdResult(EmpDetailModel empDetailModel);
    void onSearchAllEmpResult(LinkedList<EmpDetailModel> empDetailModel);
    void createNewAccount();
    void showProgressBar(boolean show);
}
