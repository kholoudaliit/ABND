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


public class LandmarkFragment extends Fragment {

    private ListView landmarksListView;
    public LandmarkFragment() {
        // Required empty public constructor
    }

    public static LandmarkFragment newInstance(String param1, String param2) {
        LandmarkFragment fragment = new LandmarkFragment();
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
        View view= inflater.inflate(R.layout.fragment_landmark, container, false);

        //Data
        ArrayList<Location> landmarksLocations = new ArrayList<Location>();
        landmarksLocations.add(new Location(getString(R.string.nasif_house_name),  ContextCompat.getDrawable(getActivity(), R.drawable.nasif_house) , getString(R.string.nasif_house_description)));
        landmarksLocations.add(new Location(getString(R.string.albald_name),  ContextCompat.getDrawable(getActivity(), R.drawable.alblad) , getString(R.string.albald_description)));
        landmarksLocations.add(new Location(getString(R.string.cornish),  ContextCompat.getDrawable(getActivity(), R.drawable.cornich) , getString(R.string.cornich_desc)));
        landmarksLocations.add(new Location(getString(R.string.fountin),  ContextCompat.getDrawable(getActivity(),R.drawable.fountin) , getString(R.string.fountin_desc)));

        //Set Adapter
        LandmarksListAdapter adapter = new LandmarksListAdapter(getActivity() , landmarksLocations);
        landmarksListView = view.findViewById(R.id.landmark_listview);
        landmarksListView.setAdapter(adapter);


        // Inflate the layout for this fragment
        return view;

    }


public class LandmarksListAdapter extends ArrayAdapter<Location> {
    public LandmarksListAdapter(Context context, ArrayList<Location> landmarkList) {
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
