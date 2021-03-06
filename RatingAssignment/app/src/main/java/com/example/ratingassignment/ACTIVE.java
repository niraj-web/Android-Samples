package com.example.ratingassignment;

import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ratingassignment.Adapter.ActiveAdapter;
import com.example.ratingassignment.Adapter.RedeemedAdapter;
import com.example.ratingassignment.Model.ProjectAssModel;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ACTIVE#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ACTIVE extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static ProjectAssModel projectAssModel;
    private static Context context;


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private List<ProjectAssModel.Active> active;
    Context mcontext;

    public ACTIVE(List<ProjectAssModel.Active> active,Context context) {
        // Required empty public constructor
        this.active = active;
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
    public static ACTIVE newInstance(String param1, String param2) {
        ACTIVE fragment = new ACTIVE(projectAssModel.getData().getActive(),context);
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


        RecyclerView recyclerView = (RecyclerView)view.findViewById(R.id.RecyActive);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        ActiveAdapter ActiveAdapter = new ActiveAdapter(active,mcontext);
        recyclerView.setAdapter(ActiveAdapter);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_a_c_t_i_v_e, container, false);

     /*   RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.RecyActive);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        ActiveAdapter ActiveAdapter = new ActiveAdapter(ACTIVE.this,projectAssModel.getData().getActive());
        recyclerView.setAdapter(ActiveAdapter);*/
        return rootView;
    }
}