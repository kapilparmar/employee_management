package com.kapil.employeemanagement;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.kapil.employeemanagement.presenter.EmployeeDetailsContractor;
import com.kapil.employeemanagement.retrofit.model.EmpDetailModel;
import com.kapil.employeemanagement.retrofit.model.PostEmployeeDetails;
import com.kapil.employeemanagement.retrofit.network.EmpServerApiCalls;
import com.kapil.employeemanagement.view.IEmpAccountView;


public class EmpAccountFragment extends Fragment implements IEmpAccountView, View.OnClickListener  {
    private String TAG = EmpAccountFragment.class.getSimpleName();
    private Context context;
    private EmpServerApiCalls empServerApiCalls;
    private EditText edtName , edtAge, edtSalary;
    private Button btnSave;
    public static final String EMP_MODEL= "emp_model";
    public static final String IS_UPDATE_PROFILE= "is_update_profile";
    private EmpDetailModel empDetailModel = new EmpDetailModel();
    private boolean isUpdateDetail;
    private EmployeeDetailsContractor employeeDetailsContractor;
    private ProgressBar progressBar;
    public EmpAccountFragment() {
        super();
    }

    public static EmpAccountFragment newInstance() {
        EmpAccountFragment fragment = new EmpAccountFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments()!=null){
            isUpdateDetail = getArguments().getBoolean(IS_UPDATE_PROFILE);
            empDetailModel = (EmpDetailModel) getArguments().getSerializable(EMP_MODEL);

        }

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_create_account,container,false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        context = getContext();
        edtName = view.findViewById(R.id.edt_name);
        edtAge = view.findViewById(R.id.edt_age);
        edtSalary = view.findViewById(R.id.edt_salary);
        btnSave = view.findViewById(R.id.btn_save);
        progressBar = view.findViewById(R.id.progressBar);
        employeeDetailsContractor = new EmployeeDetailsContractor(this);
        empServerApiCalls = new EmpServerApiCalls(context);
        btnSave.setOnClickListener(this);
        employeeDetailsContractor.setProgressBarVisibility(false);
        if (isUpdateDetail){
            btnSave.setText(R.string.update);
            employeeDetailsContractor.displayEmpDetails(empDetailModel);

        }
    }

    @Override
    public void setEmpDetails(EmpDetailModel empDetailModel) {
        edtAge.setText(empDetailModel.getEmployeeAge());
        edtName.setText(empDetailModel.getEmployeeName());
        edtSalary.setText(empDetailModel.getEmployeeSalary()) ;
    }

    @Override
    public void onAccountCreated(EmpDetailModel empDetailModel) {
        employeeDetailsContractor.setProgressBarVisibility(false);
        Toast.makeText(context, R.string.created,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDetailsUpdated(EmpDetailModel empDetailModel) {
        employeeDetailsContractor.setProgressBarVisibility(false);
        Toast.makeText(context, R.string.updated,Toast.LENGTH_SHORT).show();

    }

    @Override
    public void setProgressBarVisibility(boolean show) {
        if (show){
            progressBar.setVisibility(View.VISIBLE);
        }
        else {
            progressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }



    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_save:
                PostEmployeeDetails empDetails = new PostEmployeeDetails();
                empDetails.setAge(edtAge.getText().toString());
                empDetails.setName(edtName.getText().toString());
                empDetails.setSalary(edtSalary.getText().toString());

                if (empDetails.getName().isEmpty() || empDetails.getAge().isEmpty() || empDetails.getSalary().isEmpty()){
                    Toast.makeText(context, R.string.all_feild_mandatory,Toast.LENGTH_SHORT).show();
                    return;
                }
                if (Utils.isNetConnected(context)) {
                    employeeDetailsContractor.setProgressBarVisibility(true);
                    if (!isUpdateDetail) {
                        employeeDetailsContractor.createEmpAccount(empDetails, getContext());
                    } else {
                        String id = empDetailModel.getId();
                        employeeDetailsContractor.updateEmp(id, empDetails, context);
                    }
                }  else Toast.makeText(context,R.string.no_internet,Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
