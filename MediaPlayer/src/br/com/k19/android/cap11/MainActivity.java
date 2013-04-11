package br.com.k19.android.cap11;

import br.com.k19.android.cap11.R;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;

public class MainActivity extends Activity {
	
	private Intent intent;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		intent = new Intent(this, MediaPlayerService.class);
		startService(intent);
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		stopService(intent);
	};

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}