package DenonTelnetSender;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import com.trigg.alarmclock.AlarmListActivity;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;


public class DenonAlarm extends Thread{
	
	private String ip;
	private AlarmStrategy as;
	
	public DenonAlarm(String ip, AlarmStrategy as){
		
		this.ip = ip;
		this.as = as;

	}
	
	@Override
	public void run() {

		Log.v("ohje", "thread started");
		Socket sock = null;
		PrintWriter out = null;
		BufferedReader in = null;
		
		try {
			Log.v("ohje", "start socket");
			sock = new Socket(this.ip, 23);
			out = new PrintWriter(sock.getOutputStream(), false);
			in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
			InputReader ir = new InputReader(in);
			ir.start();
			as.startStrategy(out);
			
			//wait till execution of strategy finished
			ir.stopReader();
			
			Thread.sleep(1500);
			System.out.println("before close!!!");
			out.close();
			in.close();
			sock.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
