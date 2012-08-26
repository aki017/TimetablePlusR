package info.aki017.TimetablePlusR;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class TimetableWidgetProvider extends AppWidgetProvider {
	private static final String TAG = "Widget";
	private static final long INTERVAL = 60000; // 5分

	@Override
	public void onEnabled(Context context) {
		Log.d(TAG, "onDisabled called!!");
		AlarmManager am = (AlarmManager) context
				.getSystemService(Context.ALARM_SERVICE);

		PendingIntent sender = getPendingAlarmIntent(context);
		am.cancel(sender);
		super.onEnabled(context);
	}

	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager,
			int[] appWidgetIds) {
		Log.d(TAG, "onUpdate");
		super.onUpdate(context, appWidgetManager, appWidgetIds);
	}

	@Override
	public void onDeleted(Context context, int[] appWidgetIds) {
		Log.d(TAG, "onDeleted");
		super.onDeleted(context, appWidgetIds);
	}

	@Override
	public void onDisabled(Context context) {
		Log.d(TAG, "onDisabled");
		super.onDisabled(context);
	}

	@Override
	public void onReceive(Context context, Intent intent) {
		Log.d(TAG, "onReceive");
		String action = intent.getAction();
		if (AppWidgetManager.ACTION_APPWIDGET_UPDATE.equals(action)) {
			setAlarm(context);
			Intent serviceIntent = new Intent(context,
					TimetableWidgetProvider.class);
			// context.stopService(serviceIntent);
			context.startService(serviceIntent);
		}
		super.onReceive(context, intent);

	}

	private void setAlarm(Context context) {
		AlarmManager am = (AlarmManager) context
				.getSystemService(Context.ALARM_SERVICE);
		long now = System.currentTimeMillis() + 1;
		long nextTime = now + INTERVAL - now % 1000;
		// メンバ変数ではダメだった
		// sender = getPendingAlarmIntent(context);
		PendingIntent sender = getPendingAlarmIntent(context);
		am.set(AlarmManager.RTC_WAKEUP, nextTime, sender);
	}

	private PendingIntent getPendingAlarmIntent(Context context) {
		Intent intent = new Intent(context, TimetableWidgetProvider.class);
		intent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
		PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0,
				intent, 0);
		return pendingIntent;
	}
}
