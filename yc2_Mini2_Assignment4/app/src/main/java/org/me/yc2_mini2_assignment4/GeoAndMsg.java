package org.me.yc2_mini2_assignment4;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;


import android.os.Bundle;

import android.support.v4.app.ActivityCompat;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;

import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;


import org.me.yc2_mini2_assignment4.Exception.InvalidInputException;
import org.me.yc2_mini2_assignment4.Exception.fixException;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import static com.google.android.gms.location.LocationServices.*;

public class GeoAndMsg extends Activity implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener, LocationListener {

    private static final String TAG = GeoAndMsg.class.getSimpleName();
    private Location mLastLocation;
    private boolean mRequestingLocationUpdates = false;
    private GoogleApiClient mGoogleApiClient;
    private LocationRequest mLocationRequest;

    private static final int REQUEST_CODE = 1000;

    // Location updates intervals in sec
    private static int UPDATE_INTERVAL = 10000; // 10 sec
    private static int FATEST_INTERVAL = 5000; // 5 sec
    private static int DISPLACEMENT = 1; // 1 meters
    //UI elements
    private TextView lblLocation;
    private Button btnSendMsg;
    FileOutputStream fos=null ;


    /***
     *
     *
     > First check for availability of Google Play Services by calling checkPlayServices() in onResume()

     > Once play services are available on the device, build the GoogleApiClient by calling buildGoogleApiClient() method.

     > Connect to google api client by calling mGoogleApiClient.connect() in onStart() method. By calling this, onConnectionFailed(), onConnected() and onConnectionSuspended() will be triggered depending upon the connection status.

     > Once google api is successfully connected, displayLocation() should be called in onConnected() method to get the current location.


     */
    @Override
    protected void onStart() {
        super.onStart();

        if (mGoogleApiClient != null) {
            Toast.makeText(this, "Starting connect!", Toast.LENGTH_LONG).show();
            mGoogleApiClient.connect();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            fos = getBaseContext().openFileOutput("log.txt", Context.MODE_APPEND);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        setContentView(R.layout.activity_geo_and_msg);
        lblLocation = (TextView) findViewById(R.id.lblLocation);
        btnSendMsg = (Button) findViewById(R.id.btnSendMsg);

        //1. check the availability of the google service
        if (checkPlayServices()) {
            buildGoogleApiClient();
            createLocationRequest();
        }

        btnSendMsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //send message
                EditText addrTxt = (EditText) findViewById(R.id.addrEditText);

                EditText msgTxt = (EditText) findViewById(R.id.msgEditText);

                String addr=addrTxt.getText().toString();
                String msg=msgTxt.getText().toString();
                    if(msg.equals("Enter message")){
                        try{
                            throw new InvalidInputException("Please input a valid message!");
                        }catch (InvalidInputException e){
                            try {
                                fos.write(e.getmessage().getBytes());
                                fixException fix=new fixException();
                                msg=fix.fix();
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }

                        }
                    }
                try {

                    sendSmsMessage(addr, msg);

                    Toast.makeText(getApplicationContext(), "Successfully sent SMS", Toast.LENGTH_LONG).show();

                } catch (Exception e) {

                    Toast.makeText(getApplicationContext(), "Failed to send SMS", Toast.LENGTH_LONG).show();

                    e.printStackTrace();

                }
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        checkPlayServices();
        // Resuming the periodic location updates
        if (mGoogleApiClient.isConnected() && mRequestingLocationUpdates) {
            startLocationUpdates();
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        stopLocationUpdates();
    }


    @Override
    protected void onStop() {
        super.onStop();
        if (mGoogleApiClient.isConnected()) {
            mGoogleApiClient.disconnect();
        }
    }

    /**
     * This method is used to send the message
     * @param addr
     * @param msg
     */
    private void sendSmsMessage(String addr, String msg) {

        SmsManager manager = SmsManager.getDefault();
        manager.sendTextMessage(addr, null, msg, null, null);

    }

    /**
     * This method is used to verify the google play services on the device
     * @return
     */
    private boolean checkPlayServices() {
        int resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
        if (resultCode != ConnectionResult.SUCCESS) {
            if (GooglePlayServicesUtil.isUserRecoverableError(resultCode)) {
                //Toast the recoverable error
                GooglePlayServicesUtil.getErrorDialog(resultCode, this, REQUEST_CODE).show();
            } else {
                //Toast the unrecoverable error
                Toast.makeText(getApplicationContext(), "This device is not supported!", Toast.LENGTH_LONG).show();
                finish();
            }
            return false;
        }
        return true;
    }

    /**
     * This method is used to build the google api client
     * since maybe multiple api client, then we should synchronize this method
     */
    private void buildGoogleApiClient() {
        Toast.makeText(this,"GootGoogle",Toast.LENGTH_LONG).show();
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
    }

    /***
     * This method is used to display the location
     * 1. check location provider
     * 2. check the permission of api
     * 3. get last known location
     */
    private void displayLocation() {
        String provider;
        //1. check the location provider : is the google api client
        //2.check the permission of api
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "No permission authorized!", Toast.LENGTH_LONG).show();
            return;
        }
        //3. get lastKnown location
        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);

        if (mLastLocation != null) {
            double latitude = mLastLocation.getLatitude();
            double longitude = mLastLocation.getLongitude();
            Log.i(TAG, latitude + "," + longitude);
            lblLocation.setText(latitude + ", " + longitude);
        } else {

            lblLocation
                    .setText("(Couldn't get the location. Make sure location is enabled on the device)");
        }

    }

    /**
     * Creating location request object
     * */
    protected void createLocationRequest() {
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(UPDATE_INTERVAL);
        mLocationRequest.setFastestInterval(FATEST_INTERVAL);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        mLocationRequest.setSmallestDisplacement(DISPLACEMENT);
    }

    /**
     * Starting the location updates
     * */
    protected void startLocationUpdates() {

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            Toast.makeText(this, "No permission authorized!", Toast.LENGTH_LONG).show();
            return;
        }

        LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
    }

    /**
     * Stopping location updates
     */
    protected void stopLocationUpdates() {
        LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);
    }


    @Override
    public void onLocationChanged(Location location) {
        // Assign the new location
        mLastLocation = location;

        Toast.makeText(getApplicationContext(), "Location changed!",
                Toast.LENGTH_SHORT).show();

        // Displaying the new location on UI
        displayLocation();
    }


    /***
     * Google api callback methods to respond to different connection status
     * @param bundle
     */
    @Override
    public void onConnected(Bundle bundle) {
        Toast.makeText(this,"Connection success!", Toast.LENGTH_LONG).show();
        displayLocation();
    }

    @Override
    public void onConnectionSuspended(int i) {
       mGoogleApiClient.connect();
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Toast.makeText(this,"Connection failed", Toast.LENGTH_LONG).show();
        Log.i(TAG,"Connection failed");
    }




}
