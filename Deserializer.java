import org.jdom2.*;
import org.jdom2.input.*;
import java.util.Scanner;
import java.io.InputStream;
import java.io.ByteArrayInputStream;
import java.util.*;
import java.lang.reflect.*;
import java.lang.*;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import java.io.FileWriter;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import java.lang.Integer;
import java.util.List;

public class Deserializer
{
	private Scanner in;

	public Deserializer()
	{
		in = new Scanner(System.in);
	}

	public Document stringToDoc(String message) throws Exception
	{
		Document doc = null;
		try
		{
			SAXBuilder docBuilder = new SAXBuilder();
			InputStream docStream = new ByteArrayInputStream(message.getBytes("UTF-8"));
			doc = docBuilder.build(docStream);
			
			XMLOutputter outfile = new XMLOutputter();
			outfile.setFormat(Format.getPrettyFormat());
			
			outfile.output(doc, new FileWriter("Recieved.xml"));
			System.out.println("File Saved");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return doc;
	}

	public static Object deserializeThis(Document state) throws Exception
	{
		List objects = state.getRootElement().getChildren();

		Map map = new HashMap();
		instantiate(map, objects);
		assignValues(map, objects);

		return map.get("0");
	}

	public static void instantiate(Map map, List objects) throws Exception
	{
		for(int i = 0; i<objects.size(); i++)
		{
			Element objElem = (Element) objects.get(i);
			Class objClass = Class.forName(objElem.getAttributeValue("class"));
			Object instanceOf = null;
			if(!objClass.isArray())
			{
				Constructor cons = objClass.getDeclaredConstructor();//get default constructor
				if(!Modifier.isPublic(cons.getModifiers()))
				{
					cons.setAccessible(true);
				}
				instanceOf = cons.newInstance();
			}
			else
			{
				instanceOf = Array.newInstance(objClass.getComponentType(), Integer.parseInt(objElem.getAttributeValue("length")));
			}
			map.put(objElem.getAttributeValue("id"), instanceOf);
		}
	}

	public static void assignValues(Map map, List objects) throws Exception
	{
		for(int i = 0; i<objects.size(); i++)
		{
			Element objElem = (Element) objects.get(i);
			Object instanceOf = map.get(objElem.getAttributeValue("id"));
			Class declaringClass = instanceOf.getClass();
			List fieldElems = objElem.getChildren();
			if(!instanceOf.getClass().isArray())
			{
				for(int e = 0; e < fieldElems.size(); e++)
				{
					Element field = (Element) fieldElems.get(e);
					Element value = (Element) field.getChildren().get(0);
					String fName = field.getAttributeValue("name");
					Field f = declaringClass.getDeclaredField(fName);
					if(!Modifier.isPublic(f.getModifiers())) {
						f.setAccessible(true); }
					if(field.getChild("value") == null) {
						f.set(instanceOf, deserializeVal(value, f.getType(), map)); }
					else {
						f.set(instanceOf, deserializeVal(value, f.getType(), map)); }
				}
			}
			else
			{
				Class component = instanceOf.getClass().getComponentType();
				for (int a = 0; a<fieldElems.size(); a++)
				{
					Array.set(instanceOf, a, deserializeVal((Element) fieldElems.get(a), component, map));
				}
			}
		}
	}

	public static Object deserializeVal(Element elem, Class type, Map map) throws ClassNotFoundException
	{
		String eType = elem.getName();
		if (eType.equals("null"))
			return null;
		else if (eType.equals("reference"))
			return map.get(elem.getText());
		else
		{

			if (type.equals(byte.class))
			{
				return Byte.valueOf(elem.getText());
			}
			else if (type.equals(short.class))
			{
				return Short.valueOf(elem.getText());
			}
			else if (type.equals(long.class))
			{
				return Long.valueOf(elem.getText());
			}
			else if (type.equals(float.class))
			{
				return Float.valueOf(elem.getText());
			}
			else if (type.equals(int.class))
			{
				return Integer.valueOf(elem.getText());
			}
			else if (type.equals(double.class))
			{
				return Double.valueOf(elem.getText());
			}
			else if (type.equals(char.class))
			{
				return Character.valueOf(elem.getText().charAt(0));
			}
			else if (type.equals(boolean.class))
			{
				if (elem.getText().equals("true"))
					return Boolean.TRUE;
				else
					return Boolean.FALSE;			
			}
			else
			{
				return elem.getText();
			}
		}
	}
}
