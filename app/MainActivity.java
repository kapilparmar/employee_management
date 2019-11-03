package com.kapil.employeemanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.kapil.employeemanagement.presenter.MainActivityContractor;
import com.kapil.employeemanagement.retrofit.model.EmpDetailModel;
import com.kapil.employeemanagement.retrofit.network.EmpServerApiCalls;
import com.kapil.employeemanagement.view.IEmpSearchView;

import java.io.Serializable;
import java.util.LinkedList;

import static com.kapil.employeemanagement.EmpAccountFragment.EMP_MODEL;
import static com.kapil.employeemanagement.EmpAccountFragment.IS_UPDATE_PROFILE;

public class MainActivity extends AppCompatActivity implements IEmpSearchView,View.OnClickListener {

    private String TAG = MainActivity.class.getSimpleName();
    EmpServerApiCalls empServerApiCalls;
    private EditText edtEmpId;
    private ImageButton imgSearch;
    private TextView txtSearchEmps;
    private Button btnCreateAcc;
    private ProgressBar progressBar;
    private MainActivityContractor iMainActivityEmpPresenter;
    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        edtEmpId = findViewById(R.id.edt_search_emp_id);
        txtSearchEmps = findViewById(R.id.txt_search_all_emp);
        txtSearchEmps.setOnClickListener(this);
        imgSearch = findViewById(R.id.img_search);
        imgSearch.setOnClickListener(this);
        btnCreateAcc = findViewById(R.id.btn_create);
        btnCreateAcc.setOnClickListener(this);
        progressBar = findViewById(R.id.progressBar);
        iMainActivityEmpPresenter = new MainActivityContractor(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.txt_search_all_emp:
                progressBar.setVisibility(View.VISIBLE);
                iMainActivityEmpPresenter.searchAllEmp(context);
                break;
            case R.id.img_search:
                progressBar.setVisibility(View.VISIBLE);
                iMainActivityEmpPresenter.searchSingleEmp(edtEmpId.getText().toString().trim(),context);
                break;
            case R.id.btn_create:
                iMainActivityEmpPresenter.createNewAccountClicked(context);
                break;
        }
    }

    @Override
    public void onSearchEmpIdResult(EmpDetailModel empDetailModel) {
     EmpAccountFragment fragment = EmpAccountFragment.newInstance();
                Bundle args = new Bundle();
                progressBar.setVisibility(View.GONE);
                args.putSerializable(EMP_MODEL,empDetailModel);
                args.putBoolean(IS_UPDATE_PROFILE, true);
                fragment.setArguments(args);
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.container, fragment)
                        .addToBackStack(EmpAccountFragment.class.getSimpleName())
                        .commit();
    }

    @Override
    public void onSearchAllEmpResult(LinkedList<EmpDetailModel> empDetailModel) {
        EmpDetailFragment fragment = EmpDetailFragment.newInstance();
        Bundle args = new Bundle();
        args.putSerializable(EMP_MODEL, (Serializable) empDetailModel);
        fragment.setArguments(args);

        progressBar.setVisibility(View.GONE);
        getSupportFragmentManager()
                .beginTransaction().
                replace(R.id.container, fragment)
                .addToBackStack(EmpDetailFragment.class.getSimpleName())
                .commit();
    }

    @Override
    public void createNewAccount() {
        EmpAccountFragment fragment = EmpAccountFragment.newInstance();
        Bundle args = new Bundle();
        args.putBoolean(IS_UPDATE_PROFILE, false);
        fragment.setArguments(args);

        getSupportFragmentManager()
                .beginTransaction().
                replace(R.id.container, fragment)
                .addToBackStack(EmpDetailFragment.class.getSimpleName())
                .commit();
    }



    @Override
    public void showProgressBar(boolean show) {
        if (show){
            progressBar.setVisibility(View.VISIBLE);
        }
        else {
            progressBar.setVisibility(View.GONE);
        }
    }
}
