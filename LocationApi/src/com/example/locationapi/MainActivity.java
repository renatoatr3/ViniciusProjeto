package com.example.locationapi;

import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.view.Menu;
import android.widget.TextView;
import android.widget.Toast;
import android.util.Log;

public class MainActivity extends Activity implements LocationListener {
	public static final String TAG = "MainActivity";
			
	private TextView latitudeText;
	private TextView longitudeText;
	private LocationManager locationManager;
	private String provider;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		latitudeText = (TextView) findViewById(R.id.latitude_text);
		longitudeText = (TextView) findViewById(R.id.longitude_text);
		
		locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		
		Criteria criteria = new Criteria();
		provider = locationManager.getBestProvider(criteria, false);
		
		Location location = locationManager.getLastKnownLocation(provider);
		
		if(location != null) {
			Log.d(TAG, "provider " + provider + " foi selecionado.");
			onLocationChanged(location);
		} else {
			latitudeText.setText(R.string.location_not_available);
			longitudeText.setText(R.string.location_not_available);
		}
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		locationManager.requestLocationUpdates(provider, 400, 1, this);
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		locationManager.removeUpdates(this);
	}
	
	@Override
	public void onLocationChanged(Location location) {
		double lat = location.getLatitude();
		double lng = location.getLongitude();
		latitudeText.setText(getString(R.string.point_label, lat));
		longitudeText.setText(getString(R.string.point_label, lng));
	}
	
	@Override
	public void onStatusChanged(String provider, int status, Bundle extas) {
		
	}
	
	@Override
	public void onProviderEnabled(String provider) {
		Toast.makeText(this, "Novo provider " + provider,
				Toast.LENGTH_SHORT).show();
	}
	
	@Override
	public void onProviderDisabled(String provider) {
		Toast.makeText(this, "Provider Desabilitado " + provider,
				Toast.LENGTH_SHORT).show();
	}
	}