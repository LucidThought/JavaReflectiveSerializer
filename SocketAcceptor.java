import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class SocketAcceptor
{
	private ServerSocket serverSocket = null;
	private Socket socket = null;
	private int socketTimeoutDuration = 2000;
	private final char END_OF_STREAM = (char)-1;
	
	public SocketAcceptor()
	{
		createServerSocket(4321);
	}
	public SocketAcceptor(int newSocket)
	{
		create
	}
	
	private void createServerSocket(int port)
	{
		try
		{
			serverSocket = new ServerSocket(port);
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public String getMessage()
	{
		StringBuilder message = new StringBuilder();
		boolean reading = true;
		boolean socketClosed = false;
		
		while(socket != null && socket.isConnected() && reading)
		{
			try
			{
				char dataByte;
				
				while((dataByte = (char)socket.getInputStream().read()) != END_OF_STREAM)
				{
					message.append(dataByte);
				}
				socketClosed = dataByte == END_OF_STREAM;
				reading = false;
			}
			catch(SocketTimeoutException e)
			{
			}
			catch(IOException e)
			{
			}
		}
		if(socketClosed)
			Receiver.connected = false;
		else
			System.out.println("Socket is not closed");
		
		return message.toString();
	}

	public void acceptConnection()
	{
		try
		{
			socket = serverSocket.accept();
			socket.setSoTimeout(socketTimeoutDuration);
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
}
