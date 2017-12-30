package com.example.kholoudali.jeddahtourguide;

import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


public class RestraunatsFragment extends Fragment {

    private ListView restraunatsListView;
    public RestraunatsFragment() {
        // Required empty public constructor
    }

    public static RestraunatsFragment newInstance(String param1, String param2) {
        RestraunatsFragment fragment = new RestraunatsFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_restraunats, container, false);

        //Data
        ArrayList<Location> resturantsLocations = new ArrayList<Location>();
        resturantsLocations.add(new Location(getString(R.string.rest1),  ContextCompat.getDrawable(getActivity(), R.drawable.byblos) , getString(R.string.rest1_des)));
        resturantsLocations.add(new Location(getString(R.string.rest2),  ContextCompat.getDrawable(getActivity(), R.drawable.albaik) , getString(R.string.rest2_des)));
        resturantsLocations.add(new Location(getString(R.string.rest3),  ContextCompat.getDrawable(getActivity(), R.drawable.abuzaid) , getString(R.string.rest3_des)));

        //Set Adapter
        RestrauntsAdapter adapter = new RestrauntsAdapter(getActivity() , resturantsLocations);
        restraunatsListView = view.findViewById(R.id.resturant_listview);
        restraunatsListView.setAdapter(adapter);


        // Inflate the layout for this fragment
        return view;

    }


    public class RestrauntsAdapter extends ArrayAdapter<Location> {
        public RestrauntsAdapter(Context context, ArrayList<Location> restlist) {
            super(context, 0, restlist);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // Get the data item for this position
            Location location = getItem(position);
            // Check if an existing view is being reused, otherwise inflate the view
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.location_layout, parent, false);
            }
            // Lookup view for data population
            TextView locationName = (TextView) convertView.findViewById(R.id.Loc_Name);
            TextView locationDescription = (TextView) convertView.findViewById(R.id.Loc_desc);
            ImageView locationImage = (ImageView) convertView.findViewById(R.id.location_img);

            // Populate the data into the template view using the data object
            locationName.setText(location.getLocationName());
            locationDescription.setText(location.getLocationDescription());
            locationImage.setImageDrawable(location.getLocationImg());

            // Return the completed view to render on screen
            return convertView;
        }
    }
}
