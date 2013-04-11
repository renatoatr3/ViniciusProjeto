package br.com.k19.android.cap05;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {
	final static String APP_PREFS = "app_prefs";
	final static String USERNAME_KEY = "username";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
	}

	@Override
	protected void onResume() {
		super.onResume();
		
		SharedPreferences prefs = getSharedPreferences(APP_PREFS, MODE_PRIVATE);
		String username = prefs.getString(USERNAME_KEY, null);
		
		TextView message = (TextView) findViewById(R.id.welcome_message);
		Button addNameButton = (Button) findViewById(R.id.add_name_button);
		
		if (username != null) {
			message.setText("Bem vindo, " + username + "!");
			addNameButton.setText("Trocar de Nome");
		}else{
			message.setText("Você não cadastrou seu nome...");
			addNameButton.setText("Adicionar nome");
		}
		
		addNameButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this,
						AddNameActivity.class);
				startActivity(intent);
			}
		});
	}
}