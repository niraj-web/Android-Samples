package com.example.hotelbookingassignment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hotelbookingassignment.Adapter.LocationAdapter;
import com.example.hotelbookingassignment.Model.ProjectAssModel;

import java.util.List;

public class LOCATION extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static ProjectAssModel projectAssModel;
    private static Context context;


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private List<ProjectAssModel.Location> locationList;
    Context mcontext;

    public LOCATION(List<ProjectAssModel.Location> locationList, Context context) {
        // Required empty public constructor
        this.locationList = locationList;
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
    public static LOCATION newInstance(String param1, String param2) {
        LOCATION fragment = new LOCATION(projectAssModel.getData().getLocation(), context);
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

       /* RecyclerView locationList = (RecyclerView)view.findViewById(R.id.RecyLocation);
        locationList.setHasFixedSize(true);
        locationList.setLayoutManager(new LinearLayoutManager(getActivity()));
        LocationAdapter locationAdapter = new LocationAdapter(projectAssModel.getData().getLocation(),mcontext);
        locationList.setAdapter(locationAdapter);*/

      RecyclerView recyclerView = (RecyclerView)view.findViewById(R.id.RecyLocation);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        LocationAdapter locationAdapter = new LocationAdapter(locationList,mcontext);
        recyclerView.setAdapter(locationAdapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_location, container, false);

        return rootView;
    }
}
