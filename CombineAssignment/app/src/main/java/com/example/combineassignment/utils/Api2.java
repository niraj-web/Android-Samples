package com.example.combineassignment.utils;

import com.example.combineassignment.Model.DentalModel;
import com.example.combineassignment.Model.HospitalModel;
import com.example.combineassignment.Model.OfferDetailsModel;
import com.example.combineassignment.Model.OffersModel;
import com.example.combineassignment.Model.PaymentModel;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface Api2 {

    @FormUrlEncoded
    @POST("/manazil1.0/services/")
        //@POST("/demos-mxprm/services/")
    Call<OffersModel> Data1 (@FieldMap Map<String, String> params, @Query("lang") String en, @Header("Authorization") String authHeader);

    @FormUrlEncoded
    @POST("/manazil1.0/services/")
        //@POST("/demos-mxprm/services/")
    Call<HospitalModel> Data2 (@FieldMap Map<String, String> params,@Query("lang") String en, @Header("Authorization") String authHeader);

    @FormUrlEncoded
    @POST("/manazil1.0/services/")
        //@POST("/demos-mxprm/services/")
    Call<DentalModel> Data3 (@FieldMap Map<String, String> params,@Query("lang") String en, @Header("Authorization") String authHeader);

    @FormUrlEncoded
    @POST("/manazil1.0/services/")
        //@POST("/demos-mxprm/services/")
    Call<OfferDetailsModel> Data4 (@FieldMap Map<String, String> params,@Query("lang") String en, @Header("Authorization") String authHeader);

    @FormUrlEncoded
    @POST("/manazil1.0/services/")
        //@POST("/demos-mxprm/services/")
    Call<PaymentModel> Data5 (@FieldMap Map<String, String> params,@Query("lang") String en, @Header("Authorization") String authHeader);
}
