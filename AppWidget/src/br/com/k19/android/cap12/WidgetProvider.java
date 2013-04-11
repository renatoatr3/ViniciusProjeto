package br.com.k19.android.cap12;

import java.util.Random;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

public class WidgetProvider extends AppWidgetProvider{
	
	private static final String ACTION_CLICK = "ACTION_CLIICK";
	
	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
		
		ComponentName thisWidget = new ComponentName(context, WidgetProvider.class);
		
		int[] allWidgetId = appWidgetManager.getAppWidgetIds(thisWidget);
		
		for (int widgetId : allWidgetId) {
			
			int number = (new Random().nextInt(100));
			
			RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget_layout);
			
			remoteViews.setTextViewText(R.id.update, context.getString(R.string.number_label, number));
			
			Intent intent = new Intent(context, WidgetProvider.class);
			
			intent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
			intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, appWidgetIds);
			
			PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
			remoteViews.setOnClickPendingIntent(R.id.update, pendingIntent);
			appWidgetManager.updateAppWidget(widgetId, remoteViews);
		}
	}
}
