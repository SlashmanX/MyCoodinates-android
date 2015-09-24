package com.tallorder.apps.mycoordinates;

import android.app.Activity;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Map;

import io.nlopez.smartlocation.OnLocationUpdatedListener;
import io.nlopez.smartlocation.SmartLocation;
import io.nlopez.smartlocation.location.LocationProvider;
import io.nlopez.smartlocation.location.config.LocationParams;
import io.nlopez.smartlocation.location.providers.LocationManagerProvider;

public class CoordsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coords);
        SmartLocation.with(this).location()
                .provider(new LocationManagerProvider())
                .config(LocationParams.NAVIGATION)
                .start(locationUpdateListener);
    }

    public OnLocationUpdatedListener locationUpdateListener = new OnLocationUpdatedListener() {

        @Override
        public void onLocationUpdated(Location location) {
            CoordsActivityFragment coordsFrag = (CoordsActivityFragment)
                    getFragmentManager().findFragmentById(R.id.coords_fragment);

            GoogleMap map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map_fragment))
                    .getMap();

            if(map != null) {
                LatLng coords = new LatLng(location.getLatitude(), location.getLongitude());

                Circle user_location_circle = map.addCircle(new CircleOptions()
                        .center(coords)
                        .radius(location.getAccuracy())
                        .fillColor(Color.argb(20, 0, 0, 225))
                        .strokeColor(Color.argb(200, 0, 0, 255))
                        .strokeWidth(3.0f)
                );
                map.moveCamera(CameraUpdateFactory.newLatLngZoom(coords, 15));
            }

            if(coordsFrag != null) {
                coordsFrag.setLocation(location);
            }
        }
    };


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_coords, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPause() {
        super.onPause();
        SmartLocation.with(this).location().stop();
    }

    @Override
    protected void onStop() {
        super.onStop();
        SmartLocation.with(this).location().stop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SmartLocation.with(this).location().stop();
    }

    @Override
    protected void onResume() {
        super.onResume();
        SmartLocation.with(this).location()
                .start(locationUpdateListener);
    }
}
