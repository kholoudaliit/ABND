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


public class ShoppingFragment extends Fragment {

    private ListView shoppingListView;
    public ShoppingFragment() {
        // Required empty public constructor
    }

    public static ShoppingFragment newInstance(String param1, String param2) {
        ShoppingFragment fragment = new ShoppingFragment();
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
        View view= inflater.inflate(R.layout.fragment_shopping, container, false);

        //Data
        ArrayList<Location> shoppingLocations = new ArrayList<Location>();
        shoppingLocations.add(new Location(getString(R.string.redseamall),  ContextCompat.getDrawable(getActivity(), R.drawable.redsea) , getString(R.string.redsea_description)));
        shoppingLocations.add(new Location(getString(R.string.arabmall),  ContextCompat.getDrawable(getActivity(), R.drawable.mallofarabiajeddah) , getString(R.string.arabmall_desc)));
        shoppingLocations.add(new Location(getString(R.string.gabel_souq),  ContextCompat.getDrawable(getActivity(), R.drawable.gabel_souq) , getString(R.string.gabel_souq)));

        //Set Adapter
        ShoppingAdapter adapter = new ShoppingAdapter(getActivity() , shoppingLocations);
        shoppingListView = view.findViewById(R.id.shopping_listview);
        shoppingListView.setAdapter(adapter);


        // Inflate the layout for this fragment
        return view;

    }


    public class ShoppingAdapter extends ArrayAdapter<Location> {
        public ShoppingAdapter(Context context, ArrayList<Location> landmarkList) {
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
