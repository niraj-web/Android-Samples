package com.example.fragmentassignment;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fragmentassignment.Adapter.HospitalAdapter;
import com.example.fragmentassignment.Adapter.OfferAdapter;
import com.example.fragmentassignment.Model.HospitalModel;
import com.example.fragmentassignment.Model.OffersModel;
import com.example.fragmentassignment.utils.ApiRequestHelper;
import com.example.fragmentassignment.utils.Utils;

import java.util.HashMap;

public class SecondFragment extends BaseFragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private OffersModel offersModel;
    Context context;
    private RecyclerView offerlist;
    private HospitalModel hospitalModel;
    private RecyclerView hospitalList;


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SecondFragment newInstance(String param1, String param2) {
        SecondFragment fragment = new SecondFragment();
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
        getSubCategory();

    }

    private void getSubCategory() {
        ProgressDialog dialog = new ProgressDialog(getContext());
        dialog.setMessage("Please Wait while data loading");
        dialog.show();

        HashMap<String, String> params = new HashMap<>();
        params.put("xAction","getSubCategory");
        params.put("userID", "3");
        params.put("langCode", "en");
        params.put("categoryID", "7");
        params.put("deviceToken","dFiMBWhBQEuckw7M2xw98T%3AAPA91bF8gXI3UzXysRa7TkF3NDrS7ENWPAHjjp_ZdBhoZnsIg32QkXVTfDF3YEKWijeXQJbnDVQIC59u-rUY9hWbhh-Y8NR-JvPoXPt8nG1Ijjg6w0FL0qGrim-pHbn16n_mj-PeRNMR");
        if (cd.isConnectingToInternet()) {
            app.getApiRequestHelper().updateProjectAssign1(params, new ApiRequestHelper.OnRequestComplete() {
                @Override
                public void onSuccess(Object object) {
                    hospitalModel = (HospitalModel) object;
//                    Utils.showLongToast(mContext,projectAssModel.getMsg());
                    if (hospitalModel != null) {
                        if (hospitalModel.getStatus()  && hospitalModel.getIsUserAuthTokenValid() != null) {


                            hospitalList.setHasFixedSize(true);
                            hospitalList.setLayoutManager(new GridLayoutManager(getContext(),2));
                            HospitalAdapter hospitalAdapter = new HospitalAdapter(getContext(),hospitalModel.getData(),getActivity());
                            hospitalList.setAdapter(hospitalAdapter);

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

        hospitalList = (RecyclerView) view.findViewById(R.id.HospitalRecyclerView);

    }

    @Override
    protected int getActivityLayout() {
        return R.layout.fragment_second;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.fragment_second, container, false);
        return rootView;

        // return inflater.inflate(R.layout.fragment_home, container, false);
    }
}
