package com.example.myapplication;

import androidx.fragment.app.FragmentActivity;

import android.app.Activity;
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

public class MapsActivity extends Activity {

    private GoogleMap mMap;
    static final LatLng agra = new LatLng(24.17975090926697, 120.64656943861674);
    Button launch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        launch = (Button)findViewById(R.id.btn);
        launch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                launchMap();
            }
        });
    }


    public void launchMap(){
        String uri = String.format("geo:24.17975090926697, 120.64656943861674?q = 台中+逢甲大學");
        Uri uri1 = Uri.parse(uri);
        Intent intent = new Intent(Intent.ACTION_VIEW,uri1);
        intent.setPackage("com.google.android.apps.maps");

        startActivity(intent);
    }
}