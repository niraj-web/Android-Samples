package com.example.testing.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.DatePicker;
import android.widget.SearchView;
import android.widget.TextView;

import com.example.testing.Adapters.AdditionAdapter;
import com.example.testing.Adapters.ClientHourAdapter;
import com.example.testing.Adapters.EmployeeNameAdapter;
import com.example.testing.Adapters.ProjectNameAdapter;
import com.example.testing.Adapters.TsHourAdapter;
import com.example.testing.Adapters.TsMinAdapter;
import com.example.testing.Adapters.WorkTypeAdapter;
import com.example.testing.Model.ProjectAssModel;
import com.example.testing.R;
import com.example.testing.utils.ApiRequestHelper;
import com.example.testing.utils.CommonInterface;
import com.example.testing.utils.Utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;

public class MainActivity extends BaseActivity implements CommonInterface
{
    final Calendar myCalendar = Calendar.getInstance();

    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        }
    };

    private ProjectAssModel projectAssModel;
    private TextView tvProjectName;
    private TextView tvEmployeeSelect;
    private TextView tvworktypeSelect;
    private TextView tvAdditionSelect;
    private TextView tvHour;
    private TextView tvMinute;
    private TextView tvClientHour;
    private TextView tvSelectClientMin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvProjectName = findViewById(R.id.tvProjectName);
        tvEmployeeSelect = findViewById(R.id.tvEmployeeSelect);
        tvworktypeSelect = findViewById(R.id.tvWorkTypeSelect);
        tvAdditionSelect = findViewById(R.id.tvadditionSelect);
        tvHour= (TextView) findViewById(R.id.tvHour);
        tvMinute = (TextView) findViewById(R.id.tvMin);
        tvClientHour = (TextView) findViewById(R.id.tvClientHour);
        tvSelectClientMin = (TextView)findViewById(R.id.tvSelectClientMin);

        TextView tvDateSelect= (TextView) findViewById(R.id.tvDateSelect);

        tvProjectName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(MainActivity.this);
                dialog.setContentView(R.layout.project_name_list);
                dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                dialog.setCanceledOnTouchOutside(true);
                dialog.setCancelable(true);

                RecyclerView projectNameList = (RecyclerView) dialog.findViewById(R.id.project_name_list);
                projectNameList.setHasFixedSize(true);
                projectNameList.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                ProjectNameAdapter ProjectNameAdapter = new ProjectNameAdapter(MainActivity.this,projectAssModel.getData().getProject(),MainActivity.this);
                projectNameList.setAdapter(ProjectNameAdapter);

                dialog.show();
            }
        });
        tvEmployeeSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(MainActivity.this);
                dialog.setContentView(R.layout.employee_name_list);
                dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                dialog.setCanceledOnTouchOutside(true);
                dialog.setCancelable(true);

                RecyclerView employeeNameList = (RecyclerView) dialog.findViewById(R.id.employee_name_list);
                employeeNameList.setHasFixedSize(true);
                employeeNameList.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                EmployeeNameAdapter EmployeeNameAdapter = new EmployeeNameAdapter(MainActivity.this,projectAssModel.getData().getUsersList(),MainActivity.this);
                employeeNameList.setAdapter(EmployeeNameAdapter);

                dialog.show();
            }
        });

        tvworktypeSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(MainActivity.this);
                dialog.setContentView(R.layout.work_type_list);
                dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                dialog.setCanceledOnTouchOutside(true);
                dialog.setCancelable(true);

                RecyclerView WorkTypeList = (RecyclerView) dialog.findViewById(R.id.work_type_list);
                WorkTypeList.setHasFixedSize(true);
                WorkTypeList.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                WorkTypeAdapter WorkTypeAdapter = new WorkTypeAdapter(MainActivity.this, projectAssModel.getData().getWorkType(),MainActivity.this);
                WorkTypeList.setAdapter(WorkTypeAdapter);

                dialog.show();

            }
        });
        tvAdditionSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(MainActivity.this);
                dialog.setContentView(R.layout.addition_work_list);
                dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                dialog.setCanceledOnTouchOutside(true);
                dialog.setCancelable(true);

                RecyclerView AdditionWorkList = (RecyclerView) dialog.findViewById(R.id.additin_work_list);
                AdditionWorkList.setHasFixedSize(true);
                AdditionWorkList.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                AdditionAdapter AdditionAdapter = new AdditionAdapter(MainActivity.this, projectAssModel.getData().getExtraWork(),MainActivity.this);
                AdditionWorkList.setAdapter(AdditionAdapter);

                dialog.show();

            }
        });
        tvDateSelect.setOnClickListener(new View.OnClickListener() {
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

                tvDateSelect.setText(sdf.format(myCalendar.getTime()));
            }
        });
        tvHour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(MainActivity.this);
                dialog.setContentView(R.layout.tshour_list);
                dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                dialog.setCanceledOnTouchOutside(true);
                dialog.setCancelable(true);

                RecyclerView tsHourList = (RecyclerView) dialog.findViewById(R.id.tshour_list);
                tsHourList.setHasFixedSize(true);
                tsHourList.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                TsHourAdapter TsHourAdapter = new TsHourAdapter(MainActivity.this, projectAssModel.getData().getTsHrs(),MainActivity.this,"tsHour");
                tsHourList.setAdapter(TsHourAdapter);

                dialog.show();

            }
        });

        tvMinute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Dialog dialog = new Dialog(MainActivity.this);
                dialog.setContentView(R.layout.tsminute_list);
                dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                dialog.setCanceledOnTouchOutside(true);
                dialog.setCancelable(true);

                RecyclerView tsMinuteList = (RecyclerView) dialog.findViewById(R.id.tsminute_list);
                tsMinuteList.setHasFixedSize(true);
                tsMinuteList.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                TsMinAdapter TsMinAdapter = new TsMinAdapter(MainActivity.this, projectAssModel.getData().getTsMinuts(),MainActivity.this,"TsEmpMin");
                tsMinuteList.setAdapter(TsMinAdapter);

                dialog.show();
            }
        });

        tvClientHour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Dialog dialog = new Dialog(MainActivity.this);
                dialog.setContentView(R.layout.clienthour_list);
                dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                dialog.setCanceledOnTouchOutside(true);
                dialog.setCancelable(true);

                RecyclerView ClientHourList = (RecyclerView) dialog.findViewById(R.id.clienthour_list);
                ClientHourList.setHasFixedSize(true);
                ClientHourList.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                ClientHourAdapter clientHourAdapter = new ClientHourAdapter(MainActivity.this, projectAssModel.getData().getTsHrs(),MainActivity.this,"ClientHour");
                ClientHourList.setAdapter(clientHourAdapter);

                dialog.show();

            }
        });
        tvSelectClientMin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(MainActivity.this);
                dialog.setContentView(R.layout.tsminute_list);
                dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                dialog.setCanceledOnTouchOutside(true);
                dialog.setCancelable(true);

                RecyclerView tsMinuteList = (RecyclerView) dialog.findViewById(R.id.tsminute_list);
                tsMinuteList.setHasFixedSize(true);
                tsMinuteList.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                TsMinAdapter TsMinAdapter = new TsMinAdapter(MainActivity.this, projectAssModel.getData().getTsMinuts(),MainActivity.this, "TsClientMin");
                tsMinuteList.setAdapter(TsMinAdapter);

                dialog.show();
            }
        });

        getData("");

    }

    private void getData(String projectID) {
        ProgressDialog dialog = new ProgressDialog(MainActivity.this);
        dialog.setMessage("Please Wait while data loading");
        dialog.show();

        HashMap<String, String> params = new HashMap<>();
        params.put("xAction", "getTimesheetAddEditData");
        params.put("projectID", projectID);
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
    public void onListSelected(Object object, String string)
    {
        if (object instanceof ProjectAssModel.Project)
        {
           ProjectAssModel.Project project =(ProjectAssModel.Project) object;
           tvProjectName.setText(project.getProjectName());
           getData(project.getProjectID());
        }
        if (object instanceof ProjectAssModel.UsersList)
        {
          ProjectAssModel.UsersList user = (ProjectAssModel.UsersList) object;
          tvEmployeeSelect.setText(user.getDisplayName());
        }
        if (object instanceof ProjectAssModel.WorkType) {
            ProjectAssModel.WorkType workType = (ProjectAssModel.WorkType) object;
            tvworktypeSelect.setText(workType.getWorkTypeName());

        }
        if (object instanceof ProjectAssModel.ExtraWork) {
            ProjectAssModel.ExtraWork extraWork = (ProjectAssModel.ExtraWork) object;
            tvAdditionSelect.setText(extraWork.getExtraworkName());

        }
        if (string.equalsIgnoreCase("tsHour")) {
            String tshour = (String) object;
            tvHour.setText(tshour);

        }
        if (string.equalsIgnoreCase("TsEmpMin")) {
            String tsMin = (String) object;
            tvMinute.setText(tsMin);

        }
        if (string.equalsIgnoreCase("ClientHour")) {
            String clientHour = (String) object;
            tvClientHour.setText(clientHour);
        }
        if (string.equalsIgnoreCase("TsClientMin")) {
            String clientMin = (String) object;
            tvSelectClientMin.setText(clientMin);
        }
    }
}