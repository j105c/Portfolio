/**
 * This thread is passed a socket that it reads from. Whenever it gets input
 * it writes it to the ChatScreen text area using the displayMessage() method.
 */

import java.io.*;
import java.net.*;
import javax.swing.*;

public class ReaderThread implements Runnable
{
	Socket server;
	BufferedReader fromServer;
	ChatScreen screen;
	String username;
	public ReaderThread(Socket server, ChatScreen screen, String username) {
		this.server = server;
		this.screen = screen;
		this.username = username;
		
	}
	//This porgram breaks up the message sent into 2 or 3 parts depending
	//on the message
	//If join, let chat know who joins and send messages
	//If send, send proper message
	//if LEAVE, leave chatroom
	public void run() {
		try {
			fromServer = new BufferedReader(new InputStreamReader(server.getInputStream()));
			while (true) {
				String message = fromServer.readLine();
				String parts[] = message.split(" ");
				
				if (parts[0].equals("CRP1.0JOIN")) {
					screen.displayMessage(parts[1] + " Has Joined the Chat\r\n");
					
				}
				else if(parts[0].equals("CRP1.0SEND")) {
					int space = message.indexOf(parts[1]);
					String newMessage = message.substring(space + parts[1].length());
					screen.displayMessage(parts[1] + ": " + newMessage + "\r\n");
		
				}else if(parts[0].equals("CRP1.0LEAVE")) {
					screen.displayMessage(parts[1] + " Has Left the Chat\r\n");
					
					
				}
				
				
			}
		}
		catch (IOException ioe) { System.out.println(ioe); }

	}
}