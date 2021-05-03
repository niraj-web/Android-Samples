package com.example.fragmentassignment.utils;

import com.example.fragmentassignment.Model.DentalModel;
import com.example.fragmentassignment.Model.HospitalModel;
import com.example.fragmentassignment.Model.OfferDetailsModel;
import com.example.fragmentassignment.Model.OffersModel;
import com.example.fragmentassignment.Model.PaymentModel;
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
    Call<OffersModel> Data (@FieldMap Map<String, String> params,@Header("Authorization") String authHeader);

    @FormUrlEncoded
    @POST("/manazil1.0/services/")
        //@POST("/demos-mxprm/services/")
    Call<HospitalModel> Data1 (@FieldMap Map<String, String> params,@Header("Authorization") String authHeader);

    @FormUrlEncoded
    @POST("/manazil1.0/services/")
        //@POST("/demos-mxprm/services/")
    Call<DentalModel> Data2 (@FieldMap Map<String, String> params, @Header("Authorization") String authHeader);

    @FormUrlEncoded
    @POST("/manazil1.0/services/")
        //@POST("/demos-mxprm/services/")
    Call<OfferDetailsModel> Data3 (@FieldMap Map<String, String> params, @Header("Authorization") String authHeader);

    @FormUrlEncoded
    @POST("/manazil1.0/services/")
        //@POST("/demos-mxprm/services/")
    Call<PaymentModel> Data4 (@FieldMap Map<String, String> params, @Header("Authorization") String authHeader);
}
