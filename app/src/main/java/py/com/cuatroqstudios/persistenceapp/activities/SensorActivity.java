package py.com.cuatroqstudios.persistenceapp.activities;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import py.com.cuatroqstudios.persistenceapp.R;

/**
 * Created by manuelitox on 8/16/2016.
 */
public class SensorActivity extends AppCompatActivity {
    private SensorManager mSensorManager;
    private Sensor mProximity;
    private TextView tvData;

    @Override
    public final void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensors);

        tvData = (TextView) findViewById(R.id.tvData);
        // Get an instance of the sensor service, and use that to get an instance of
        // a particular sensor.
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mProximity = mSensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);

        if (mProximity != null) {
            //Success! There's a proximity sensor.
            tvData.setText("Success! There's a proximity sensor, and it is ready to use");
        } else {
            //Failure! No proximity sensor.
            tvData.setText("Failure! No proximity sensor.");

        }
    }


    @Override
    protected void onResume() {
        // Register a listener for the sensor.
        super.onResume();
        mSensorManager.registerListener(mLightSensorListener, mProximity, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        // Be sure to unregister the sensor when the activity pauses.
        super.onPause();
        mSensorManager.unregisterListener(mLightSensorListener);
    }

    private SensorEventListener mLightSensorListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            // Do something with this sensor data.
            Log.d("MY_APP", String.valueOf(event.values[0]));
            Log.d("MY_APP", String.valueOf(event.values[1]));
            Log.d("MY_APP", String.valueOf(event.values[2]));
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
            // Do something here if sensor accuracy changes.
            Log.d("MY_APP", sensor.toString() + " - " + accuracy);
        }
    };
}