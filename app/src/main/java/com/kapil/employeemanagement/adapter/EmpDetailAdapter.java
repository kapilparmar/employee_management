package com.kapil.employeemanagement.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kapil.employeemanagement.R;
import com.kapil.employeemanagement.retrofit.model.EmpDetailModel;

import java.util.LinkedList;

public class EmpDetailAdapter extends RecyclerView.Adapter<EmpDetailAdapter.EmpViewHolder> {

    private LinkedList<EmpDetailModel> empDetailModelList;
    private OnDetailClickListener onDetailClickListener;

    public EmpDetailAdapter(LinkedList<EmpDetailModel> empDetailModelList , Context context, OnDetailClickListener onDetailClickListener) {
        this.empDetailModelList = empDetailModelList;
        this.onDetailClickListener = onDetailClickListener;
    }

    @NonNull
    @Override
    public EmpViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View itemView = LayoutInflater.from(parent.getContext()).
               inflate(R.layout.emp_details, parent, false);

        return new EmpViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final EmpViewHolder holder, int pos) {

        final int position = holder.getAdapterPosition();
        holder.txtAge.setText(empDetailModelList.get(position).getEmployeeAge());
        holder.txtName.setText(empDetailModelList.get(position).getEmployeeName());
        holder.txtSalary.setText(empDetailModelList.get(position).getEmployeeSalary());
        holder.txtEmpId.setText(empDetailModelList.get(position).getId());
        holder.imgMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onDetailClickListener.onDetailClicked(empDetailModelList.get(position),position);
            }
        });
        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onDetailClickListener.onSelectEmployee(empDetailModelList.get(position));

            }
        });
    }

    @Override
    public int getItemCount() {
        return empDetailModelList.size();
    }

    public class EmpViewHolder extends RecyclerView.ViewHolder {
        TextView txtName;
        TextView txtAge;
        TextView txtSalary;
        TextView txtEmpId;
        ImageView imgMore;
        RelativeLayout relativeLayout;

        EmpViewHolder(View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txt_name);
            txtAge = itemView.findViewById(R.id.txt_age);
            txtSalary = itemView.findViewById(R.id.txt_salary);
            txtEmpId = itemView.findViewById(R.id.txt_emp_id);
            relativeLayout = itemView.findViewById(R.id.relative_layout);
            imgMore = itemView.findViewById(R.id.img_more);
        }
    }

    public interface OnDetailClickListener{
        void onDetailClicked(EmpDetailModel model, int position);
        void onSelectEmployee(EmpDetailModel model);
    }
}


