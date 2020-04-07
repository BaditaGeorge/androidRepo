package com.example.firstapplication;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ActivityForSensor extends AppCompatActivity implements LocationListener {

    private SensorManager sensorManager;
    private Sensor mSensor;

    private final static int REQUEST_CODE_ASK_PERMISSIONS = 1;

    /**
     * Permissions that need to be explicitly requested from end user.
     */
    private static final String[] REQUIRED_SDK_PERMISSIONS = new String[] {
            Manifest.permission.ACCESS_FINE_LOCATION };

    @Override
    protected void onCreate(Bundle savedInstances){
        super.onCreate(savedInstances);
        setContentView(R.layout.sensor_activity);
        checkPermissions();
    }

    public void useSensors(View view){
        sensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
        mSensor = null;

        if(sensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY) != null){
            List<Sensor> gravSensors = sensorManager.getSensorList(Sensor.TYPE_GRAVITY);
            for(int i=0;i<gravSensors.size();i++){
//                if((gravSensors.get(i).getVendor().contains("Google LLC")) &&
//                        (gravSensors.get(i).getVersion() == 3)){
//                    mSensor = gravSensors.get(i);
//                    Toast.makeText(getApplicationContext(),mSensor.toString(),Toast.LENGTH_LONG).show();
//                }
                mSensor = gravSensors.get(i);
                Toast.makeText(getApplicationContext(),mSensor.toString(),Toast.LENGTH_LONG).show();
            }
        }

//        if(sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) != null){
//
//        }else{
//
//        }
    }

    protected void checkPermissions() {
        final List<String> missingPermissions = new ArrayList<String>();
        // check all required dynamic permissions
        for (final String permission : REQUIRED_SDK_PERMISSIONS) {
            final int result = ContextCompat.checkSelfPermission(this, permission);
            if (result != PackageManager.PERMISSION_GRANTED) {
                missingPermissions.add(permission);
            }
        }
        if (!missingPermissions.isEmpty()) {
            // request all missing permissions
            final String[] permissions = missingPermissions
                    .toArray(new String[missingPermissions.size()]);
            ActivityCompat.requestPermissions(this, permissions, REQUEST_CODE_ASK_PERMISSIONS);
        } else {
            final int[] grantResults = new int[REQUIRED_SDK_PERMISSIONS.length];
            Arrays.fill(grantResults, PackageManager.PERMISSION_GRANTED);
            onRequestPermissionsResult(REQUEST_CODE_ASK_PERMISSIONS, REQUIRED_SDK_PERMISSIONS,
                    grantResults);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[],
                                           @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_ASK_PERMISSIONS:
                for (int index = permissions.length - 1; index >= 0; --index) {
                    if (grantResults[index] != PackageManager.PERMISSION_GRANTED) {
                        // exit the app if one permission is not granted
                        Toast.makeText(this, "Required permission '" + permissions[index]
                                + "' not granted, exiting", Toast.LENGTH_LONG).show();
                        finish();
                        return;
                    }
                }
                // all permissions were granted
                break;
        }
    }


    @Override
    public void onLocationChanged(Location location) {
        // TODO Auto-generated method stub
        TextView txtv = (TextView) findViewById(R.id.textView2);
        txtv.setText("Latitude: " + String.valueOf(location.getLatitude()) + ", " + "Longitude: " + String.valueOf(location.getLongitude()));
//        Toast.makeText(getApplicationContext(),"Latitude: " + String.valueOf(location.getLatitude()) + ", " + "Longitude: " + String.valueOf(location.getLongitude()),Toast.LENGTH_SHORT).show();
        return;
    }

    @Override
    public void onProviderDisabled(String provider) {
        // TODO Auto-generated method stub
    }

    @Override
    public void onProviderEnabled(String provider) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        // TODO Auto-generated method stub

    }

    public void getLocation(View view){
        LocationManager lm = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        try {
            lm.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0,this);
//            Toast.makeText(getApplicationContext(),"Aici",Toast.LENGTH_LONG).show();
        }catch(SecurityException e){
            Toast.makeText(getApplicationContext(),"Eroare",Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }
}
