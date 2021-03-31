package com.example.leaveassignment.utils;

import com.example.leaveassignment.Model.ProjectAssModel;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Api {
    @FormUrlEncoded
    @POST("/mxprm1.0/services/")
        //@POST("/demos-mxprm/services/")
    Call<ProjectAssModel> Data (@FieldMap Map<String, String> params);
}
