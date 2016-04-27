import java.net.*;
import java.io.*;

public class GreetingClient
{
	public void wrapper(String serverName, int port, String data)
	{
		try
		{
			System.out.println("Connecting to " + serverName +
			" on port " + port);
			Socket client = new Socket(serverName, port);
			System.out.println("Just connected to " 
			+ client.getRemoteSocketAddress());
			OutputStream outToServer = client.getOutputStream();
			DataOutputStream out = new DataOutputStream(outToServer);
			out.writeUTF(data + " from : "
					  + client.getLocalSocketAddress());
			InputStream inFromServer = client.getInputStream();
			DataInputStream in =
						new DataInputStream(inFromServer);
			System.out.println("Server says " + in.readUTF());
			client.close();
		}catch(IOException e)
		{
			e.printStackTrace();
		}
	}

	public static void main(String [] args)
	{
		String serverName = args[0];
		int port = Integer.parseInt(args[1]);
		String data = args[2];
		GreetingClient client = new GreetingClient();
		client.wrapper(serverName, port, data);
	}
}