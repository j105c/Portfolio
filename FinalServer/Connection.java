/**
 * This is the separate thread that services each
 * incoming echo client request.
 *
 * @author Greg Gagne 
 */

import java.io.BufferedWriter;
import java.net.*;
import java.util.*;

public class Connection implements Runnable
{
	private Socket	client;
	private ArrayList<String> messageQueue;
	private static Handler handler = new Handler();
	private ArrayList<BufferedWriter> buff;
	
	public Connection(Socket client, ArrayList<String> queue, ArrayList<BufferedWriter> buff) {
		this.client = client;
		this.messageQueue = queue;
		this.buff = buff;
	}

    /**
     * This method runs in a separate thread.
     */	
	//Simple connection method
	public void run() { 
		
		try {
			
			handler.process(client, messageQueue, buff);
			
		}
		catch (java.io.IOException ioe) {
			System.err.println(ioe);
		}
	}
}

