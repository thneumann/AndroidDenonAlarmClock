package DenonTelnetSender;
import java.io.BufferedReader;
import java.io.IOException;


public class InputReader extends Thread{

	private BufferedReader in;
	private boolean stop = false;
	
	public InputReader(BufferedReader in){
		this.in = in;
	}
	
	@Override
	public void run() {
		while(!stop){
			try {
				if(this.in.ready()){
					System.out.println(this.in.readLine());
				}else{
					Thread.sleep(1000);
				}
			} catch (IOException | InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	
	public void stopReader(){
		stop = true;
	}
	
}
