package com.kapil.employeemanagement;

import android.content.Context;
import android.content.SearchRecentSuggestionsProvider;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.kapil.employeemanagement.adapter.EmpDetailAdapter;
import com.kapil.employeemanagement.retrofit.model.ApiErrorModel;
import com.kapil.employeemanagement.retrofit.model.DeleteResModel;
import com.kapil.employeemanagement.retrofit.model.EmpDetailModel;
import com.kapil.employeemanagement.retrofit.network.EmpApiListener;
import com.kapil.employeemanagement.retrofit.network.EmpServerApiCalls;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static com.kapil.employeemanagement.EmpAccountFragment.EMP_MODEL;
import static com.kapil.employeemanagement.EmpAccountFragment.IS_UPDATE_PROFILE;


public class EmpDetailFragment extends Fragment implements View.OnClickListener, EmpDetailAdapter.OnDetailClickListener {
    private String TAG = EmpDetailFragment.class.getSimpleName();
    private Context context;
    private RecyclerView recyclerView;
    private EmpDetailAdapter empDetailAdapter;
    private EmpDetailAdapter.OnDetailClickListener onDetailClickListener;
    LinkedList<EmpDetailModel> empDetailModelList;

    public static EmpDetailFragment newInstance() {
        EmpDetailFragment fragment = new EmpDetailFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_emp_details,container,false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        context = getContext();
        recyclerView = view.findViewById(R.id.recycler_view);
        onDetailClickListener = this;

        if (getArguments()!=null) {
            empDetailModelList = (LinkedList<EmpDetailModel>) getArguments().getSerializable(EMP_MODEL);
            if (empDetailModelList!=null) {
                empDetailAdapter = new EmpDetailAdapter(empDetailModelList, context, onDetailClickListener);
                recyclerView.setAdapter(empDetailAdapter);
                recyclerView.setLayoutManager(
                        new LinearLayoutManager(context));
            }
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

    }

    @Override
    public void onDetailClicked(final EmpDetailModel model, final int position) {
        EmpServerApiCalls  empServerApiCalls = new EmpServerApiCalls(context);

        empServerApiCalls.deleteEmpDdetails(model.getId(),new EmpApiListener<DeleteResModel, ApiErrorModel>() {
            @Override
            public void onSuccess(DeleteResModel object) {
                Toast.makeText(context, R.string.deleted,Toast.LENGTH_SHORT).show();
                empDetailModelList.remove(model);
                empDetailAdapter.notifyItemRemoved(position);
            }
            @Override
            public void onError(ApiErrorModel object) {
                Toast.makeText(context, R.string.somthing_went_wrong,Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onSelectEmployee(EmpDetailModel model) {
        EmpAccountFragment fragment = EmpAccountFragment.newInstance();
        Bundle args = new Bundle();
        args.putSerializable(EMP_MODEL, (Serializable) model);
        args.putBoolean(IS_UPDATE_PROFILE, true);
        fragment.setArguments(args);

        getActivity().getSupportFragmentManager()
                .beginTransaction().
                replace(R.id.container, fragment)
                .addToBackStack(EmpDetailFragment.class.getSimpleName())
                .commit();
    }
}
