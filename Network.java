import java.net.Socket;
import java.net.ServerSocket;
import java.net.InetAddress;
import java.io.IOException;
import java.io.OutputStream;
import java.io.InputStream;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.Scanner;

import org.jdom2.*;

public class Network
{
	private ServerSocket server = null;
	private int listeningPort = 12555;
	private String ip = "localhost";
	private final char END_OF_STREAM = (char)-1;
	private Scanner in;
	
	public Network()
	{ 
		in = new Scanner(System.in);
	}

	public Network(int port)
	{
		listeningPort = port;
		in = new Scanner(System.in);
	}

	public String recieve()
	{
		StringBuilder message = new StringBuilder();
		boolean reading = true;
		boolean socketClosed = false;
		try
		{
			ip = getIP();
			listeningPort = getPort();
			Socket ears = new Socket(ip,listeningPort);

			char dataByte;
		
			while((dataByte = (char)ears.getInputStream().read()) != END_OF_STREAM)
			{
				message.append(dataByte);
			}
			socketClosed = dataByte == END_OF_STREAM;
			reading = false;
			if (socketClosed)
				ears.close();
		}
		catch(UnknownHostException e)
		{
			e.printStackTrace();
		}
		catch(SocketTimeoutException e)
		{
			e.printStackTrace();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		return message.toString();

	}

	public void send(String message) throws IOException
	{
		server = new ServerSocket(listeningPort);		

		System.out.println("Server at " + ip + " Awaiting Connection on Port " + server.getLocalPort() + " ...");
		Socket clientSocket = server.accept();
		System.out.println("Connection from "+clientSocket.getInetAddress().toString()+" accepted.");

		OutputStream os = clientSocket.getOutputStream();
		os.write(message.getBytes());
		os.flush();
		clientSocket.close();
		server.close();
	}

	public void setIP(String newIP)
	{
		ip = newIP;
	}

	public void setPort(int newPort)
	{
		listeningPort = newPort;
	}
	
	public String getIP()
	{
		System.out.print("Please enter the network location of the sender: ");
		String location = in.nextLine();
		return location;
	}
	public int getPort()
	{
		System.out.print("Please enter the desired port: ");
		int port = in.nextInt();
		return port;
	}
}
