package com.kapil.employeemanagement.presenter;

import android.content.Context;
import android.widget.Toast;

import com.kapil.employeemanagement.R;
import com.kapil.employeemanagement.model.EmpMainActivityModel;
import com.kapil.employeemanagement.retrofit.model.ApiErrorModel;
import com.kapil.employeemanagement.retrofit.model.EmpDetailModel;
import com.kapil.employeemanagement.retrofit.network.EmpApiListener;
import com.kapil.employeemanagement.retrofit.network.EmpServerApiCalls;
import com.kapil.employeemanagement.view.IEmpSearchView;

import java.util.LinkedList;

public class MainActivityContractor implements MainActivityPresenter {

    private IEmpSearchView iEmpSearchView;
    private EmpMainActivityModel empMainActivityModel;

    public MainActivityContractor(IEmpSearchView iEmpSearchView) {
        this.iEmpSearchView = iEmpSearchView;
        empMainActivityModel = new EmpMainActivityModel();
    }


    @Override
    public void searchSingleEmp(String id , final Context context) {
       if (empMainActivityModel.checkValidEmpId(id)){
           return;
       }
      EmpServerApiCalls  empServerApiCalls = new EmpServerApiCalls(context);
        empServerApiCalls.getSingleEmpDdetails(id,new EmpApiListener<EmpDetailModel, ApiErrorModel>() {
            @Override
            public void onSuccess(EmpDetailModel object) {
                iEmpSearchView.onSearchEmpIdResult(object);
                iEmpSearchView.showProgressBar(false);
            }

            @Override
            public void onError(ApiErrorModel object) {
                iEmpSearchView.showProgressBar(false);
                Toast.makeText(context,context.getResources().getString(R.string.somthing_went_wrong),Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void searchAllEmp(final Context context) {
        EmpServerApiCalls  empServerApiCalls = new EmpServerApiCalls(context);
        empServerApiCalls.getAllEmpRecords(new EmpApiListener<LinkedList<EmpDetailModel>, ApiErrorModel>() {
            @Override
            public void onSuccess(LinkedList<EmpDetailModel> object) {
                iEmpSearchView.onSearchAllEmpResult(object);
                iEmpSearchView.showProgressBar(false);
            }

            @Override
            public void onError(ApiErrorModel object) {
                iEmpSearchView.showProgressBar(false);
                Toast.makeText(context,context.getResources().getString(R.string.somthing_went_wrong),Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void createNewAccountClicked(Context context) {
        iEmpSearchView.createNewAccount();
    }


}
