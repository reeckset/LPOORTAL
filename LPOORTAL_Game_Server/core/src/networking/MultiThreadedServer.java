package networking;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class MultiThreadedServer{
   
	ServerSocket socket;
	
	public MultiThreadedServer(int port) {
		try {
			socket = new ServerSocket(port);
			
			//Creates a server in an alternate thread
			Server server = new Server(socket);
			
			new Thread(server).start();
			   
		} catch (IOException e) {
			e.printStackTrace();
		}
	   
	}
   
}