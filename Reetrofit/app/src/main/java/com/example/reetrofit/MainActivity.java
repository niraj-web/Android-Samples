package com.example.reetrofit;

import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;

import com.android.volley.AuthFailureError;
import com.android.volley.Cache;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.example.reetrofit.adapter.MessagesAdapter;
import com.example.reetrofit.model.Message;
import com.example.reetrofit.network.ApiClient;
import com.example.reetrofit.network.ApiInterface;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Handler;
import android.util.Log;
import android.view.ActionMode;
import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.ImageView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;

import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener, MessagesAdapter.MessageAdapterListener {
    private List<Message> messages = new ArrayList<>();
    private RecyclerView recyclerView;
    private MessagesAdapter mAdapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    private ActionModeCallback actionModeCallback;
    private ActionMode actionMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

            }
        });

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);
        swipeRefreshLayout.setOnRefreshListener(this);

        mAdapter = new MessagesAdapter(this, messages, this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(mAdapter);

        actionModeCallback = new ActionModeCallback();

        // show loader and fetch messages
        swipeRefreshLayout.post(
                new Runnable() {
                    @Override
                    public void run() {
                        getInbox();
                    }
                }
        );
    }

    /**
     * Fetches mail messages by making HTTP request
     * url: https://api.androidhive.info/json/inbox.json
     */
    private void getInbox() {
        swipeRefreshLayout.setRefreshing(true);

        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<List<Message>> call = apiService.getInbox();
        call.enqueue(new Callback<List<Message>>() {
            @Override
            public void onResponse(Call<List<Message>> call, Response<List<Message>> response) {
                // clear the inbox
                messages.clear();

                // add all the messages
                // messages.addAll(response.body());

                // TODO - avoid looping
                // the loop was performed to add colors to each message
                for (Message message : response.body()) {
                    // generate a random color
                    message.setColor(getRandomMaterialColor("400"));
                    messages.add(message);
                    Log.e("TAG", "onResponse: "+message .getMessage());
                }

                mAdapter.notifyDataSetChanged();
                swipeRefreshLayout.setRefreshing(false);
            }

            @Override
            public void onFailure(Call<List<Message>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Unable to fetch json: " + t.getMessage(), Toast.LENGTH_LONG).show();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    /**
     * chooses a random color from array.xml
     */
    private int getRandomMaterialColor(String typeColor) {
        int returnColor = Color.GRAY;
        int arrayId = getResources().getIdentifier("mdcolor_" + typeColor, "array", getPackageName());

        if (arrayId != 0) {
            TypedArray colors = getResources().obtainTypedArray(arrayId);
            int index = (int) (Math.random() * colors.length());
            returnColor = colors.getColor(index, Color.GRAY);
            colors.recycle();
        }
        return returnColor;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_search) {
            Toast.makeText(getApplicationContext(), "Search...", Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onRefresh() {
        // swipe refresh is performed, fetch the messages again
        getInbox();
    }

    @Override
    public void onIconClicked(int position) {
       /* if (actionMode == null) {
            actionMode = startSupportActionMode(actionModeCallback);
        }*/

        toggleSelection(position);
    }

    @Override
    public void onIconImportantClicked(int position) {
        // Star icon is clicked,
        // mark the message as important
        Message message = messages.get(position);
        message.setImportant(!message.isImportant());
        messages.set(position, message);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onMessageRowClicked(int position) {
        // verify whether action mode is enabled or not
        // if enabled, change the row state to activated
        if (mAdapter.getSelectedItemCount() > 0) {
            enableActionMode(position);
        } else {
            // read the message which removes bold from the row
            Message message = messages.get(position);
            message.setRead(true);
            messages.set(position, message);
            mAdapter.notifyDataSetChanged();

            Toast.makeText(getApplicationContext(), "Read: " + message.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRowLongClicked(int position) {
        // long press is performed, enable action mode
        enableActionMode(position);
    }

    private void enableActionMode(int position) {
       /* if (actionMode == null) {
            actionMode = startSupportActionMode(actionModeCallback);
        }*/
        toggleSelection(position);
    }

    private void toggleSelection(int position) {
        mAdapter.toggleSelection(position);
        int count = mAdapter.getSelectedItemCount();

        if (count == 0) {
            actionMode.finish();
        } else {
            actionMode.setTitle(String.valueOf(count));
            actionMode.invalidate();
        }
    }


    private class ActionModeCallback implements ActionMode.Callback {
        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            mode.getMenuInflater().inflate(R.menu.menu_action_mode, menu);

            // disable swipe refresh if action mode is enabled
            swipeRefreshLayout.setEnabled(false);
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            switch (item.getItemId()) {
                case R.id.action_delete:
                    // delete all the selected messages
                    deleteMessages();
                    mode.finish();
                    return true;

                default:
                    return false;
            }
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {
            mAdapter.clearSelections();
            swipeRefreshLayout.setEnabled(true);
            actionMode = null;
            recyclerView.post(new Runnable() {
                @Override
                public void run() {
                    mAdapter.resetAnimationIndex();
                    // mAdapter.notifyDataSetChanged();
                }
            });
        }
    }

    // deleting the messages from recycler view
    private void deleteMessages() {
        mAdapter.resetAnimationIndex();
        List<Integer> selectedItemPositions =
                mAdapter.getSelectedItems();
        for (int i = selectedItemPositions.size() - 1; i >= 0; i--) {
            mAdapter.removeData(selectedItemPositions.get(i));
        }
        mAdapter.notifyDataSetChanged();
    }

    //volley
    String url = "https:// string_url/";
    StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new com.android.volley.Response.Listener<String>() {
        @Override
        public void onResponse(String response) {

        }
    }, new com.android.volley.Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {

        }

    });
//3
    String url1 = "https:// json_url/";
    JsonObjectRequest
            jsonObjectRequest
            = new JsonObjectRequest(
            Request.Method.GET,
            url,
            null,
            new com.android.volley.Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {

                }
            }, new com.android.volley.Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {

        }
    });
//4
    JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new com.android.volley.Response.Listener<JSONArray>() {
        @Override
        public void onResponse(JSONArray response) {

        }
    },
            new com.android.volley.Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }

            });

    public JsonArrayRequest getJsonArrayRequest() {
        return jsonArrayRequest;
    }

            //5
    int max_width = 10;
    int max_height = 20;

    String URL = "http:// image_url.png";

    ImageRequest imageRequest = new ImageRequest(URL, new com.android.volley.Response.Listener<Bitmap>() {
        @Override
        public void onResponse(Bitmap response)
        {
            ImageView imageView = (ImageView) findViewById(R.id.icon_star);

            imageView.setImageBitmap(response);
        }
    }, requestQueue.add(imageRequest);

//6
    String tag_json_obj = "json_obj_req";

    String url2 = "https:// api.xyz.info/volley/person_object.json";

    ProgressDialog pDialog = new ProgressDialog(this);

    JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, url, null, new com.android.volley.Response.Listener<JSONObject>() {
        @Override
        public void onResponse(JSONObject response) {
            Log.d("TAG", response.toString());
            pDialog.hide();
        }
    }, new com.android.volley.Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            VolleyLog.d("TAG", "Error: " + error.getMessage());
            pDialog.hide();

        }
    });
        @Override
        public Map getParams()
        {
            Map params = new HashMap();
            params.put("name", "Androidhive");
            params.put("email", "abc@androidhive.info");
            params.put("password", "password123");

            return params;
        }

    String tag_json_obj2 = "json_obj_req";

        String url3 = "https:// api.androidhive.info/volley/person_object.json";

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Method.POST, url, null, new com.android.volley.Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("TAG", response.toString());
                pDialog.hide();

            }

        },
                new com.android.volley.Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        VolleyLog.d("Tag", "Error: "
                                + error.getMessage());
                        pDialog.hide();
                    }

            })
        {
            @Override
        public Map getHeaders() throws AuthFailureError
        {
            HashMap headers = new HashMap();
            headers.put("Content-Type", "application/json");
            headers.put("apiKey", "xxxxxxxxxxxxxxx");
            return headers;
        }

    };AppController.getInstance()
        .addToRequestQueue(jsonObjReq, tag_json_obj);


    Cache cache = AppController.getInstance().getRequestQueue().getCache();

    Cache.Entry entry = cache.get(url);

    public Cache.Entry getEntry() {
        return entry;
    }if () {
        try {
            String
                    data
                    = new String(entry.data, "UTF-8");
            // handle data, like converting it
            // to xml, json, bitmap etc.,
        }
        catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
else
        {
        // If cached response doesn't exists
        }

// Invalidate cache
        AppController.getInstance()
        .getRequestQueue()
        .getCache()
        .invalidate(url, true);

// Turning off cache
// String request
        StringRequest
        stringReq
        = new StringRequest(....);

// disable cache
        stringReq.setShouldCache(false);

// Deleting cache for particular cache</strong>
        AppController.getInstance()
        .getRequestQueue()
        .getCache()
        .remove(url);

// Deleting all the cache
        AppController.getInstance()
        .getRequestQueue()
        .getCache()
        .clear(url);


        }




