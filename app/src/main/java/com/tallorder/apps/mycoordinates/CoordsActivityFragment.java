package com.tallorder.apps.mycoordinates;

import android.app.Fragment;
import android.location.Location;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import io.nlopez.smartlocation.OnLocationUpdatedListener;
import io.nlopez.smartlocation.SmartLocation;

/**
 * A placeholder fragment containing a simple view.
 */
public class CoordsActivityFragment extends Fragment {

    TextView longitudeText;
    TextView latitudeText;
    TextView accuracyText;
    OnLocationUpdatedListener mCallback;

    public CoordsActivityFragment() {
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        longitudeText = (TextView) getView().findViewById(R.id.longitude_value);
        latitudeText = (TextView) getView().findViewById(R.id.latitude_value);
        accuracyText = (TextView) getView().findViewById(R.id.accuracy_value);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_coords, container, false);
    }

    public void setLocation(Location location) {
        longitudeText.setText(location.getLongitude() + "");
        latitudeText.setText(location.getLatitude() + "");
        accuracyText.setText(location.getAccuracy() + "m");
    }
}
