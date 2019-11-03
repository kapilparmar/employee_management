package com.kapil.employeemanagement.model;

import com.kapil.employeemanagement.retrofit.model.EmpDetailModel;

public class EmployeeDetailsModel implements IEmployeeDetails {
    private EmpDetailModel empDetailModel;

    public EmployeeDetailsModel() {

    }

    public  void setEmpDetailModel(EmpDetailModel empDetailModel){
        this.empDetailModel = empDetailModel;
    }
    @Override
    public EmpDetailModel getEmpDetails() {
        return empDetailModel;
    }

    @Override
    public boolean checkValidEmpId(String empId) {
        if (empId!=null){
            return false;
        }
        return true;
    }
}
