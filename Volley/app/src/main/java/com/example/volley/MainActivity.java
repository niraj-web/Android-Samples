package com.example.volley;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.nfc.Tag;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.util.Log;
import android.widget.ImageView;

import com.android.volley.AuthFailureError;
import com.android.volley.Cache;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {


    private Object Const;
    private Object imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getData();
        array();
        string();
        getParams();
        getHeaders();
        Network();
        cache();
        cache1();
        cancel();
        remove();
        delete();
        Cancel();

    }

    private void getData() {
        String tag_json_obj = "json_obj_req";

        String url = "https://api.androidhive.info/volley/person_object.json";

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("Tag", response.toString());

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        VolleyLog.d("Tag", "ERROR: " + error.getMessage());

                    }

                });

        AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);
    }

    private void array() {
        String tag_json_array = "json_array_req";

        String url = "https://api.androidhive.info/volley/person_array.json";
        ProgressDialog pDialog = new ProgressDialog(this);
        pDialog.setMessage("Losding0..");
        pDialog.show();

        JsonArrayRequest req = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.d("Tag", response.toString());
                pDialog.hide();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                VolleyLog.d("Tag", "Error:  " + error.getMessage());
                pDialog.hide();
            }
        });
        AppController.getInstance().addToRequestQueue(req, tag_json_array);
    }

    public void string() {
        String tag_string_req = "string_req";

        String url = "https://api.androidhive.info/volley/string_response.html";

        ProgressDialog pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...");
        pDialog.show();

        StringRequest strReq = new StringRequest(Request.Method.GET,
                url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d("TAG", response.toString());
                pDialog.hide();

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("TAG", "Error: " + error.getMessage());
                pDialog.hide();
            }
        });

// Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);

    }

    private void getParams() {
        String tag_json_obj = "json_obj_req";

        String url = "https://api.androidhive.info/volley/person_object.json";

        ProgressDialog pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...");
        pDialog.show();

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
                url, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("TAG", response.toString());
                        pDialog.hide();
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("TAG", "Error: " + error.getMessage());
                pDialog.hide();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("name", "Androidhive");
                params.put("email", "abc@androidhive.info");
                params.put("password", "password123");

                return params;
            }

        };

// Adding request to request queue
        AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);
    }

    private void getHeaders() {
        String tag_json_obj = "json_obj_req";

        String url = "https://api.androidhive.info/volley/person_object.json";

        ProgressDialog pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...");
        pDialog.show();

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
                url, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("TAG", response.toString());
                        pDialog.hide();
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("TAG", "Error: " + error.getMessage());
                pDialog.hide();
            }
        }) {

            /**
             * Passing some request headers
             * */
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json");
                headers.put("apiKey", "xxxxxxxxxxxxxxx");
                return headers;
            }

        };

// Adding request to request queue
        AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);
    }

    private void Network() {
        ImageLoader imageLoader = AppController.getInstance().getmImageLoader();

// If you are using normal ImageView
        imageLoader.get(Const.toString(), new ImageLoader.ImageListener() {
            @Override
            public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate) {
                if (response.getBitmap() != null)
                {

                }
            }

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("TAG", "Image Load Error: " + error.getMessage());
            }
        });
    }
    private void cache()
    {
        Cache cache = AppController.getInstance().getmRequestQueue().getCache();
        Cache.Entry entry = cache.get("url");
        if(entry != null){
            try {
                String data = new String(entry.data, "UTF-8");
                // handle data, like converting it to xml, json, bitmap etc.,
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
    }
    private void cache1()
    {
        AppController.getInstance().getmRequestQueue().getCache().invalidate("url", true);
    }
//    private void off() {
//        StringRequest stringReq = new StringReq"url");
//        stringReq.setShouldCache(false);

//    }
    private void remove()
    {
        AppController.getInstance().getmRequestQueue().getCache().remove("url");
    }
    private void delete()
    {
        AppController.getInstance().getmRequestQueue().getCache().clear();
    }
    private void cancel() {
        String tag_json_array = "json_req";
        AppController.getInstance().getmRequestQueue().cancelAll("feed_request");
    }

    private void Cancel()
    {
        AppController.getInstance().getmRequestQueue().cancelAll("feed_request");
    }


}



