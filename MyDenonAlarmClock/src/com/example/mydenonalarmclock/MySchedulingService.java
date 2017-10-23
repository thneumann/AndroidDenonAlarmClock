package com.example.mydenonalarmclock;

import DenonTelnetSender.DenonAlarm;
import DenonTelnetSender.DirectStrategy;
import DenonTelnetSender.SlowStrategy;
import android.app.Activity;
import android.app.IntentService;
import android.content.Intent;
import android.widget.Toast;

public class MySchedulingService extends IntentService {

	
	
	public MySchedulingService() {
		super("SchedulingService");
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		
		//DO something
		
		DenonAlarm da = new DenonAlarm("192.168.100.228", new SlowStrategy());
		da.start();
		
	}
	
	

	
	
}
