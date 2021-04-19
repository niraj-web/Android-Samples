package com.example.hotelbookingassignment.utils;

import com.example.hotelbookingassignment.Model.ProjectAssModel;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface Api
{
    @FormUrlEncoded
    @POST("/manazil1.0/services/")
        //@POST("/demos-mxprm/services/")
    Call<ProjectAssModel> Data (@FieldMap Map<String, String> params,@Header("Authorization") String authHeader);
}
