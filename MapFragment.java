package com.example.googlemap;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.MarkerOptions;


public class MapFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Initialize view
        View view = inflater.inflate(R.layout.fragment_map, container, false);

        //Initialize map fragment
        SupportMapFragment supportMapFragment = (SupportMapFragment)
                getChildFragmentManager().findFragmentById(R.id.google_map);

        //Async map
        supportMapFragment.getMapAsync(googleMap -> {
            //when map is loaded
            googleMap.setOnMapClickListener(latLng -> {
                //when clicked on map
                //Initialize marker options
                MarkerOptions markerOptions = new MarkerOptions();
                //Set position of marker
                markerOptions.position(latLng);
                //Set title of marker
                markerOptions.title(latLng.latitude + " : " + latLng.longitude);
                //Remove all marker
                googleMap.clear();
                //Animating to zoom the marker
                googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(
                        latLng, 10
                ));
                //Add marker on map
                googleMap.addMarker(markerOptions);
            });
        });

        //Return view
        return view;
    }
}