import java.net.Socket;
import java.net.ServerSocket;
import java.io.IOException;
import java.io.OutputStream;
import java.io.InputStream;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import org.jdom2.*;

public class Network
{
	private Socket socket = null;
	private int listeningPort = 12555;
	private String ip = "localhost";
	private final char END_OF_STREAM = (char)-1;
	
	public Network()
	{ }

	public Network(int port)
	{
		listeningPort = port;
	}

	public String recieve()
	{
		StringBuilder message = new StringBuilder();
		boolean reading = true;
		boolean socketClosed = false;
		try
		{
			Socket ears = new Socket(ip,listeningPort);

			char dataByte;
		
			while((dataByte = (char)ears.getInputStream().read()) != END_OF_STREAM)
			{
				message.append(dataByte);
			}
			socketClosed = dataByte == END_OF_STREAM;
			reading = false;
		}
		catch(UnknownHostException e)
		{
			e.printStackTrace();
		}
		catch(SocketTimeoutException e)
		{
		}
		catch(IOException e)
		{
		}
		return message.toString();

	}

	public void send(String message) throws IOException
	{
		ServerSocket server = new ServerSocket(listeningPort);
		
		System.out.println("Awaiting Connection...");
		Socket clientSocket = server.accept();
		System.out.println("Connection from "+clientSocket.getInetAddress().toString()+" accepted.");

		OutputStream os = clientSocket.getOutputStream();
		os.write(message.getBytes());
		os.flush();
		clientSocket.close();
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
