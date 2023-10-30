/**
 * Handler class containing the logic for echoing results back
 * to the client. 
 *
 * This conforms to RFC 862 for echo servers.
 *
 * @author Greg Gagne 
 */

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Vector;

public class Handler 
{
	public static final int BUFFER_SIZE = 256;
	
	/**
	 * this method is invoked by a separate thread
	 */
	public void process(Socket client, ArrayList<String> messageQueue, ArrayList<BufferedWriter> buff) throws java.io.IOException {
		//byte[] buffer = new byte[BUFFER_SIZE];
		BufferedReader in = null;
		//DataOutputStream out = null;
		BufferedOutputStream buffOut = null;
		
		String message;
		
		
		
		
		
		try {
			
			in = new BufferedReader (new InputStreamReader(client.getInputStream()));
			
			//out = new DataOutputStream(client.getOutputStream());
			
			//If message contains LEAVE protocol, remove client from list.
			while((message = in.readLine()) != null) {
				if (message.contains("LEAVE")) {
					buff.remove(client);
				}
				messageQueue.add(message);
				
			}
			
			
			
			
			
	
			
   		}
		catch (IOException ioe) {
			System.err.println(ioe);
		}
		finally {
			// close streams and socket
			if (in != null)
				in.close();
			if (buffOut != null)
				buffOut.close();
		}
	}

}
