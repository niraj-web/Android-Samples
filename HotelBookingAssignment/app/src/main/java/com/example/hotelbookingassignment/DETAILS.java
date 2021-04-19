package com.example.hotelbookingassignment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hotelbookingassignment.Model.ProjectAssModel;

import butterknife.BindView;

public class DETAILS extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static ProjectAssModel projectAssModel;
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


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ACTIVE.
     */
    // TODO: Rename and change types and number of parameters
    public static DETAILS newInstance(String param1, String param2) {
        DETAILS fragment = new DETAILS(projectAssModel.getData().getRewardDetails().getVoucherDescription(), context);
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

        TextView details = (TextView )view.findViewById(R.id.tvdetails);
        details.setText(detail);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_details, container, false);

        return rootView;
    }
}
