import java.net.Socket;
import java.net.ServerSocket;
import java.io.IOException;
import java.io.OutputStream;

public class Network
{
	private Socket socket = null;
	private int listeningPort = 12345;
	private String ip = "localhost";
	
	public Network()
	{ }

	public Network(int port)
	{
		listeningPort = port;
	}

	public void send(String message) throws IOException
	{
		ServerSocket server = new ServerSocket(listeningPort);
		
		System.out.println("Awaiting Connection...");
		Socket clientSocket = server.accept();
		System.out.println("Connection from "+clientSocket.getInetAddress().toString()+" accepted.");
		

		
	}

	public void setIP(String newIP)
	{
		ip = newIP;
	}

	public void setPort(int newPort)
	{
		listeningPort = newPort;
	}
}
