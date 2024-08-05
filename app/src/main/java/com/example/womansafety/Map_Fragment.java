package com.example.womansafety;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

public class Map_Fragment extends Fragment   {


   // private GoogleMap mMap;
   // private ActivityMapsBinding binding;
    SearchView searchView;
    SupportMapFragment mapFragment;
    Context context;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview = inflater.inflate(R.layout.fragment_map_, container, false);

         mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(@NonNull GoogleMap mMap) {
                mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

                mMap.clear(); //clear old markers
                CameraPosition googlePlex = CameraPosition.builder()
                        .target(new LatLng(37.4219999,-122.0862462))
                                .zoom(10)
                                .bearing(0)
                                .tilt(45)
                                .build();

                mMap.animateCamera(CameraUpdateFactory.newCameraPosition(googlePlex), 10000, null);

                LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));


                //initialize search view (2)
        searchView = rootview.findViewById(R.id.searchview);



        //adding a query listener for the search view
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                //get location name on search view
                String location = searchView.getQuery().toString();
                List<Address> addressList = null; //store list of all address

                //checking if entered location is null or not.
                if(location !=null || location.equals(" ")){
                    //creating and initializing a geocoder
                    Geocoder geocoder = new Geocoder(getActivity());

                    //getting location from location name and adding that location to address list
                    try {
                        addressList = geocoder.getFromLocationName(location, 1);

                    }catch (IOException e){
                        e.printStackTrace();
                    }

                    //getting location from our list a first position.
                    Address address = addressList.get(0);

                    //creating a variable for our location where we will add our location latitude and longitude
                    LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());
                    mMap.addMarker(new MarkerOptions().position(latLng).title(location));
                    mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10)); //animate camera to above marker
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        //at last we calling our map fragment to update
        //mapFragment.getMapAsync(this);
         }
        });
        return rootview;
//        return inflater.inflate(R.layout.fragment_map_, container, false);
    }

}