package com.example.leaveassignment;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TextView;

import com.example.leaveassignment.Adapter.LeaveTypeAdapter;
import com.example.leaveassignment.Model.ProjectAssModel;
import com.example.leaveassignment.utils.ApiRequestHelper;
import com.example.leaveassignment.utils.CommonInterface;
import com.example.leaveassignment.utils.Utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;

public class MainActivity extends BaseActivity implements CommonInterface {
    private ProjectAssModel projectAssModel;
    private TextView tvLeaveTypeSelect;

    final Calendar myCalendar = Calendar.getInstance();

    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvLeaveTypeSelect = findViewById(R.id.tvLeavetypeSelect);
        TextView tvFromSelect = findViewById(R.id.tvFromSelect);
        TextView tvToSelect = findViewById(R.id.tvToSelect);

        tvLeaveTypeSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(MainActivity.this);
                dialog.setContentView(R.layout.leave_type_list);
                dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                dialog.setCanceledOnTouchOutside(true);
                dialog.setCancelable(true);

                RecyclerView projectNameList = (RecyclerView) dialog.findViewById(R.id.leave_type_list);
                projectNameList.setHasFixedSize(true);
                projectNameList.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                LeaveTypeAdapter LeaveTypeAdapter = new LeaveTypeAdapter(MainActivity.this,projectAssModel.getData().getLeaveType(),MainActivity.this);
                projectNameList.setAdapter(LeaveTypeAdapter);
                dialog.show();

            }
        });

        tvFromSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(MainActivity.this,date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();


                updateLabel();
            }
            private void updateLabel()
            {
                String myFormat = "dd/MM/yy"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
                tvFromSelect.setText(sdf.format(myCalendar.getTime()));
            }
        });

        tvToSelect.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                new DatePickerDialog(MainActivity.this,date, myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();

                updateLabel();
            }
            private void updateLabel() {
                String myFormat = "dd/MM/yy"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
                tvToSelect.setText(sdf.format(myCalendar.getTime()));
            }
        });

        getData();
    }



    private void getData() {
        ProgressDialog dialog = new ProgressDialog(MainActivity.this);
        dialog.setMessage("Please Wait while data loading");
        dialog.show();

        HashMap<String, String> params = new HashMap<>();
        params.put("xAction", "getLeaveAddEditData");
        params.put("userID", "275");
        if (cd.isConnectingToInternet()) {
            app.getApiRequestHelper().updateProjectAssign(params, new ApiRequestHelper.OnRequestComplete() {
                @Override
                public void onSuccess(Object object) {
                    projectAssModel = (ProjectAssModel) object;
//                    Utils.showLongToast(mContext,projectAssModel.getMsg());
                    if (projectAssModel != null) {
                        if (projectAssModel.getCount() > 0 && projectAssModel.getData() != null) {
                        } else {
                        }
                    } else {
                        Utils.showLongToast(MainActivity.this, Utils.UNPROPER_RESPONSE);
                    }
                    dialog.dismiss();
                }
                @Override
                public void onFailure(String apiResponse) {
                    Log.e("in", "error " + apiResponse);
                    Utils.showLongToast(MainActivity.this, apiResponse);
                }
            });
        } else {
            Utils.alert_dialog(MainActivity.this);
        }
    }
    @Override
    protected int getActivityLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void onListSelected(Object object, String string) {
        if (object instanceof ProjectAssModel.LeaveType)
        {
            ProjectAssModel.LeaveType LeaveType =(ProjectAssModel.LeaveType) object;
            tvLeaveTypeSelect.setText(LeaveType.getLeaveTypeName());
        }
    }
}