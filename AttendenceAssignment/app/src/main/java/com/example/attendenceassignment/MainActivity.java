package com.example.attendenceassignment;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.attendenceassignment.Adapter.Adapter;
import com.example.attendenceassignment.Adapter.MonthAdapter;
import com.example.attendenceassignment.Adapter.YearAdapter;
import com.example.attendenceassignment.Model.ProjectAssModel;
import com.example.attendenceassignment.utils.ApiRequestHelper;
import com.example.attendenceassignment.utils.CommonInterface;
import com.example.attendenceassignment.utils.Utils;

import java.time.Year;
import java.util.Calendar;
import java.util.HashMap;

import butterknife.BindView;

public class MainActivity extends BaseActivity implements CommonInterface
{
    private TextView tvYear;
    private TextView tvMonth;
    private ProjectAssModel projectAssModel;
    private Adapter adapter;
    public String year;
    public String month;

    RecyclerView recyclerView;

    private Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvYear = findViewById(R.id.tvYear);
        tvMonth = findViewById(R.id.tvMonth);
        recyclerView = findViewById(R.id.recyclerView);

        Calendar c = Calendar.getInstance();
         year = String.valueOf(c.get(Calendar.YEAR));
         month = String.valueOf(c.get(Calendar.MONTH));

        tvMonth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 dialog = new Dialog(MainActivity.this);
                dialog.setContentView(R.layout.month_list);
                dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                dialog.setCanceledOnTouchOutside(true);
                dialog.setCancelable(true);

                RecyclerView MonthList = (RecyclerView) dialog.findViewById(R.id.month_list);
                MonthList.setHasFixedSize(true);
                MonthList.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                MonthAdapter MonthAdapter = new MonthAdapter(MainActivity.this,MainActivity.this);
                MonthList.setAdapter(MonthAdapter);

                dialog.show();
            }
        });

        tvYear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog = new Dialog(MainActivity.this);
                dialog.setContentView(R.layout.year_list);
                dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                dialog.setCanceledOnTouchOutside(true);
                dialog.setCancelable(true);

                RecyclerView YearList = (RecyclerView) dialog.findViewById(R.id.year_list);
                YearList.setHasFixedSize(true);
                YearList.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                YearAdapter YearAdapter = new YearAdapter(MainActivity.this,MainActivity.this);
                YearList.setAdapter(YearAdapter);

                dialog.show();

            }
        });

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager (mContext, LinearLayoutManager.VERTICAL, false));

        getData();

    }

    @Override
    protected int getActivityLayout() {
        return R.layout.activity_main;
    }

    public void getData() {
        ProgressDialog dialog = new ProgressDialog(MainActivity.this);
        dialog.setMessage("Please Wait while data loading");
        dialog.show();

        HashMap<String, String> params = new HashMap<>();
        params.put("xAction", "getAttendanceList");
        params.put("month", String.valueOf(month));
        params.put("year", String.valueOf(year));
        params.put("userID", "275");
        if (cd.isConnectingToInternet()) {
            app.getApiRequestHelper().updateProjectAssign(params, new ApiRequestHelper.OnRequestComplete() {
                @Override
                public void onSuccess(Object object)
                {
                    projectAssModel = (ProjectAssModel) object;
//                    Utils.showLongToast(mContext,projectAssModel.getMsg());
                    if ( projectAssModel != null) {
                        if (projectAssModel.getCount() > 0 && projectAssModel.getData() != null)
                        {
                            adapter=new Adapter(mContext,projectAssModel.getData());
                            recyclerView.setAdapter(adapter);

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
    public void onListSelected(String object, String string) {
        if (string.equalsIgnoreCase("month") ){
            month = object;
            tvMonth.setText(month);
            dialog.dismiss();

        }
        if (string.equalsIgnoreCase("year")) {
            year = object;
            tvYear.setText(year);
            dialog.dismiss();
        }
        getData();

    }
}