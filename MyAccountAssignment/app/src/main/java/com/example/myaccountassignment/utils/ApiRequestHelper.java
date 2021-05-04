package com.example.myaccountassignment.utils;

import android.text.Html;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.myaccountassignment.Model.AccountModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiRequestHelper
{
    static Gson gson;
    private static com.example.myaccountassignment.utils.ApiRequestHelper instance;
    private App app;
    private Api appService;

    public static synchronized com.example.myaccountassignment.utils.ApiRequestHelper init(App app) {
        if (null == instance) {
            instance = new com.example.myaccountassignment.utils.ApiRequestHelper();
            instance.setApplication (app);
            gson = new GsonBuilder().setLenient ()
                    .registerTypeAdapterFactory (new NullStringToEmptyAdapterFactory ())   // new LoginResponseModelDeserializer ()
                    .create ();
            instance.createRestAdapter ();
        }
        return instance;
    }

    /**
     * REST Adapter Configuration
     */
    private void createRestAdapter() {
        Retrofit.Builder builder = new Retrofit.Builder ().baseUrl (Utils.BASE_URL).addConverterFactory (GsonConverterFactory.create (gson));
        Retrofit retrofit = builder.client (getClient ().build ()).build ();
        appService = retrofit.create (Api.class);
    }

    @NonNull
    public OkHttpClient.Builder getClient() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor ();
// set your desired log level
        logging.setLevel (HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder ();
        httpClient.readTimeout (60, TimeUnit.SECONDS);
        httpClient.connectTimeout (60, TimeUnit.SECONDS);
// add your other interceptors …

// add logging as last interceptor
        httpClient.interceptors ().add (logging);
        return httpClient;
    }

    /**
     * End REST Adapter Configuration
     */

    public App getApplication() {
        return app;
    }

    private void handle_fail_response(Throwable t, OnRequestComplete onRequestComplete) {
        if (t != null && t.getMessage () != null)
            Log.e ("server msg", Html.fromHtml (t.getMessage ()) + "");
        if (t != null && t.getMessage () != null) {
            if (t.getMessage ().contains ("Unable to resolve host")) {
                onRequestComplete.onFailure (Utils.NO_INTERNET_MSG);
            } else {
                onRequestComplete.onFailure (Html.fromHtml (t.getMessage ()) + "");
            }
        } else {
            onRequestComplete.onFailure (Utils.UNPROPER_RESPONSE);
        }
    }

    /**
     * API Calls
     */
   /* public void login(Map<String, String> params, final OnRequestComplete onRequestComplete) {
        Call<ProjectAssModel> call = appService.Project (params);
        call.enqueue (new Callback<ProjectAssModel>() {
            @Override
            public void onResponse(Call<ProjectAssModel> call, Response<ProjectAssModel> response) {
                if (response.isSuccessful ()) {
                    onRequestComplete.onSuccess (response.body ());
                } else {
                    onRequestComplete.onFailure (Html.fromHtml (response.message ()) + "");
                }
            }

            @Override
            public void onFailure(Call<ProjectAssModel> call, Throwable t) {
                handle_fail_response (t, onRequestComplete);
            }
        });
    }*/

    public void updateProjectAssign(Map<String, String> params, final OnRequestComplete onRequestComplete) {
        Call<AccountModel> call = appService.Data(params,"Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJUSEVfSVNTVUVSIiwiYXVkIjoiVEhFX0FVRElFTkNFIiwiaWF0IjoxNjIwMTEwMzQ3LCJuYmYiOjE2MjAxMTAzNTcsImV4cCI6MTYyMDE5Njc0NywiZGF0YSI6eyJpZCI6NjA5LCJkaXNwbGF5TmFtZSI6IkxpbmRhIFlvdW5nIiwiZW1haWwiOiJsaW5kYS55b3VuZy5xYUBnbWFpbC5jb20iLCJkZXZpY2VUb2tlbiI6ImRGaU1CV2hCUUV1Y2t3N00yeHc5OFQ6QVBBOTFiRjhnWEkzVXpYeXNSYTdUa0YzTkRyUzdFTldQQUhqanBfWmRCaG9abnNJZzMyUWtYVlRmREYzWUVLV2lqZVhRSmJuRFZRSUM1OXUtclVZOWhXYmhoLVk4TlItSnZQb1hQdDhuRzFJampnNncwRkwwcUdyaW0tcEhibjE2bl9tai1QZVJOTVIifX0.r6r0YCOvw_44gjLOme9tpA2qQIQXqQsEH-0zW2JHB2A");
        call.enqueue (new Callback<AccountModel>() {
            @Override
            public void onResponse(Call<AccountModel> call, Response<AccountModel> response) {
                if (response.isSuccessful ()) {
                    onRequestComplete.onSuccess (response.body ());
                }
                else {
                    onRequestComplete.onFailure (Html.fromHtml (response.message ()) + "");
                }
            }

            @Override
            public void onFailure(Call<AccountModel> call, Throwable t) {
                handle_fail_response (t, onRequestComplete);
            }
        });
    }


    /**
     * End API Calls
     */

    public void setApplication(App app) {
        this.app = app;
    }

    @NonNull
    private RequestBody createPartFromString(String descriptionString) {
        return RequestBody.create (MultipartBody.FORM, descriptionString);
    }

    public interface OnRequestComplete {
        void onSuccess(Object object);

        void onFailure(String apiResponse);
    }

    public static class NullStringToEmptyAdapterFactory<T> implements TypeAdapterFactory {
        public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {

            Class<T> rawType = (Class<T>) type.getRawType ();
            if (rawType != String.class) {
                return null;
            }
            return (TypeAdapter<T>) new StringAdapter ();
        }
    }

    public static class StringAdapter extends TypeAdapter<String> {
        public String read(JsonReader reader) throws IOException {
            if (reader.peek () == JsonToken.NULL) {
                reader.nextNull ();
                return "";
            }
            return reader.nextString ();
        }

        public void write(JsonWriter writer, String value) throws IOException {
            if (value == null) {
                writer.nullValue ();
                return;
            }
            writer.value (value);
        }
    }
}
