package com.peeparound.wizventure.peeparoundsecond;

import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;

/**
 * Created by Amaresh on 24-07-2016.
 */
public class ListActivity extends AppCompatActivity implements
        GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, AdapterView.OnItemClickListener {

    public LocationManager mLocationManager;
    private static final String GOOGLE_API_KEY = "AIzaSyB4XeZsxstbxHomPVCBcs-ZYxa_eAH3-f0";
    private int PROXIMITY_RADIUS = 1500;

    double latitude = 0;
    double longitude = 0;
    String googleUrl = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?";
    static final String KEY_TITLE = "title";
    ListView list;
    LazyAdapter adapter;
    String str;
    private Location mLastLocation;
    private final LocationListener mLocationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            //code
            System.out.println("onLocationChanged");

            mLastLocation = location;
            latitude = mLastLocation.getLatitude();
            longitude = mLastLocation.getLongitude();
            StringBuilder googlePlacesUrl = new StringBuilder(googleUrl);
            googlePlacesUrl.append("location=" + latitude + "," + longitude);
            googlePlacesUrl.append("&radius=" + PROXIMITY_RADIUS);
            googlePlacesUrl.append("&types=" + str);
            googlePlacesUrl.append("&sensor=true");
            googlePlacesUrl.append("&key=" + GOOGLE_API_KEY);
            Log.d("ATM URL:", "" + googlePlacesUrl);
            GooglePlacesReadTask googlePlacesReadTask = new GooglePlacesReadTask(ListActivity.this);
            Object[] toPass = new Object[2];
            toPass[0] = list;
            toPass[1] = googlePlacesUrl.toString();
            googlePlacesReadTask.execute(toPass);

        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
            System.out.println("onStatusChanged");
        }

        @Override
        public void onProviderEnabled(String provider) {
            System.out.println("onProviderEnabled");
        }

        @Override
        public void onProviderDisabled(String provider) {
            System.out.println("onProviderDisabled");
            //turns off gps services
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listofplaces);

        list = (ListView) findViewById(R.id.list);
        list.setOnItemClickListener(this);
        PlacesDisplayTask pc = new PlacesDisplayTask(this);
        Bundle bndl = getIntent().getExtras();
        str = bndl.getString("places");
        int LOCATION_REFRESH_TIME = 10000;
        int LOCATION_REFRESH_DISTANCE = 100;

        mLocationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, LOCATION_REFRESH_TIME,
                LOCATION_REFRESH_DISTANCE, mLocationListener);


    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


        Intent intent = new Intent(this, Details.class);
        startActivity(intent);

    }

    @Override
    public void onConnected(Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }
}