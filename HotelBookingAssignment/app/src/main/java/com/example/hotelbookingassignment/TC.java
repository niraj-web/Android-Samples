package com.example.hotelbookingassignment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.hotelbookingassignment.Model.ProjectAssModel;

import java.text.BreakIterator;

public class TC extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static ProjectAssModel projectAssModel;
    private static Context context;


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private String condition;
    Context mcontext;


    public TC(String condition, Context context) {
        // Required empty public constructor
        this.condition = condition;
        this.mcontext = context;
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
    public static TC newInstance(String param1, String param2) {
        TC fragment = new TC(projectAssModel.getData().getRewardDetails().getTncDescription(),context);
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

        TextView tvTC = (TextView )view.findViewById(R.id.tvTC);
        tvTC.setText(condition);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.fragment_tc, container, false);

        return rootView;
    }
}
