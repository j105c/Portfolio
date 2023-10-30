/**
 * An echo server listening on port 6007. 
 * This server reads from the client
 * and echoes back the result. 
 *
 * This services each request in a separate thread.
 *
 * This conforms to RFC 862 for echo servers.
 *
 * @author - Greg Gagne.
 */

import java.net.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Vector;
import java.util.concurrent.*;

//Server to host chatroom
public class  Server
{
   public static final int DEFAULT_PORT = 15001;

    // construct a thread pool for concurrency	
   private static final Executor exec = Executors.newCachedThreadPool();
	
   public static void main(String[] args) throws IOException {
      ServerSocket sock = null;
   	
      try {
      	// establish the socket
         sock = new ServerSocket(DEFAULT_PORT);
         DataOutputStream in;
         ArrayList<String> messageQueue = new ArrayList<String>();
         ArrayList<BufferedWriter> socketList = new ArrayList<BufferedWriter>();
         Runnable thread = new BroadcastThread(messageQueue, socketList);
         exec.execute(thread);
      	
         while (true) {
         	/*
         	 * now listen for connections
         	 * and service the connection in a separate thread.
         	 */
        	 Socket client = sock.accept();
        	 in = new DataOutputStream(client.getOutputStream());
        	 OutputStreamWriter out = new OutputStreamWriter(in);
        	 
             BufferedWriter toClient = new BufferedWriter(out);
             
             socketList.add(toClient);
             //creating new connction with client who just joined
             Runnable task = new Connection(client, messageQueue, socketList);
             exec.execute(task);
             
             
             
         }
      }
      catch (IOException ioe) { System.err.println(ioe); }
      finally {
         if (sock != null)
            sock.close();
      }
   }
}
