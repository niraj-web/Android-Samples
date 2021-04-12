package com.example.ratingassignment;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ratingassignment.Adapter.RedeemedAdapter;
import com.example.ratingassignment.Model.ProjectAssModel;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link REDEEMED#newInstance} factory method to
 * create an instance of this fragment.
 */
public class REDEEMED extends Fragment {



    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static ProjectAssModel projectAssModel;
    private static Context context;



    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private List<ProjectAssModel.Redeem> redeem;
    Context mContext;

    public REDEEMED(List<ProjectAssModel.Redeem> redeem, Context context) {
        // Required empty public constructor
        this.redeem = redeem;
        this.mContext = context;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment REDEEMED.
     */
    // TODO: Rename and change types and number of parameters
    public static REDEEMED newInstance(String param1, String param2) {
        REDEEMED fragment = new REDEEMED(projectAssModel.getData().getRedeem(),context);
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

        RecyclerView recyclerView = (RecyclerView)view.findViewById(R.id.RecyRedeemed);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        RedeemedAdapter RedeemedAdapter = new RedeemedAdapter(mContext,redeem);
        recyclerView.setAdapter(RedeemedAdapter);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview= inflater.inflate(R.layout.fragment_r_e_d_e_e_m_e_d, container, false);

    /*    RecyclerView recyclerView = (RecyclerView) rootview.findViewById(R.id.RecyRedeemed);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        RedeemedAdapter RedeemedAdapter = new RedeemedAdapter(REDEEMED.this,projectAssModel.getData().getRedeem());
        recyclerView.setAdapter(RedeemedAdapter);*/
        return rootview;
    }
}