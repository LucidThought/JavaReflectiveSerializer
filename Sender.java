import java.lang.reflect.*;
import java.lang.Class;
import java.util.Arrays;
import java.util.ArrayList;

import org.jdom2.Element;
import org.jdom2.Document;
import org.jdom2.Attribute;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

public class Sender
{
	private static Network networkConnection = null;
	private static Serializer serializer = null;
	private static ObjectCreator objCreator = null;
	
	public static boolean connected;
	private String fileName = "Objects.xml";
	
	public static void main(String[] args)
	{
		initialize(args);
		Object obj = objCreator.createObject();

		while(connected)
		{	
			if(!connected)
			{
				continue;
			}
			try
			{
				Document doc = serializer.serialize(obj, new Document(new Element("serialized")));
				String xmlString = XMLtoString(doc);
			
				networkConnection.send(xmlString);

				connected = false;
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		UI.printSent();
	}
	
	public static void initialize(String[] args)
	{
		connected = true;
		networkConnection = new Network();
		serializer = new Serializer();
		objCreator = new ObjectCreator();
		
		if(args.length == 2)
		{
			networkConnection.setIP(args[0]);
			networkConnection.setPort(Integer.valueOf(args[1]));
		}
	}
	
	public static String XMLtoString(Document doc)
	{
		XMLOutputter outString = new XMLOutputter();
		return outString.outputString(doc);
	}
}
