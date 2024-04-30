package com.example.bccrssv2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsFragment extends Fragment {

    private OnMapReadyCallback callback = new OnMapReadyCallback() {


        @Override
        public void onMapReady(GoogleMap googleMap) {
           // LatLng sydney = new LatLng(-34, 151);

           LatLng glasgow = new LatLng(55.8642, -4.2518);
          LatLng london = new LatLng(51.5074, -0.1278);
          LatLng newYork = new LatLng(40.7128, -74.0060);
      LatLng oman= new LatLng(21.4735, 55.9754);
          LatLng mauritius = new LatLng(-20.3484, 57.5522);
         LatLng bangladesh= new LatLng(23.6850, 90.3563);

            Marker glasgowMarker = googleMap.addMarker(new MarkerOptions().position(glasgow).title("Marker in glasgow"));
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(glasgow));

            Marker londonMarker = googleMap.addMarker(new MarkerOptions().position(london).title("Marker in London"));
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(london));

            Marker newYorkMarker = googleMap.addMarker(new MarkerOptions().position(newYork).title("Marker in New York"));
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(newYork));

            Marker omanMarker = googleMap.addMarker(new MarkerOptions().position(oman).title("Marker in Oman"));
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(oman));

            Marker mauritiusMarker = googleMap.addMarker(new MarkerOptions().position(mauritius).title("Marker in mauritius"));
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(mauritius));

            Marker bangladeshMarker = googleMap.addMarker(new MarkerOptions().position(bangladesh).title("Marker in bangladesh"));
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(bangladesh));

            // Add a click event listener to the marker
            googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                @Override
                public boolean onMarkerClick(Marker clickedMarker) {
                    Intent intent = new Intent(getActivity(), Dashboards2.class);
                    if (clickedMarker.equals(glasgowMarker)) {
                        Log.d("MapsActivity", "Marker clicked ma ");
                        intent.putExtra("genericMarkerKey", "2648579");
                        startActivity(intent);
                        return true; // Return true to consume the event and prevent default behavior
                    }
                    if (clickedMarker.equals(londonMarker)) {
                        Log.d("MapsActivity", "London Marker clicked ma ");
                        intent.putExtra("genericMarkerKey", "2643743");
                        startActivity(intent);
                        return true; // Return true to consume the event and prevent default behavior
                    }

                    if (clickedMarker.equals(newYorkMarker)) {
                        Log.d("MapsActivity", "New York Marker clicked ma ");
                        intent.putExtra("genericMarkerKey", "5128581");

                        startActivity(intent);

                        return true; // Return true to consume the event and prevent default behavior
                    }

                    if (clickedMarker.equals(omanMarker)) {
                        Log.d("MapsActivity", "omanMarkerMarker clicked ma ");
                        intent.putExtra("genericMarkerKey", "287286");
                        startActivity(intent);
                        return true; // Return true to consume the event and prevent default behavior
                    }

                    if (clickedMarker.equals(mauritiusMarker)) {
                        Log.d("MapsActivity", "mauritiusMarker clicked ma ");
                        intent.putExtra("genericMarkerKey", "934154");

                        startActivity(intent);
                        return true; // Return true to consume the event and prevent default behavior
                    }

                    if (clickedMarker.equals(bangladeshMarker)) {
                        Log.d("MapsActivity", "bangladeshMarker clicked ma ");
                        intent.putExtra("genericMarkerKey", "1185241");
                        startActivity(intent);
                        return true; // Return true to consume the event and prevent default behavior
                    }
                    return false; // Return false to allow default marker click behavior
                }
            });
        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_maps, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(callback);
        }
    }
}