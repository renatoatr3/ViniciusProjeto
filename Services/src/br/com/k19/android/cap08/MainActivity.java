package br.com.k19.android.cap08;

import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Messenger;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

	private Handler handler = new Handler() {
		public void handleMessage(Message message) {
			Object path = message.obj;
			if (message.arg1 == RESULT_OK && path != null) {
				Toast.makeText(MainActivity.this,
						getString(R.string.download_success, path.toString()),
						Toast.LENGTH_LONG).show();
			} else {
				Toast.makeText(MainActivity.this,
						getString(R.string.download_error), Toast.LENGTH_LONG)
						.show();
			}
		};
	};

	@Override
	public void	onCreate(Bundle savedInstaceState) {
		super.onCreate(savedInstaceState);
		setContentView(R.layout.main);
		
		Button startButton = (Button) findViewById(R.id.start_button);
		startButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this,
						DownloadService.class);
				Messenger messenger = new Messenger(handler);
				intent.putExtra("messenger", messenger);
				intent.setData(Uri.parse("cursos.html"));
				intent.putExtra("urlPath", "http://static.giantbomb.com/uploads/original/1/17172/1130612-cyndaquil3.jpg");
				startService(intent);
			}
		});
	}
}