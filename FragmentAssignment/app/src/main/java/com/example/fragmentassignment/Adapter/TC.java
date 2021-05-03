package com.example.fragmentassignment.Adapter;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.fragmentassignment.Model.OfferDetailsModel;
import com.example.fragmentassignment.R;

public class TC extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static OfferDetailsModel offerDetailsModel;
    private static Context context;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private String termConditions;
    Context mcontext;

    public TC(String termConditions, Context context) {
        this.termConditions = termConditions;
        this.mcontext = context;
    }

    public static TC newInstance(String param1, String param2) {
        TC fragment = new TC(offerDetailsModel.getData().getOfferDetails().getTncDescription(), context);
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

        TextView tc = (TextView )view.findViewById(R.id.tc);
        tc.setText(termConditions);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_tc, container, false);

        return rootView;
    }
}
