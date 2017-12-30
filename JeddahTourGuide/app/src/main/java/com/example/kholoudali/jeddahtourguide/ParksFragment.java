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


public class ParksFragment extends Fragment {

    private ListView parkListView;
    public ParksFragment() {
        // Required empty public constructor
    }

    public static ParksFragment newInstance(String param1, String param2) {
        ParksFragment fragment = new ParksFragment();
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
        View view= inflater.inflate(R.layout.fragment_parks, container, false);

        //Data
        ArrayList<Location> parksLocations = new ArrayList<Location>();
        parksLocations.add(new Location(getString(R.string.park1),  ContextCompat.getDrawable(getActivity(), R.drawable.aquirum) , getString(R.string.park1_desc)));
        parksLocations.add(new Location(getString(R.string.park2),  ContextCompat.getDrawable(getActivity(), R.drawable.silversands) , getString(R.string.park2_desc)));

        //Set Adapter
        shoppingListAdapter adapter = new shoppingListAdapter(getActivity() , parksLocations);
        parkListView = view.findViewById(R.id.parks_listview);
        parkListView.setAdapter(adapter);


        // Inflate the layout for this fragment
        return view;

    }


    public class shoppingListAdapter extends ArrayAdapter<Location> {
        public shoppingListAdapter(Context context, ArrayList<Location> landmarkList) {
            super(context, 0, landmarkList);
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
