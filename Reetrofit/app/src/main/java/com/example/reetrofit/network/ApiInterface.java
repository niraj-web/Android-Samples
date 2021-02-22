package com.example.reetrofit.network;

import com.example.reetrofit.model.Message;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface
{
    @GET("inbox.json")
    Call<List<Message>> getInbox();
}
