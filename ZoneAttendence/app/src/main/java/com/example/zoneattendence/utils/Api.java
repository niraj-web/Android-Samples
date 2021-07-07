package com.example.zoneattendence.utils;

import com.example.zoneattendence.Model.AdminBarcodeModel;
import com.example.zoneattendence.Model.LoadBarcodeModel;
import com.example.zoneattendence.Model.UpdateDeviceModel;
import com.example.zoneattendence.Model.VerifyDeviceModel;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Api
{
    @FormUrlEncoded
    @POST("/zone-attendance/xadmin/services/")
        //@POST("/demos-mxprm/services/")
    Call<VerifyDeviceModel> Data (@FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST("/zone-attendance/xadmin/services/")
        //@POST("/demos-mxprm/services/")
    Call<UpdateDeviceModel> Data1 (@FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST("/zone-attendance/xadmin/services/")
        //@POST("/demos-mxprm/services/")
    Call<AdminBarcodeModel> Data2 (@FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST("/zone-attendance/xadmin/services/")
        //@POST("/demos-mxprm/services/")
    Call<LoadBarcodeModel> Data3 (@FieldMap Map<String, String> params);

}

