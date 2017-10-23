package com.example.mydenonalarmclock;

import java.util.Calendar;
import java.util.Date;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.content.WakefulBroadcastReceiver;

public class MyAlarmReceiver extends WakefulBroadcastReceiver{

	private AlarmManager am;
	private PendingIntent pi;
	
	@Override
	public void onReceive(Context arg0, Intent arg1) {
		
		Intent service = new Intent(arg0,MySchedulingService.class);
		
		startWakefulService(arg0, service);
		
	}
	
public void setAlarm(Context context) {
		
		am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
		Intent intent = new Intent(context, MyAlarmReceiver.class);
		pi = PendingIntent.getBroadcast(context, 0, intent, 0);
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(System.currentTimeMillis());
		calendar.set(Calendar.DATE, calendar.get(Calendar.DATE)+1);
		calendar.set(Calendar.HOUR_OF_DAY, 6);
		calendar.set(Calendar.MINUTE, 20);
		
		am.setInexactRepeating(AlarmManager.RTC, calendar.getTimeInMillis(), am.INTERVAL_DAY, pi);

		
		ComponentName receiver = new ComponentName(context, MyBootReceiver.class);
		PackageManager pm = context.getPackageManager();
		
		pm.setComponentEnabledSetting(receiver, PackageManager.COMPONENT_ENABLED_STATE_ENABLED, PackageManager.DONT_KILL_APP);
		
	}

public void setAlarm(Context context, Alarm a) {
	
	am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
	Intent intent = new Intent(context, MyAlarmReceiver.class);
	pi = PendingIntent.getBroadcast(context, 0, intent, 0);
	
	Calendar calendar = Calendar.getInstance();
	calendar.setTimeInMillis(System.currentTimeMillis());
	if(calendar.get(Calendar.HOUR_OF_DAY) > a.getHours() && calendar.get(Calendar.MINUTE) > a.getMinutes()){
		calendar.set(Calendar.DATE, calendar.get(Calendar.DATE)+1);
	}else{
		calendar.set(Calendar.DATE, calendar.get(Calendar.DATE));
	}
	calendar.set(Calendar.HOUR_OF_DAY, a.getHours());
	calendar.set(Calendar.MINUTE, a.getMinutes());
	if(!a.isMonday() && !a.isTuesday() && !a.isWednesday() && !a.isThursday() && !a.isFriday() && !a.isSaturday() && !a.isSunday()){
		am.set(AlarmManager.RTC, calendar.getTimeInMillis(), pi);
	}else{
		if(a.isMonday()){
			calendar.set(Calendar.DAY_OF_WEEK,Calendar.MONDAY);
			am.setRepeating(AlarmManager.RTC, calendar.getTimeInMillis(), am.INTERVAL_DAY*7, pi);
		}
		if(a.isTuesday()){
			calendar.set(Calendar.DAY_OF_WEEK,Calendar.TUESDAY);
			am.setRepeating(AlarmManager.RTC, calendar.getTimeInMillis(), am.INTERVAL_DAY*7, pi);
		}
		if(a.isWednesday()){
			calendar.set(Calendar.DAY_OF_WEEK,Calendar.WEDNESDAY);
			am.setRepeating(AlarmManager.RTC, calendar.getTimeInMillis(), am.INTERVAL_DAY*7, pi);
		}
		if(a.isThursday()){
			calendar.set(Calendar.DAY_OF_WEEK,Calendar.THURSDAY);
			am.setRepeating(AlarmManager.RTC, calendar.getTimeInMillis(), am.INTERVAL_DAY*7, pi);
		}
		if(a.isFriday()){
			calendar.set(Calendar.DAY_OF_WEEK,Calendar.FRIDAY);
			am.setRepeating(AlarmManager.RTC, calendar.getTimeInMillis(), am.INTERVAL_DAY*7, pi);
		}
		if(a.isSaturday()){
			calendar.set(Calendar.DAY_OF_WEEK,Calendar.SATURDAY);
			am.setRepeating(AlarmManager.RTC, calendar.getTimeInMillis(), am.INTERVAL_DAY*7, pi);
		}
		if(a.isSunday()){
			calendar.set(Calendar.DAY_OF_WEEK,Calendar.SUNDAY);
			am.setRepeating(AlarmManager.RTC, calendar.getTimeInMillis(), am.INTERVAL_DAY*7, pi);
		}
	}
	
	ComponentName receiver = new ComponentName(context, MyBootReceiver.class);
	PackageManager pm = context.getPackageManager();
	
	pm.setComponentEnabledSetting(receiver, PackageManager.COMPONENT_ENABLED_STATE_ENABLED, PackageManager.DONT_KILL_APP);
	
}

	public void cancelAlarm(Context context){
		am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
		Intent intent = new Intent(context, MyAlarmReceiver.class);
		pi = PendingIntent.getBroadcast(context, 0, intent, 0);
		
		if(am != null){
			am.cancel(pi);
		}
		
		ComponentName receiver = new ComponentName(context, MyBootReceiver.class);
		PackageManager pm = context.getPackageManager();
		
		pm.setComponentEnabledSetting(receiver, PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP);
				
		
	}
	
}
