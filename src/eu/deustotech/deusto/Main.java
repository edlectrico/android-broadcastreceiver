package eu.deustotech.deusto;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import eu.deustotech.deusto.service.MyService;

public class Main extends Activity implements android.view.View.OnClickListener {

	private static final String TAG = Main.class.getSimpleName();
	public SharedPreferences settings;
	private EditText editText;

	public static class mReceiver extends BroadcastReceiver {
		@Override
		public void onReceive(Context context, Intent intent) {
			Toast.makeText(context, "Hello toast!", Toast.LENGTH_LONG).show();
			Log.e(TAG,
					context.getResources().getString(
							R.string.broadcast_received));

			// Starting the service on boot
			Intent mServiceIntent = new Intent(context, MyService.class);
			mServiceIntent.setAction(context.getResources().getString(
					R.string.start_service));
			context.startService(mServiceIntent);
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		settings = getSharedPreferences(
				getResources().getString(R.string.preferences_name), 0);

		findViewById(R.id.bt).setOnClickListener(this);
		editText = (EditText) findViewById(R.id.et);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void storePreferences(View view) {
		SharedPreferences.Editor editor = settings.edit();

		editor.putFloat(getResources().getString(R.string.stored_parameter),
				Float.parseFloat(editText.getText().toString()));
		editor.commit();

		Log.e(TAG, getResources().getString(R.string.preference_stored));

		// TODO: send an Intent from here to notify the service
		// about the new configuration
		Intent configurationIntent = new Intent(getApplicationContext(),
				MyService.class);
		configurationIntent.setAction(getResources().getString(
				R.string.check_stored_parameter));
		startService(configurationIntent);
	}

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.bt:
			storePreferences(view);
			break;
		}
	}

}
