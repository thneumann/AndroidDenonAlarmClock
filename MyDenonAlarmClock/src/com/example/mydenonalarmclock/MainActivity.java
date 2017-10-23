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

import android.support.v7.app.ActionBarActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {

	MyAlarmReceiver alarm = new MyAlarmReceiver();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
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
	
	public void set(View v){
		alarm.setAlarm(this);
		Toast.makeText(this, "alarm set", Toast.LENGTH_LONG).show();
	}
	
	public void setAlarm(Alarm a){
		alarm.setAlarm(this,a);
		Toast.makeText(this, "alarm set", Toast.LENGTH_LONG).show();
	}
	
	public void cancel(){
		alarm.cancelAlarm(this);
		Toast.makeText(this, "alarm canceled", Toast.LENGTH_LONG).show();
	}
	
	public void addAlarm(View v){
		Intent intent = new Intent(this, Add_New_Alarm.class);
		startActivity(intent);
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		final ArrayList<Alarm> alarms = getAllAlarms();
		final ListViewAdapter myListViewAdapter = new ListViewAdapter(this.getBaseContext(), R.layout.alarmlistrow, alarms);
		ListView lv = (ListView) findViewById(R.id.listViewAlarms);
		lv.setAdapter(myListViewAdapter);
		lv.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				Alarm a = alarms.remove(arg2);
				cancel();
				myListViewAdapter.notifyDataSetChanged();
				saveAlarms(alarms);
				for (int i = 0; i < alarms.size(); i++) {
					setAlarm(alarms.get(i));
				}
				return true;
			}
			
		});
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
			Toast.makeText(this, "on resume", Toast.LENGTH_LONG).show();
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
	
}
