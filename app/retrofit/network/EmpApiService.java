package com.kapil.employeemanagement.retrofit.network;

import com.kapil.employeemanagement.retrofit.model.DeleteResModel;
import com.kapil.employeemanagement.retrofit.model.EmpDetailModel;
import com.kapil.employeemanagement.retrofit.model.PostEmployeeDetails;

import java.util.LinkedList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface EmpApiService {

    /**
     * Get all employees data
     * @param
     * @return
     */

    @GET("employees")
    Call<LinkedList<EmpDetailModel>> getAllEmployeeData();

    /**
     * Get single employee data based on empID
     * @param empID
     * @return
     */
    @GET("employee/{id}")
    Call<EmpDetailModel> getSingleEmployeeData(@Path("id") String empID);

    /**
     * create new employee records
     * @param empDetailModel
     * @return
     */
    @POST("create")
    Call<EmpDetailModel> createEmployeeData(@Body PostEmployeeDetails empDetailModel);


    /**
     * update employee records
     * @param empID
     * @param empDetailModel
     * @return
     */
    @PUT("update/{id}")
    Call<EmpDetailModel> updateEmployeeData(@Path("id") String empID,
                                            @Body PostEmployeeDetails empDetailModel);

    /**
     * delete employee account
     * @param empID
     * @return
     */
    @DELETE("delete/{id}")
    Call<DeleteResModel>deleteEmployeeData(@Path("id") String empID);


}
