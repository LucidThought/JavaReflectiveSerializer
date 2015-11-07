import java.net.Socket;
import java.io.OutputStream;

public class Network
{
	private Socket socket = null;
	private int port = 4321;
	private String ip = "localhost";
	
	public void send(String message)
	{
		if(socket == null)
		{
			connect();
		}
		else if (socket.isConnected())
		{
			try
			{
				OutputStream out = socket.getOutputStream();
				out.write(message.getBytes());
				out.flush();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		else
		{
			System.out.println("Socket unavailable");
		}
	}
	
	public void connect()
	{
		try
		{
			socket = new Socket(ip, port);
		}
		catch(Exception e)
		{
			Sender.connected = false;
			e.printStackTrace();
		}
	}
}