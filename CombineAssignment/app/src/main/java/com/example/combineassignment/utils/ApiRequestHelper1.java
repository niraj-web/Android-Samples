package com.example.combineassignment.utils;

import android.text.Html;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.combineassignment.Model.DentalModel;
import com.example.combineassignment.Model.HospitalModel;
import com.example.combineassignment.Model.OfferDetailsModel;
import com.example.combineassignment.Model.OffersModel;
import com.example.combineassignment.Model.PaymentModel;
import com.example.combineassignment.Model.RoomDetailsModel;
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

public class ApiRequestHelper1 {

    static Gson gson;
    private static com.example.combineassignment.utils.ApiRequestHelper1 instance;
    private App app;
    private Api2 appService;

    public static synchronized com.example.combineassignment.utils.ApiRequestHelper1 init(App app) {
        if (null == instance) {
            instance = new com.example.combineassignment.utils.ApiRequestHelper1();
            instance.setApplication (app);
            gson = new GsonBuilder().setLenient ()
                    .registerTypeAdapterFactory (new ApiRequestHelper1.NullStringToEmptyAdapterFactory())   // new LoginResponseModelDeserializer ()
                    .create ();
            instance.createRestAdapter ();
        }
        return instance;
    }

    /**
     * REST Adapter Configuration
     */
    private void createRestAdapter() {
        Retrofit.Builder builder = new Retrofit.Builder ().baseUrl (Utils.BASE_URL1).addConverterFactory (GsonConverterFactory.create (gson));
        Retrofit retrofit = builder.client (getClient ().build ()).build ();
        appService = retrofit.create (Api2.class);
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

    private void handle_fail_response(Throwable t, ApiRequestHelper1.OnRequestComplete onRequestComplete) {
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

    public void updateProjectAssign1(Map<String, String> params, final ApiRequestHelper1.OnRequestComplete onRequestComplete) {
        Call<OffersModel> call = appService.Data1(params,"ar","Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJUSEVfSVNTVUVSIiwiYXVkIjoiVEhFX0FVRElFTkNFIiwiaWF0IjoxNjIwNjQ5ODExLCJuYmYiOjE2MjA2NDk4MjEsImV4cCI6MTYyMDczNjIxMSwiZGF0YSI6eyJpZCI6NjA5LCJkaXNwbGF5TmFtZSI6IkxpbmRhIFlvdW5nIiwiZW1haWwiOiJsaW5kYS55b3VuZy5xYUBnbWFpbC5jb20ifX0.Bfe9HXVPg3HcIvmwjz55_vrfYprr45j53LFm1n7XaUc");
        call.enqueue (new Callback<OffersModel>() {
            @Override
            public void onResponse(Call<OffersModel> call, Response<OffersModel> response) {
                if (response.isSuccessful ()) {
                    onRequestComplete.onSuccess (response.body ());
                } else {
                    onRequestComplete.onFailure (Html.fromHtml (response.message ()) + "");
                }
            }

            @Override
            public void onFailure(Call<OffersModel> call, Throwable t) {
                handle_fail_response (t, onRequestComplete);
            }
        });
    }

    public void updateProjectAssign2(Map<String, String> params, final ApiRequestHelper1.OnRequestComplete onRequestComplete) {
        Call<HospitalModel> call = appService.Data2(params,"ar","Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJUSEVfSVNTVUVSIiwiYXVkIjoiVEhFX0FVRElFTkNFIiwiaWF0IjoxNjIwNjQ5ODExLCJuYmYiOjE2MjA2NDk4MjEsImV4cCI6MTYyMDczNjIxMSwiZGF0YSI6eyJpZCI6NjA5LCJkaXNwbGF5TmFtZSI6IkxpbmRhIFlvdW5nIiwiZW1haWwiOiJsaW5kYS55b3VuZy5xYUBnbWFpbC5jb20ifX0.Bfe9HXVPg3HcIvmwjz55_vrfYprr45j53LFm1n7XaUc");
        call.enqueue (new Callback<HospitalModel>() {
            @Override
            public void onResponse(Call<HospitalModel> call, Response<HospitalModel> response) {
                if (response.isSuccessful ()) {
                    onRequestComplete.onSuccess (response.body ());
                } else {
                    onRequestComplete.onFailure (Html.fromHtml (response.message ()) + "");
                }
            }

            @Override
            public void onFailure(Call<HospitalModel> call, Throwable t) {
                handle_fail_response (t, onRequestComplete);
            }
        });
    }

    public void updateProjectAssign3(Map<String, String> params, final ApiRequestHelper1.OnRequestComplete onRequestComplete) {
        Call<DentalModel> call = appService.Data3(params,"ar","Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJUSEVfSVNTVUVSIiwiYXVkIjoiVEhFX0FVRElFTkNFIiwiaWF0IjoxNjIwNjQ5ODExLCJuYmYiOjE2MjA2NDk4MjEsImV4cCI6MTYyMDczNjIxMSwiZGF0YSI6eyJpZCI6NjA5LCJkaXNwbGF5TmFtZSI6IkxpbmRhIFlvdW5nIiwiZW1haWwiOiJsaW5kYS55b3VuZy5xYUBnbWFpbC5jb20ifX0.Bfe9HXVPg3HcIvmwjz55_vrfYprr45j53LFm1n7XaUc");
        call.enqueue (new Callback<DentalModel>() {
            @Override
            public void onResponse(Call<DentalModel> call, Response<DentalModel> response) {
                if (response.isSuccessful ()) {
                    onRequestComplete.onSuccess (response.body ());
                } else {
                    onRequestComplete.onFailure (Html.fromHtml (response.message ()) + "");
                }
            }

            @Override
            public void onFailure(Call<DentalModel> call, Throwable t) {
                handle_fail_response (t, onRequestComplete);
            }
        });
    }

    public void updateProjectAssign4(Map<String, String> params, final ApiRequestHelper1.OnRequestComplete onRequestComplete) {
        Call<OfferDetailsModel> call = appService.Data4(params,"ar","Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJUSEVfSVNTVUVSIiwiYXVkIjoiVEhFX0FVRElFTkNFIiwiaWF0IjoxNjIwNjQ5ODExLCJuYmYiOjE2MjA2NDk4MjEsImV4cCI6MTYyMDczNjIxMSwiZGF0YSI6eyJpZCI6NjA5LCJkaXNwbGF5TmFtZSI6IkxpbmRhIFlvdW5nIiwiZW1haWwiOiJsaW5kYS55b3VuZy5xYUBnbWFpbC5jb20ifX0.Bfe9HXVPg3HcIvmwjz55_vrfYprr45j53LFm1n7XaUc");
        call.enqueue (new Callback<OfferDetailsModel>() {
            @Override
            public void onResponse(Call<OfferDetailsModel> call, Response<OfferDetailsModel> response) {
                if (response.isSuccessful ()) {
                    onRequestComplete.onSuccess (response.body ());
                } else {
                    onRequestComplete.onFailure (Html.fromHtml (response.message ()) + "");
                }
            }

            @Override
            public void onFailure(Call<OfferDetailsModel> call, Throwable t) {
                handle_fail_response (t, onRequestComplete);
            }
        });
    }

    public void updateProjectAssign5(Map<String, String> params, final ApiRequestHelper1.OnRequestComplete onRequestComplete) {
        Call<PaymentModel> call = appService.Data5(params,"ar","Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJUSEVfSVNTVUVSIiwiYXVkIjoiVEhFX0FVRElFTkNFIiwiaWF0IjoxNjIwNjQ5ODExLCJuYmYiOjE2MjA2NDk4MjEsImV4cCI6MTYyMDczNjIxMSwiZGF0YSI6eyJpZCI6NjA5LCJkaXNwbGF5TmFtZSI6IkxpbmRhIFlvdW5nIiwiZW1haWwiOiJsaW5kYS55b3VuZy5xYUBnbWFpbC5jb20ifX0.Bfe9HXVPg3HcIvmwjz55_vrfYprr45j53LFm1n7XaUc");
        call.enqueue (new Callback<PaymentModel>() {
            @Override
            public void onResponse(Call<PaymentModel> call, Response<PaymentModel> response) {
                if (response.isSuccessful ()) {
                    onRequestComplete.onSuccess (response.body ());
                } else {
                    onRequestComplete.onFailure (Html.fromHtml (response.message ()) + "");
                }
            }

            @Override
            public void onFailure(Call<PaymentModel> call, Throwable t) {
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
            return (TypeAdapter<T>) new ApiRequestHelper1.StringAdapter();
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
