package com.kapil.employeemanagement.presenter;

import android.content.Context;
import android.widget.Toast;

import com.kapil.employeemanagement.R;
import com.kapil.employeemanagement.model.EmployeeDetailsModel;
import com.kapil.employeemanagement.retrofit.model.ApiErrorModel;
import com.kapil.employeemanagement.retrofit.model.EmpDetailModel;
import com.kapil.employeemanagement.retrofit.model.PostEmployeeDetails;
import com.kapil.employeemanagement.retrofit.network.EmpApiListener;
import com.kapil.employeemanagement.retrofit.network.EmpServerApiCalls;
import com.kapil.employeemanagement.view.IEmpAccountView;

public class EmployeeDetailsContractor implements EmployeeDetailsPresenter {

    private IEmpAccountView iEmpSearchView;
    private EmployeeDetailsModel empMainActivityModel;

    public EmployeeDetailsContractor(IEmpAccountView iEmpSearchView) {
        this.iEmpSearchView = iEmpSearchView;
        empMainActivityModel = new EmployeeDetailsModel();
    }


    @Override
    public void updateEmp(String empId, PostEmployeeDetails empDetailModel, final Context context) {
        if (empMainActivityModel.checkValidEmpId(empId)){
            return;
        }
        EmpServerApiCalls  empServerApiCalls = new EmpServerApiCalls(context);
        empServerApiCalls.updateEmpDdetails(empId,empDetailModel,new EmpApiListener<EmpDetailModel, ApiErrorModel>() {
            @Override
            public void onSuccess(EmpDetailModel object) {
                iEmpSearchView.onDetailsUpdated(object);
            }
            @Override
            public void onError(ApiErrorModel object) {
                Toast.makeText(context, R.string.somthing_went_wrong,Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void createEmpAccount(final PostEmployeeDetails postEmployeeDetails, final Context context) {
        EmpServerApiCalls  empServerApiCalls = new EmpServerApiCalls(context);

        empServerApiCalls.createEmpAccount(postEmployeeDetails,new EmpApiListener<EmpDetailModel, ApiErrorModel>() {
            @Override
            public void onSuccess(EmpDetailModel object) {
                iEmpSearchView.onAccountCreated(object);
            }
            @Override
            public void onError(ApiErrorModel object) {

                Toast.makeText(context, R.string.somthing_went_wrong,Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void deleteEmpAccount(String empId, final Context context) {
    }

    @Override
    public void displayEmpDetails(EmpDetailModel empDetailModel) {
        iEmpSearchView.setEmpDetails(empDetailModel);
    }

    @Override
    public boolean setProgressBarVisibility(Boolean show) {
        return show;
    }
}
