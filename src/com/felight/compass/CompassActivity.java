package com.felight.compass;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

public class CompassActivity extends Activity implements SensorEventListener {

	private ImageView image;
	private float currentDegree = 0f;
	private SensorManager sensorManager;
	TextView tvHeading;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_compass);
		image = (ImageView) findViewById(R.id.imageViewCompass);
		tvHeading = (TextView) findViewById(R.id.tvHeading);
		sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
	}

	@SuppressWarnings("deprecation")
	@Override
	protected void onResume() {
		super.onResume();
		sensorManager.registerListener(this,
				sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION),
				SensorManager.SENSOR_DELAY_GAME);
	}

	@Override
	protected void onPause() {
		super.onPause();
		sensorManager.unregisterListener(this);
	}

	@Override
	public void onSensorChanged(SensorEvent event) {

		float degree = Math.round(event.values[0]);
		tvHeading.setText(" " + Float.toString(degree) + "Degress");

		RotateAnimation ra = new RotateAnimation(currentDegree, -degree,
				Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
				0.5f);
		ra.setDuration(210);
		ra.setFillAfter(true);
		image.startAnimation(ra);
		currentDegree = -degree;

	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {

	}

}
