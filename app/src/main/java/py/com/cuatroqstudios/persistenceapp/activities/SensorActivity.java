package py.com.cuatroqstudios.persistenceapp.activities;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import py.com.cuatroqstudios.persistenceapp.R;
import py.com.cuatroqstudios.persistenceapp.utils.GPSTracker;

/**
 * Created by manuelitox on 8/16/2016.
 */
public class SensorActivity extends AppCompatActivity {
    private SensorManager mSensorManager;

    private Sensor mAccelerometer;
    private TextView tvDataAccelerometer;
    private TextView tvTestAccelerometer;

    private Sensor mLight;
    private TextView tvDataLight;
    private TextView tvTestLight;

    private Sensor mGyroscope;
    private TextView tvDataGyroscope;
    private TextView tvTestGyroscope;

    private Sensor mMagneticField;
    private TextView tvDataMagneticField;
    private TextView tvTestMagneticField;

    private Location location;
    private TextView tvDataGPS;
    private Button btnGPS;

    private Button btnPhoto;

    private Button btnMic;


    @Override
    public final void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensors);

        // Get an instance of the sensor service, and use that to get an instance of a particular sensor.
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);


        //Compass
        tvDataMagneticField = (TextView) findViewById(R.id.tvDataMagneticField);
        tvTestMagneticField = (TextView) findViewById(R.id.tvTestMagneticField);
        mMagneticField = mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        if (mMagneticField != null) {
            //Success! There's a MAGNETIC_FIELD sensor.
            tvTestMagneticField.setText("Success! There's a magneticField sensor, and it is ready to use");
        } else {
            //Failure! No MAGNETIC_FIELD sensor.
            tvTestMagneticField.setText("Failure! No magneticField sensor.");
        }
        //Gyroscope
        tvDataGyroscope = (TextView) findViewById(R.id.tvDataGyroscope);
        tvTestGyroscope = (TextView) findViewById(R.id.tvTestGyroscope);
        mGyroscope = mSensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        if (mGyroscope != null) {
            //Success! There's a GYROSCOPE sensor.
            tvTestGyroscope.setText("Success! There's a gyroscope sensor, and it is ready to use");
        } else {
            //Failure! No GYROSCOPE sensor.
            tvTestGyroscope.setText("Failure! No gyroscope sensor.");
        }
        //Accelerometer
        tvDataAccelerometer = (TextView) findViewById(R.id.tvDataAccelerometer);
        tvTestAccelerometer = (TextView) findViewById(R.id.tvTestAccelerometer);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        if (mAccelerometer != null) {
            //Success! There's a ACCELEROMETER sensor.
            tvTestAccelerometer.setText("Success! There's a accelerometer sensor, and it is ready to use");
        } else {
            //Failure! No ACCELEROMETER sensor.
            tvTestAccelerometer.setText("Failure! No accelerometer sensor.");
        }
        //Ambient Light
        tvDataLight = (TextView) findViewById(R.id.tvDataLight);
        tvTestLight = (TextView) findViewById(R.id.tvTestLight);
        mLight = mSensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        if (mLight != null) {
            //Success! There's a LIGHT sensor.
            tvTestLight.setText("Success! There's a light sensor, and it is ready to use");
        } else {
            //Failure! No LIGHT sensor.
            tvTestLight.setText("Failure! No light sensor.");
        }


        //GPS
        btnGPS = (Button) findViewById(R.id.btnGPS);
        tvDataGPS = (TextView) findViewById(R.id.tvDataGPS);
        btnGPS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String locationText = "Latitude:" + String.valueOf(location.getLatitude()) + " Longitude:" + String.valueOf(location.getLongitude());
                tvDataGPS.setText(locationText);
            }
        });

        getLocation();

        //Camara
        btnPhoto = (Button) findViewById(R.id.btnPhoto);
        btnPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SensorActivity.this, MakePhotoActivity.class));
            }
        });

        //Mic
        btnMic = (Button) findViewById(R.id.btnMic);
        btnMic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SensorActivity.this, AudioRecordActivity.class));
            }
        });

    }


    @Override
    protected void onResume() {
        // Register a listener for the sensor.
        super.onResume();
        mSensorManager.registerListener(mAccelerometerListener, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        mSensorManager.registerListener(mLightSensorListener, mLight, SensorManager.SENSOR_DELAY_NORMAL);
        mSensorManager.registerListener(mGyroscopeListener, mGyroscope, SensorManager.SENSOR_DELAY_NORMAL);
        mSensorManager.registerListener(mMagneticFieldSensorListener, mMagneticField, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        // Be sure to unregister the sensor when the activity pauses.
        super.onPause();
        mSensorManager.unregisterListener(mAccelerometerListener);
        mSensorManager.unregisterListener(mLightSensorListener);
        mSensorManager.unregisterListener(mGyroscopeListener);
        mSensorManager.unregisterListener(mMagneticFieldSensorListener);
    }


    private SensorEventListener mGyroscopeListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            // Do something with this sensor data.
            String text = "values[0] :" + event.values[0] + "\nvalues[1] :" + event.values[1] + "\nvalues[2] :" + event.values[2];
            tvDataGyroscope.setText(text);

        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
            // Do something here if sensor accuracy changes.
            String text = tvTestGyroscope.getText().toString();
            text += "\n" + "Accuracy: " + sensor.toString() + " - " + accuracy;
            tvTestGyroscope.setText(text);
        }
    };

    private SensorEventListener mAccelerometerListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            // Do something with this sensor data.
            String text = "values[0] :" + event.values[0] + "\nvalues[1] :" + event.values[1] + "\nvalues[2] :" + event.values[2];
            tvDataAccelerometer.setText(text);

        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
            // Do something here if sensor accuracy changes.
            String text = tvTestAccelerometer.getText().toString();
            text += "\n" + "Accuracy: " + sensor.toString() + " - " + accuracy;
            tvTestAccelerometer.setText(text);
        }
    };

    private SensorEventListener mMagneticFieldSensorListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            // Do something with this sensor data.
            String text = "values[0] :" + event.values[0] + "\nvalues[1] :" + event.values[1] + "\nvalues[2] :" + event.values[2];
            tvDataMagneticField.setText(text);
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
            // Do something here if sensor accuracy changes.
            String text = tvTestMagneticField.getText().toString();
            text += "\n" + "Accuracy: " + sensor.toString() + " - " + accuracy;
            tvTestMagneticField.setText(text);
        }
    };

    private SensorEventListener mLightSensorListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            // Do something with this sensor data.
            String text = "values[0] :" + event.values[0] + "\nvalues[1] :" + event.values[1] + "\nvalues[2] :" + event.values[2];
            tvDataLight.setText(text);
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
            // Do something here if sensor accuracy changes.
            String text = tvTestLight.getText().toString();
            text += "\n" + "Accuracy: " + sensor.toString() + " - " + accuracy;
            tvTestLight.setText(text);
        }
    };


    public void getLocation() {
        GPSTracker gps = new GPSTracker(SensorActivity.this);
        if (gps.canGetLocation()) {
            location = gps.getLocation();

        } else {
            // can't get location
            // GPS or Network is not enabled
            Toast.makeText(SensorActivity.this, "Turn on your GPS", Toast.LENGTH_SHORT).show();
        }
    }
}