import org.jdom2.Document;

public class Receiver
{
	private static Deserializer deserializer = null;
	private static Visualizer visualizer = null;
	private static SocketAcceptor socketAcceptor = null;
	
	public static boolean connected;
	
	public static void main(String[] args)
	{
		initialize(args);
		
		String message = socketAcceptor.getMessage();
		
		Document doc = deserializer.stringToDocument(message);
		Object obj = deserializer.deserialize(doc);
		
		visualizer.visualize(obj, true);
	}
	
	public static void initialize(String[] args)
	{
		connected = true;
		
		socketAcceptor = new SocketAcceptor(4321);
		
		deserializer = new Deserializer();
		visualizer = new Visualizer();
		
		if(connected)
			socketAcceptor.acceptConnection();
	}
}