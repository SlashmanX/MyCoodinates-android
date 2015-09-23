package com.tallorder.apps.mycoordinates;

import android.app.Activity;
import android.location.Location;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import io.nlopez.smartlocation.OnLocationUpdatedListener;
import io.nlopez.smartlocation.SmartLocation;
import io.nlopez.smartlocation.location.config.LocationParams;

public class CoordsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coords);
        SmartLocation.with(this).location()
                .config(LocationParams.NAVIGATION)
                .start(locationUpdateListener);
    }

    public OnLocationUpdatedListener locationUpdateListener = new OnLocationUpdatedListener() {

        @Override
        public void onLocationUpdated(Location location) {
            CoordsActivityFragment coordsFrag = (CoordsActivityFragment)
                    getFragmentManager().findFragmentById(R.id.coords_fragment);

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
