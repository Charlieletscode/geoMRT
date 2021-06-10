package com.example.myapplication;

import androidx.fragment.app.FragmentActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.PolylineOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    LatLng sydney = new LatLng(-34, 151);
    LatLng sydney1 = new LatLng(-34, 151);
    LatLng sydney2 = new LatLng(-34, 151);
    int count = 0;
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        BitmapDescriptor d = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE);
        mMap.addMarker(new MarkerOptions().position(sydney).icon(d).draggable(true));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));


        googleMap.setOnMarkerDragListener(new GoogleMap.OnMarkerDragListener() {
            @Override
            public void onMarkerDragStart(Marker marker) {

                BitmapDescriptor d = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE);
                mMap.addMarker(new MarkerOptions().position(marker.getPosition()).icon(d).draggable(true));

                if(count == 0){
                    sydney = marker.getPosition();
                }else if(count == 1){
                    sydney1 = marker.getPosition();
                }
                Toast.makeText(getApplicationContext(),"Drag started", Toast.LENGTH_LONG).show();
                count++;
            }

            @Override
            public void onMarkerDrag(Marker marker) {

            }

            public void onMarkerDragEnd(Marker marker) {
                LatLng latlng = marker.getPosition();
                double lati = latlng.latitude;
                double longi = latlng.longitude;

                double lat1 = (Math.PI/180)*sydney.latitude;
                double lat2 = (Math.PI/180)*latlng.latitude;

                double lon1 = (Math.PI/180)*sydney.longitude;
                double lon2 = (Math.PI/180)*latlng.longitude;

//      double Lat1r = (Math.PI/180)*(gp1.getLatitudeE6()/1E6);
//      double Lat2r = (Math.PI/180)*(gp2.getLatitudeE6()/1E6);
//      double Lon1r = (Math.PI/180)*(gp1.getLongitudeE6()/1E6);
//      double Lon2r = (Math.PI/180)*(gp2.getLongitudeE6()/1E6);

                //地球半径
                double R = 6371;

                //两点间距离 km，如果想要米的话，结果*1000就可以了
                double d =  Math.acos(Math.sin(lat1)*Math.sin(lat2)+Math.cos(lat1)*Math.cos(lat2)*Math.cos(lon2-lon1))*R;
                Toast.makeText(getApplicationContext(), "Latitude: "+lati+"\nLongitude: "+longi +"\nDrag Dist: "+d+" km", Toast.LENGTH_LONG).show();
                if(count == 2){

                    sydney2 = marker.getPosition();
                    PolygonOptions pol = new PolygonOptions().add(sydney).add(sydney1).add(sydney2);
                    mMap.addPolygon(pol);
                }
            }

        });

        googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                Toast.makeText(getApplicationContext(),"you clicked the marker", Toast.LENGTH_LONG).show();
                return false;
            }
        });
    }

}
