package networking;

import java.io.IOException;
import java.net.ServerSocket;


public class NetworkManager{
   
	ServerSocket socket;
	
	public NetworkManager(int port) {
		try {
			socket = new ServerSocket(port);
			
			//Creates a server in an alternate thread
			Server server = new Server(socket);
			
			new Thread(server).start();
			   
		} catch (IOException e) {
			e.printStackTrace();
		}
	   
	}
	
	public void updateGameWithRequest(ClientToServerMsg msg) {
		System.out.println("State: " + msg.controllerState);
		
	}
   
}