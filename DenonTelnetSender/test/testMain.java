
public class testMain {

	public static void main(String[] args) {
		DenonAlarm da = new DenonAlarm("192.168.100.228", new DirectStrategy());
		da.start();
	}
	
}
