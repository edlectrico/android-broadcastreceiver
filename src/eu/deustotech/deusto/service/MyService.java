package eu.deustotech.deusto.service;

import android.app.IntentService;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import eu.deustotech.deusto.R;

public class MyService extends IntentService {
	private static final String TAG = MyService.class.getSimpleName();
	private SharedPreferences settings;

	public MyService(String name) {
		super(name);
	}

	public MyService() {
		super(TAG);
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// Restore preferences
		settings = getSharedPreferences(
				getResources().getString(R.string.preferences_name), 0);

		Log.e(TAG, intent.getAction());

		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		// TODO: create the logic to send the corresponding commands
		Log.e(TAG, "Command " + intent.getAction() + " received!");

		if (this.settings != null) {
			if (intent.getAction().equalsIgnoreCase(
					getResources().getString(R.string.start_service))) {
				Log.e(TAG, "Processing " + intent.getAction());
			} else if (intent.getAction().equalsIgnoreCase(
					getResources().getString(R.string.check_stored_parameter))) {
				Log.e(TAG, "Processing " + intent.getAction());

				Log.d(TAG, getResources()
						.getString(R.string.broadcast_creation));

				Intent stickyIntent = new Intent();
				stickyIntent.setAction(getResources().getString(
						R.string.service_broadcast));
				sendStickyBroadcast(stickyIntent);

				Log.d(TAG, getResources().getString(R.string.broadcast_sent));
			}
		}
	}
}
