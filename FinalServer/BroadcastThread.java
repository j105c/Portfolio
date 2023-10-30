import java.io.BufferedWriter;
import java.util.ArrayList;
import java.util.Vector;



public class BroadcastThread implements Runnable{
	
	private ArrayList<String> messageQueue;
	private ArrayList<BufferedWriter> socketList;
	
	public BroadcastThread(ArrayList<String> messageQueue, ArrayList<BufferedWriter> socketList) {
		// TODO Auto-generated constructor stub
		this.messageQueue = messageQueue;
		this.socketList = socketList;	
		 
	}
	

	public void run() {
		while (true) {
			try {
				Thread.sleep(100);
				
				
				String message;
				
				
				//If there is a message, add it to the message que and then send
				//that message to every cleint on the socketLisr
				while (messageQueue.isEmpty() == false) {
					message = messageQueue.get(0);
					messageQueue.remove(0);
					
					for(int i = 0; i < socketList.size(); i++) {
						socketList.get(i).write(message + "\r\n");
						socketList.get(i).flush();
						
							
							
					}
					
				}
				
			}
			//handles errros
			catch (java.io.IOException ioe) {
		         System.err.println(ioe);
		         
		    }catch(InterruptedException ignore) {}
			
			
			
		
		}

	}
}
