package com.kapil.employeemanagement.model;

import com.kapil.employeemanagement.retrofit.model.EmpDetailModel;

public interface IEmployeeDetails {


    EmpDetailModel getEmpDetails();
    boolean  checkValidEmpId(String empId);

}
