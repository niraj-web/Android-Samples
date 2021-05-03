package com.example.fragmentassignment;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fragmentassignment.Adapter.DentalOfferAdapter;
import com.example.fragmentassignment.Adapter.HospitalAdapter;
import com.example.fragmentassignment.Model.DentalModel;
import com.example.fragmentassignment.Model.HospitalModel;
import com.example.fragmentassignment.Model.OffersModel;
import com.example.fragmentassignment.utils.ApiRequestHelper;
import com.example.fragmentassignment.utils.Utils;

import java.util.HashMap;

public class ThirdFragment extends BaseFragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private OffersModel offersModel;
    Context context;
    private RecyclerView offerlist;
    private DentalModel dentalModel;


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ThirdFragment newInstance(String param1, String param2) {
        ThirdFragment fragment = new ThirdFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

       getoffers();
    }

    private void getoffers() {
        ProgressDialog dialog = new ProgressDialog(getContext());
        dialog.setMessage("Please Wait while data loading");
        dialog.show();

        HashMap<String, String> params = new HashMap<>();
        params.put("xAction","getoffers");
        params.put("userID", "3");
        params.put("langCode", "en");
        params.put("categoryID", "7");
        params.put("latitude","20.5377023");
        params.put("longitude","74.8751956");
        params.put("subcategoryID","24");
        params.put("deviceToken","dFiMBWhBQEuckw7M2xw98T%3AAPA91bF8gXI3UzXysRa7TkF3NDrS7ENWPAHjjp_ZdBhoZnsIg32QkXVTfDF3YEKWijeXQJbnDVQIC59u-rUY9hWbhh-Y8NR-JvPoXPt8nG1Ijjg6w0FL0qGrim-pHbn16n_mj-PeRNMR");
        if (cd.isConnectingToInternet()) {
            app.getApiRequestHelper().updateProjectAssign2(params, new ApiRequestHelper.OnRequestComplete() {
                @Override
                public void onSuccess(Object object) {
                    dentalModel = (DentalModel) object;
//                    Utils.showLongToast(mContext,projectAssModel.getMsg());
                    if (dentalModel != null) {
                        if (dentalModel.getStatus()  && dentalModel.getIsUserAuthTokenValid() != null) {


                            offerlist.setHasFixedSize(true);
                            offerlist.setLayoutManager(new GridLayoutManager(getContext(),2));
                            DentalOfferAdapter dentalOfferAdapter = new DentalOfferAdapter(getContext(),dentalModel.getData(),getActivity());
                            offerlist.setAdapter(dentalOfferAdapter);


                        } else {

                        }
                    } else {
                        Utils.showLongToast(getContext(), Utils.UNPROPER_RESPONSE);
                    }
                    dialog.dismiss();
                }
                @Override
                public void onFailure(String apiResponse) {
                    Log.e("in", "error " + apiResponse);
                    Utils.showLongToast(getContext(), apiResponse);
                }
            });
        } else {
            Utils.alert_dialog(getContext());
        }
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        offerlist = (RecyclerView) view.findViewById(R.id.DentalRecyclerView);

    }

    @Override
    protected int getActivityLayout() {
        return R.layout.fragment_third;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.fragment_third, container, false);
        return rootView;

        // return inflater.inflate(R.layout.fragment_home, container, false);
    }
}
