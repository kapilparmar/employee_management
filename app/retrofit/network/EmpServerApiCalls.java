package com.kapil.employeemanagement.retrofit.network;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.Toast;


import com.kapil.employeemanagement.R;
import com.kapil.employeemanagement.retrofit.model.ApiErrorModel;
import com.kapil.employeemanagement.retrofit.model.DeleteResModel;
import com.kapil.employeemanagement.retrofit.model.EmpDetailModel;
import com.kapil.employeemanagement.retrofit.model.PostEmployeeDetails;

import java.util.LinkedList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EmpServerApiCalls {

    private static final String TAG = EmpServerApiCalls.class.getSimpleName();
    private final EmpApiService empApiService;
    private Context context;

    public EmpServerApiCalls(Context context){

        this.context = context;
        empApiService = EmpServerApiClient.getClient().create(EmpApiService.class);
    }
    public void createEmpAccount(final PostEmployeeDetails empDetailModel, final EmpApiListener<EmpDetailModel, ApiErrorModel> listener){
           empApiService.createEmployeeData(empDetailModel).enqueue(new Callback<EmpDetailModel>() {
               @Override
               public void onResponse(Call<EmpDetailModel> call, Response<EmpDetailModel> response) {
                   if (response.body()!=null){
                       listener.onSuccess(response.body());
                   }
               }
               @Override
               public void onFailure(Call<EmpDetailModel> call, Throwable t) {
                   listener.onError(ApiErrorModel.buildErrorObject(t));
               }
           });
    }


    public void getAllEmpRecords( final EmpApiListener<LinkedList<EmpDetailModel>, ApiErrorModel> listener){
       empApiService.getAllEmployeeData().enqueue(new Callback<LinkedList<EmpDetailModel>>() {
           @Override
           public void onResponse(Call<LinkedList<EmpDetailModel>> call, Response<LinkedList<EmpDetailModel>> response) {
               listener.onSuccess(response.body());
           }

           @Override
           public void onFailure(Call<LinkedList<EmpDetailModel>> call, Throwable t) {
               listener.onError(ApiErrorModel.buildErrorObject(t));
           }
       });
    }

    public void getSingleEmpDdetails( String empId, final EmpApiListener<EmpDetailModel, ApiErrorModel> listener){
        empApiService.getSingleEmployeeData(empId).enqueue(new Callback<EmpDetailModel>() {
            @Override
            public void onResponse(Call<EmpDetailModel> call, Response<EmpDetailModel> response) {
                if (response.body()!=null){
                    listener.onSuccess(response.body());
                }

            }

            @Override
            public void onFailure(Call<EmpDetailModel> call, Throwable t) {
                listener.onError(ApiErrorModel.buildErrorObject(t));
            }
        });
    }

    public void updateEmpDdetails( String id, PostEmployeeDetails empDetailModel, final EmpApiListener<EmpDetailModel, ApiErrorModel> listener){
        empApiService.updateEmployeeData(id,empDetailModel).enqueue(new Callback<EmpDetailModel>() {
            @Override
            public void onResponse(Call<EmpDetailModel> call, Response<EmpDetailModel> response) {
                if (response.body()!=null){
                    listener.onSuccess(response.body());
                }

            }

            @Override
            public void onFailure(Call<EmpDetailModel> call, Throwable t) {
                listener.onError(ApiErrorModel.buildErrorObject(t));
            }
        });
    }

    public void deleteEmpDdetails(final String empId , final EmpApiListener<DeleteResModel, ApiErrorModel> listener){
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(R.string.delete_confirm);
        builder.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(final DialogInterface dialogInterface, int i) {
                empApiService.deleteEmployeeData(empId).enqueue(new Callback<DeleteResModel>() {
                    @Override
                    public void onResponse(Call<DeleteResModel> call, Response<DeleteResModel> response) {
                        listener.onSuccess(response.body());
                        dialogInterface.dismiss();
                    }

                    @Override
                    public void onFailure(Call<DeleteResModel> call, Throwable t) {
                        listener.onError(ApiErrorModel.buildErrorObject(t));
                        dialogInterface.dismiss();
                    }
                });
            }
        });
        AlertDialog alertDialog = builder.create();
        builder.show();

    }

}
