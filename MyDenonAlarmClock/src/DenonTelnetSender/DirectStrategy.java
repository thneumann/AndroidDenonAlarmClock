package DenonTelnetSender;
import java.io.PrintWriter;


public class DirectStrategy implements AlarmStrategy{

	@Override
	public void startStrategy(PrintWriter out) {
		
		System.out.println("on");
		out.print("ZMON\r");
		out.flush();
		try {
			Thread.sleep(20000);
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
		System.out.println("volume");
		out.print("MV30\r");
		out.flush();

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
