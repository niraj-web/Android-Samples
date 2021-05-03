package com.example.offersassignment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.offersassignment.Model.OfferDetailsModel;

public class DETAILS extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static OfferDetailsModel offerDetailsModel;
    private static Context context;


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private String detail;
    Context mcontext;

    public DETAILS(String detail, Context context) {
        // Required empty public constructor
        this.detail = detail;
        this.mcontext =context;
    }

    // TODO: Rename and change types and number of parameters
    public static DETAILS newInstance(String param1, String param2) {
        DETAILS fragment = new DETAILS(offerDetailsModel.getData().getOfferDetails().getOfferBrandDesc(), context);
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
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView tvdetails = (TextView )view.findViewById(R.id.tvdetails);
        tvdetails.setText(detail);
        Log.e("my",detail);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_details, container, false);
        return rootView;
    }
}
