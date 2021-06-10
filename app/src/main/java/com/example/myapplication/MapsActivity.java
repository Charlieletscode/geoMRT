package com.example.myapplication;

import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    Button launch, launch1, launch2;
    String uri;
    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        launch = (Button)findViewById(R.id.btn);
        launch.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                uri = String.format("google.navigation:q=逢甲大學&mode=d");
                launchMap();
            }
        });

        launch1 = (Button)findViewById(R.id.btn1);
        launch1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                uri = String.format("google.navigation:q=逢甲大學&mode=w");
                launchMap();
            }
        });

        launch2 = (Button)findViewById(R.id.btn2);
        launch2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                uri = String.format("google.navigation:q=逢甲大學&mode=b");
                launchMap();
            }
        });
    }

        public void launchMap(){
            Uri intentUri = Uri.parse(uri);
            Intent intent = new Intent(Intent.ACTION_VIEW,intentUri);
            intent.setPackage("com.google.android.apps.maps");


                startActivity(intent);

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
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }
}