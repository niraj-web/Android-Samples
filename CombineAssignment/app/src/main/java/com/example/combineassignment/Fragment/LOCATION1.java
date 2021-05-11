package com.example.combineassignment.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.combineassignment.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;

public class LOCATION1 extends Fragment {

    GoogleMap map;
    MapView mapView;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    // TODO: Rename and change types and number of parameters
    public static LOCATION1 newInstance(String param1, String param2) {
        LOCATION1 fragment = new LOCATION1();
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
    /*  @Override
      public void onViewCreated(View view, Bundle savedInstanceState) {
          super.onViewCreated(view, savedInstanceState);

          mapView = view.findViewById(R.id.mapView);

          if (mapView != null)
          {
              mapView.onCreate(null);
              mapView.onResume();
              mapView.getMapAsync(this);
          }

      }
  */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_location1, container, false);

        return rootView;
    }

  /*  @Override
    public void onMapReady(GoogleMap googleMap) {

        MapsInitializer.initialize(getContext());
        map = googleMap;

    }*/

}
