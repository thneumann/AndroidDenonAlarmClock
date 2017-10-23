package com.example.mydenonalarmclock;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class MyBootReceiver extends BroadcastReceiver{

	MyAlarmReceiver mar = new MyAlarmReceiver();
	
	@Override
	public void onReceive(Context arg0, Intent arg1) {
		if(arg1.getAction().equals("android.intent.action.BOOT_COMPLETED"));{
			mar.setAlarm(arg0);
		}
	}

}
