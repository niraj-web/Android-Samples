package com.example.offersassignment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.offersassignment.Adapter.OfferAdapter;
import com.example.offersassignment.Model.OffersModel;
import com.example.offersassignment.Model.PaymentModel;
import com.example.offersassignment.utils.ApiRequestHelper;
import com.example.offersassignment.utils.Utils;

import java.util.HashMap;

public class PaymentActivity extends BaseActivity {

    private PaymentModel paymentModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        offerBuyNow();
    }

   private void offerBuyNow() {
        ProgressDialog dialog = new ProgressDialog(PaymentActivity.this);
        dialog.setMessage("Please Wait while data loading");
        dialog.show();

        HashMap<String, String> params = new HashMap<>();
        params.put("xAction","offerBuyNow");
        params.put("userID", "3");
        params.put("langCode", "en");
        params.put("vatPoint", "500");
        params.put("price","500.00");
        params.put("vatPrice","25.0");
        params.put("offerID","119");
        params.put("categoryTypeID","1");
        params.put("offerExpiryDate","30th%20Apr%202021");
        params.put("offerQuantity","1");
        params.put("point","10000");
        params.put("deviceToken","dFiMBWhBQEuckw7M2xw98T%3AAPA91bF8gXI3UzXysRa7TkF3NDrS7ENWPAHjjp_ZdBhoZnsIg32QkXVTfDF3YEKWijeXQJbnDVQIC59u-rUY9hWbhh-Y8NR-JvPoXPt8nG1Ijjg6w0FL0qGrim-pHbn16n_mj-PeRNMR");
        if (cd.isConnectingToInternet()) {
            app.getApiRequestHelper().updateProjectAssign4(params, new ApiRequestHelper.OnRequestComplete() {
                @Override
                public void onSuccess(Object object) {
                    paymentModel = (PaymentModel) object;
//                    Utils.showLongToast(mContext,projectAssModel.getMsg());
                    if (paymentModel != null) {
                        if (paymentModel.getStatus()  && paymentModel.getIsUserAuthTokenValid() != null) {


                            TextView total = (TextView) findViewById(R.id.total);
                            total.setText(paymentModel.getData().getTotalAmount() + " AED OR " + paymentModel.getData().getOrderPoint() + "PTS");

                        } else {

                        }
                    } else {
                        Utils.showLongToast(PaymentActivity.this, Utils.UNPROPER_RESPONSE);
                    }
                    dialog.dismiss();
                }
                @Override
                public void onFailure(String apiResponse) {
                    Log.e("in", "error " + apiResponse);
                    Utils.showLongToast(PaymentActivity.this, apiResponse);
                }
            });
        } else {
            Utils.alert_dialog(PaymentActivity.this);
        }
    }

    @Override
    protected int getActivityLayout() {
        return R.layout.activity_payment;
    }
}