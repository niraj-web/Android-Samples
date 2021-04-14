package com.example.hospitalbookingassignmet;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hospitalbookingassignmet.Adapters.ClinicAdapter;
import com.example.hospitalbookingassignmet.Adapters.DoctorAdapter;
import com.example.hospitalbookingassignmet.Model.ModelClass;
import com.example.hospitalbookingassignmet.utils.ApiRequestHelper;
import com.example.hospitalbookingassignmet.utils.CommonInterface;
import com.example.hospitalbookingassignmet.utils.Utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class MainActivity extends BaseActivity implements CommonInterface {

    private ModelClass modelClass;
    private TextView etChooseClinic;
    private Dialog dialog;
    private TextView etChooseDoctor;
    private TextView etChooseDate;
    private EditText message;
    private Button submit;
    private Calendar c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etChooseClinic = findViewById(R.id.etChooseClinic);
        etChooseDoctor = findViewById(R.id.etChooseDoctor);
        etChooseDate = findViewById(R.id.etChooseDate);
        submit = findViewById(R.id.submit);
        message = findViewById(R.id.message);

        etChooseClinic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog = new Dialog(MainActivity.this);
                dialog.setContentView(R.layout.clinic_name_list);
                dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                dialog.setCanceledOnTouchOutside(true);
                dialog.setCancelable(true);

                RecyclerView clinicNameList = (RecyclerView) dialog.findViewById(R.id.clinic_name_list);
                clinicNameList.setHasFixedSize(true);
                clinicNameList.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                ClinicAdapter clinicAdapter = new ClinicAdapter(MainActivity.this,modelClass.getData(),MainActivity.this);
                clinicNameList.setAdapter(clinicAdapter);
                dialog.show();

                TextView cancel =(TextView) dialog.findViewById(R.id.cancel);

                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
            }
        });

        etChooseDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 c = Calendar.getInstance();
                DatePickerDialog dialog = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        String _year = String.valueOf(year);
                        String _month = (month+1) < 10 ? "0" + (month+1) : String.valueOf(month+1);
                        String _date = dayOfMonth < 10 ? "0" + dayOfMonth : String.valueOf(dayOfMonth);
                        String _pickedDate = _year + "-" + _month + "-" + _date;
                        Log.e("PickedDate: ", "Date: " + _pickedDate); //2019-02-12
                    }
                }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.MONTH));
                dialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
                dialog.show();
                updateLabel();
            }
            private void updateLabel()
            {
                String myFormat = "dd/MM/yy"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
                etChooseDate.setText(sdf.format(c.getTime()));
            }
        });

        etChooseDoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog = new Dialog(MainActivity.this);
                dialog.setContentView(R.layout.doctor_name_list);
                dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                dialog.setCanceledOnTouchOutside(true);
                dialog.setCancelable(true);

                RecyclerView doctorNameList = (RecyclerView) dialog.findViewById(R.id.doctor_name_list);
                doctorNameList.setHasFixedSize(true);
                doctorNameList.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                DoctorAdapter doctorAdapter = new DoctorAdapter(MainActivity.this,modelClass.getData(),MainActivity.this);
                doctorNameList.setAdapter(doctorAdapter);
                dialog.show();

                TextView cancel2 =(TextView) dialog.findViewById(R.id.cancel2);

                cancel2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("WrongConstant")
            @Override
            public void onClick(View view) {
               ((MainActivity)mContext).getUpdateStatus();
                if(etChooseClinic.getText().toString().equalsIgnoreCase("")){
                    Toast.makeText(getApplicationContext(),"Please Enter Clinic Name",5).show();
                }

                if(etChooseDoctor.getText().toString().equalsIgnoreCase("")){
                    Toast.makeText(getApplicationContext(),"Please Enter Doctor Name",5).show();
                }

                if(etChooseDate.getText().toString().equalsIgnoreCase("")){
                    Toast.makeText(getApplicationContext(),"Please Enter Date",5).show();
                }

                if(message.getText().toString().equalsIgnoreCase("")){
                    Toast.makeText(getApplicationContext(),"Please Enter Message",5).show();
                }
            }
        });
        getData();
    }

    private void getData() {
        ProgressDialog dialog = new ProgressDialog(MainActivity.this);
        dialog.setMessage("Please Wait while data loading");
        dialog.show();

        HashMap<String, String> params = new HashMap<>();
        params.put("xAction", "getHospitals");
        params.put(" langCode","en");
        params.put("userID","3");
        params.put("deviceToken","f2axYC1vQ-muSxdst-6GX5%3AAPA91bEzphps1MU2LTT2DurncnlFKJo5kwpMgN5Ht0iGz0CcnWE9j2PKG_H8usTGorafu348ZxtRQu2100icX0nmuYVkytjU4q-ottfDeerrtxLX0shmrpbeYgtiap2jejZGLH9en4nj");
        if (cd.isConnectingToInternet()) {
            app.getApiRequestHelper().updateProjectAssign(params, new ApiRequestHelper.OnRequestComplete() {
                @Override
                public void onSuccess(Object object) {
                    modelClass = (ModelClass) object;
//                    Utils.showLongToast(mContext,projectAssModel.getMsg());
                    if (modelClass != null) {
                        if (modelClass.getStatus()  && modelClass.getIsUserAuthTokenValid() != null) {
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

    private void getDoctorData(Integer hospitalID) {
        ProgressDialog dialog = new ProgressDialog(MainActivity.this);
        dialog.setMessage("Please Wait while data loading");
        dialog.show();

        HashMap<String, String> params = new HashMap<>();
        params.put("xAction", "getDoctor");
        params.put("hospitalID", String.valueOf(hospitalID));
        params.put("langCode","en");
        params.put("userID","3");
        params.put("deviceToken","f2axYC1vQ-muSxdst-6GX5%3AAPA91bEzphps1MU2LTT2DurncnlFKJo5kwpMgN5Ht0iGz0CcnWE9j2PKG_H8usTGorafu348ZxtRQu2100icX0nmuYVkytjU4q-ottfDeerrtxLX0shmrpbeYgtiap2jejZGLH9en4nj");
        if (cd.isConnectingToInternet()) {
            app.getApiRequestHelper().updateProjectAssign(params, new ApiRequestHelper.OnRequestComplete() {
                @Override
                public void onSuccess(Object object) {
                    modelClass = (ModelClass) object;
//                    Utils.showLongToast(mContext,projectAssModel.getMsg());
                    if (modelClass != null) {
                        if (modelClass.getStatus()  && modelClass.getIsUserAuthTokenValid() != null) {
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

    public void getUpdateStatus() {
        Map<String, String> params = new HashMap<>();
        params.put("xAction", "hospitalAppointment");
        params.put("hospitalID", "hospitalID");
        params.put("deviceToken","f2axYC1vQ-muSxdst-6GX5%3AAPA91bEzphps1MU2LTT2DurncnlFKJo5kwpMgN5Ht0iGz0CcnWE9j2PKG_H8usTGorafu348ZxtRQu2100icX0nmuYVkytjU4q-ottfDeerrtxLX0shmrpbeYgtiap2jejZGLH9en4nj");
        params.put("appointmentDate","selectedDate");
        params.put("doctorID","doctorID");
      //      params.put("message", etMessage.getText().toString());
        params.put("offerCouponCodeID","offerCouponCodeID");
        params.put("langCode", "en");
        params.put("userID", "3");
        if (cd.isConnectingToInternet()) {
            app.getApiRequestHelper().updateProjectAssign(params, new ApiRequestHelper.OnRequestComplete() {
                @Override
                public void onSuccess(Object object) {
                    ModelClass modelClass = (ModelClass) object;
//                    Utils.showLongToast(mContext,projectAssModel.getMsg());
                    if ( modelClass != null) {
                        if (modelClass.getStatus() && modelClass.getIsUserAuthTokenValid() != null) {

                        } else {
                        }
                    } else {
                        Utils.showLongToast(mContext, Utils.UNPROPER_RESPONSE);
                    }
                }

                @Override
                public void onFailure(String apiResponse) {
                    Log.e("in", "error " + apiResponse);
                    Utils.showLongToast(mContext, apiResponse);
                }
            });

        } else {
            Utils.alert_dialog(mContext);
        }
    }
    @Override
    protected int getActivityLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void onListSelected(Object object, String string) {
        if (object instanceof ModelClass.Data) {
            ModelClass.Data clinic = (ModelClass.Data) object;
            etChooseClinic.setText(clinic.getHosName());
            getDoctorData(clinic.getHosID());
            dialog.dismiss();
        }
    }
}