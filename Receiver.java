import org.jdom2.Document;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import org.jdom2.*;

public class Receiver
{
	private static Deserializer deserializer = null;
	private static Visualizer visualizer = null;
	private static Network socketAcceptor = null;
	
	public static boolean connected;
	
	public static void main(String[] args)
	{
		initialize(args);
		try
		{
		String message = socketAcceptor.recieve();
		
		Document doc = deserializer.stringToDoc(message);
		Object obj = deserializer.deserializeThis(doc);

		visualizer.visualize(obj, true);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static void initialize(String[] args)
	{
		connected = true;
		
		socketAcceptor = new Network(12555);
		deserializer = new Deserializer();
		visualizer = new Visualizer();
		
	}
}
