package com.codetest;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.List;
import java.util.Locale;

/**
 * represents the google maps to help the street choice on the route search
 */
public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    public static final String STREET_NAME_PARAMETER = "STREET_NAME_PARAMETER";

    private static final String LANGUAGE = "pt-BR";
    private static final double FLORIANOPOLIS_LATITUDE = -27.594278d;
    private static final double FLORIANOPOLIS_LONGITUDE = -48.548378d;
    private static final float INITIAL_ZOOM = 14.0f;


    private GoogleMap mMap;
    private String streetName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Florianopolis, Brazil.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng fpolisPosition = new LatLng(FLORIANOPOLIS_LATITUDE,FLORIANOPOLIS_LONGITUDE);
        mMap.addMarker(new MarkerOptions().position(fpolisPosition).title(getString(R.string.google_maps_position_marker)));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(fpolisPosition));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(fpolisPosition, INITIAL_ZOOM));
        configureOnMapClickListener();
    }

    /**
     * sets the listener for onclick events over the map and sugests the closest street for confirmation, if confirmed it passes the selected street name to the parent activity
     */
    private void configureOnMapClickListener() {
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {

            @Override
            public void onMapClick(LatLng point) {
                String selectedStreet = getClosestStreet(point);
                MapsActivity.this.setStreetName(selectedStreet);
                if (selectedStreet != null) {
                    new AlertDialog.Builder(MapsActivity.this)
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .setTitle(R.string.confirm_title)
                            .setMessage(MessageFormat.format(getString(R.string.confirm_message), selectedStreet))
                            .setPositiveButton(R.string.confirm_label_yes, new DialogInterface.OnClickListener() {

                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent result = new Intent();
                                    result.putExtra(STREET_NAME_PARAMETER, MapsActivity.this.getStreetName());
                                    setResult(RESULT_OK, result);
                                    MapsActivity.this.finish();
                                }

                            })
                            .setNegativeButton(R.string.confirm_label_no, null)
                            .show();
                }
            }
        });
    }

    /**
     * tries to find the closest street for the given position
     * @param position
     * @return closest street name from position
     */
    private String getClosestStreet(LatLng position) {
        try {
            Geocoder geocoder = new Geocoder(getApplicationContext(),Locale.forLanguageTag(LANGUAGE) );
            List<Address> addresses = geocoder.getFromLocation(position.latitude, position.longitude, 1);
            if (addresses.isEmpty()) {
                return null;
            }

            return addresses.get(0).getThoroughfare();
        } catch (IOException e) {
            return null;
        }
    }

    public String getStreetName(){
        return this.streetName;
    }

    private void setStreetName(String streetName){
        this.streetName = streetName;
    }
}
