package DenonTelnetSender;

import java.io.PrintWriter;

public class SlowStrategy implements AlarmStrategy{

	@Override
	public void startStrategy(PrintWriter out) {
		// TODO Auto-generated method stub
		
		System.out.println("on");
		out.print("ZMON\r");
		out.flush();
		try {
			Thread.sleep(15000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("volume");
		out.print("MV20\r");
		out.flush();
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("favorite");
		out.print("ZMFAVORITE3\r");
		out.flush();
		
		try {
			Thread.sleep(15000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//out.println("MV?\n");
		for (int i = 20; i <= 40; i++) {
			System.out.println("volume");
			out.print("MV"+i+"\r");
			out.flush();;

			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	}

	
	
}
