import org.jdom2.*;
import org.jdom2.input.*;
import java.util.Scanner;
import java.io.InputStream;
import java.io.ByteArrayInputStream;
import java.util.*;

public class Deserializer
{
	private Scanner in;

	public Deserializer()
	{
		in = new Scanner(System.in);
	}

	public Document stringToDoc(String message)
	{
		Document doc = null;
		try
		{
			SAXBuilder docBuilder = new SAXBuilder();
			InputStream docStream = new ByteArrayInputStream(message.getBytes("UTF-8"));
			doc = docBuilder.build(docStream);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return doc;
	}
	
	public Object parseDocument(Document doc)
	{
		List<Element> objectElements = doc.getRootElement().getChildren("object");
	}

	public Object deserialize(Document doc)
	{
		Object obj = null;
		try
		{
			//initialiazeReferenceMap(doc);
			obj = parseDocument(doc);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
