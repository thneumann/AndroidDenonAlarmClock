package com.example.mydenonalarmclock;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;
import java.util.ArrayList;
import java.util.Calendar;

import android.support.v7.app.ActionBarActivity;
import android.app.Activity;
import android.app.AlarmManager;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

public class Add_New_Alarm extends ActionBarActivity {

	private MyAlarmReceiver alarm = new MyAlarmReceiver();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add__new__alarm);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add__new__alarm, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public void setAlarm(Alarm a){
		alarm.setAlarm(this,a);
		Toast.makeText(this, "alarm set", Toast.LENGTH_LONG).show();
	}
	
	public void saveAlarm(View v){
		TimePicker tp = (TimePicker) findViewById(R.id.timePicker1);
		int hours = tp.getCurrentHour();
		int minutes = tp.getCurrentMinute();
		boolean monday = false, tuesday = false, wednesday = false, thursday = false, friday = false, saturday = false, sunday = false;
		
		CheckBox cb = (CheckBox) findViewById(R.id.checkBoxMonday);
		if(cb.isChecked()){
			monday = true;
		}
		cb = (CheckBox) findViewById(R.id.checkBoxTuesday);
		if(cb.isChecked()){
			tuesday = true;
		}
		cb = (CheckBox) findViewById(R.id.checkBoxWednesday);
		if(cb.isChecked()){
			wednesday = true;
		}
		cb = (CheckBox) findViewById(R.id.checkBoxThursday);
		if(cb.isChecked()){
			thursday = true;
		}
		cb = (CheckBox) findViewById(R.id.checkBoxFriday);
		if(cb.isChecked()){
			friday = true;
		}
		cb = (CheckBox) findViewById(R.id.checkBoxSaturday);
		if(cb.isChecked()){
			saturday = true;
		}
		cb = (CheckBox) findViewById(R.id.checkBoxSunday);
		if(cb.isChecked()){
			sunday = true;
		}
		
		EditText et = (EditText) findViewById(R.id.editTextName);
		String name = et.getText()+"";
		
		Alarm a = new Alarm(name, monday, tuesday, wednesday, thursday, friday, saturday, sunday, hours, minutes);
		
		ArrayList<Alarm> alarms = getAllAlarms();
		for (int i = 0; i < alarms.size(); i++) {
			if(alarms.get(i).getName().equals(a.getName())){
				Toast.makeText(this, "Name already given", Toast.LENGTH_LONG).show();
				return;
			}
		}
		alarms.add(a);
		saveAlarms(alarms);
		setAlarm(a);
		finish();
	}
	
	
	private void saveAlarms(ArrayList<Alarm> a){
		
		ObjectOutputStream oos = null;
		File f = new File(this.getApplicationContext().getFilesDir().getPath().toString()+"/Alarms.al");
		f.delete();
		try {
			f.createNewFile();

			FileOutputStream fos = this.getApplicationContext().openFileOutput("Alarms.al", Activity.MODE_PRIVATE);
			oos = new ObjectOutputStream(fos);
			oos.writeObject(a);
			fos.getFD().sync();
			Toast.makeText(this, "on save", Toast.LENGTH_LONG).show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	
	private ArrayList<Alarm> getAllAlarms(){
		ArrayList<Alarm> a = new ArrayList<Alarm>();
		
		ObjectInputStream ois = null;
		Object obj = null;
		
				
		FileInputStream fis;
		try {
			fis = this.getBaseContext().getApplicationContext().openFileInput("Alarms.al");
			ois = new ObjectInputStream(fis);
			a = (ArrayList<Alarm>) ois.readObject();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (StreamCorruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return a;
	}
	
}
