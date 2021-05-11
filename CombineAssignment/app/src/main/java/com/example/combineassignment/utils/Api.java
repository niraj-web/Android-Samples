package com.example.combineassignment.utils;

import com.example.combineassignment.Model.RoomDetailsModel;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface Api
{
    @FormUrlEncoded
    @POST("/terhab/hotelbookingwp/wp-json/manazil/v1/getAvailableRooms/")
        //@POST("/demos-mxprm/services/")
    Call<RoomDetailsModel> Data (@FieldMap Map<String, String> params, @Query("lang") String en, @Header("Authorization") String authHeader);
}
