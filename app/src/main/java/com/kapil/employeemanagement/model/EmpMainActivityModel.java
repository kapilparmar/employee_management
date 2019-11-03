package com.kapil.employeemanagement.model;

public class EmpMainActivityModel implements IEmpMainActivity {
    private String empID;

    public String getEmpID() {
        return empID;
    }

    @Override
    public boolean checkValidEmpId(String empId) {
        if (empId!=null){
            return false;
        }
        return true;
    }


    public void setEmpID(String empID) {
        this.empID = empID;
    }


}
