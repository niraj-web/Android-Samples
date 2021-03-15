package com.example.recaptcha;

import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.safetynet.SafetyNet;
import com.google.android.gms.safetynet.SafetyNetApi;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity
{
    private static final String TAG = MainActivity.class.getSimpleName();

    private static final String SAFETY_NET_API_SITE_KEY ="6LezDXEaAAAAAGoVwiCYcz8TcWucrGp_lbXaqPeE";

    private static final String URL_VERIFY_ON_SERVER = "https://api.androidhive.info/google-recaptcha-verfication.php";

    @BindView(R.id.input_feedback)
    EditText inputFeedback;

    @BindView(R.id.layout_feedback_form)
    LinearLayout layoutFeedbackForm;

    @BindView(R.id.message_feedback_done)
    TextView messageFeedbackDone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(getString(R.string.feedback));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Toast.makeText(getApplicationContext(),"Always check Android Studio 'Logcat' for errors!", Toast.LENGTH_LONG).show();



    }

    @OnClick(R.id.btn_send)
    public void validateCaptcha()
    {
        String feedback = inputFeedback.getText().toString().trim();

        if (TextUtils.isEmpty(feedback))
        {
            Toast.makeText(getApplicationContext(), "Enter feedback",Toast.LENGTH_LONG).show();
            return;
        }
        SafetyNet.getClient(this).verifyWithRecaptcha(SAFETY_NET_API_SITE_KEY)
                .addOnSuccessListener(this, new OnSuccessListener<SafetyNetApi.RecaptchaTokenResponse>() {
                    @Override
                    public void onSuccess(SafetyNetApi.RecaptchaTokenResponse recaptchaTokenResponse)
                    {
                        Log.d(TAG, "onSuccess");

                        if (!recaptchaTokenResponse.getTokenResult().isEmpty())
                        {
                            verifyTokenOnServer(recaptchaTokenResponse.getTokenResult());
                        }

                    }
                })
                .addOnFailureListener(this, new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e)
                    {
                        if (e instanceof ApiException)
                        {
                            ApiException apiException = (ApiException) e;
                            Log.d(TAG, "Error message:  " + CommonStatusCodes.getStatusCodeString(apiException.getStatusCode()));
                        }
                        else
                        {
                            Log.d(TAG, "Unknown type of error: " + e.getMessage());
                        }

                    }
                });
    }

    private void verifyTokenOnServer(String tokenResult)
    {
        Log.d(TAG, "Captcha Token" + tokenResult);

        StringRequest strReq = new StringRequest(Request.Method.POST, URL_VERIFY_ON_SERVER, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, response.toString());

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    boolean success = jsonObject.getBoolean("success");
                    String message = jsonObject.getString("message");

                    if (success) {
                        layoutFeedbackForm.setVisibility(View.GONE);
                        messageFeedbackDone.setVisibility(View.VISIBLE);
                    } else {
                        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "Json Error: " + e.getMessage(), Toast.LENGTH_LONG).show();

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                Log.e(TAG, "Error: " + error.getMessage());

            }
        })
        {
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String> params = new HashMap<>();
                params.put("recaptcha-response",tokenResult);

                return params;
            }

        };
        MyApplication.getInstance().addToRequestQueue(strReq);
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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}