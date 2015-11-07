import java.lang.reflect.*;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;

import org.jdom2.Element;
import org.jdom2.Document;
import org.jdom2.Attribute;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

public class Serializer
{
	private Document doc = null;
	private Element root;
	
	private Integer referenceID = 0;
	private HashMap<Object, Integer> referenceMap = new HashMap<Object, Integer>();
	
	private int currentElement = 0;
	private ArrayList<Object> serializedObjects = new ArrayList<Object>();
	
	public Serializer()
	{
		
	}
	
	public Document serialize(Object object)
	{
		if(object == null)
		{
			
		}
		else if(!serializedObjects.contains(object))
		{
			serializedObjects.add(object);
			
			if(currentElement++ == 0)
			{
				doc = new Document()
				root = new Element("serialized");
				doc.setRootElement(root);
			}
			Class<?> c = object.getClass();
			Integer id = getID(object);
		
			Element objectElement = new Element("object");
			objectElement.setAttribute(new Attribute("class", c.getname()));
			objectElement.setAttribute(new Attribute("id", id.toString()));
			doc.getRootElement.addContent(objectElement);

			if(c.isArray())
			{
				Object array = object;
				objectElement.setAttribute(new Attribute("length"), Integer.toString(Array.getLength(array)));
				
				if(c.getComponentType().isPrimitive())
				{
					for(int i = 0;i<Array.getLength(array);i++)
					{
						Element value = new Element("value");
						value.setText()
						objectElement.addContent(value);
					}
				}
				else
				{
					for(int j = 0;j < Array.getLength(array);i++)
					{
						Element ref = new Element("reference");
						id = getID(Array, j);
						if(id != -1)
						{
							ref.setText()
							// add each array element
						}
					}
					for(int k - 0;k<Array.getLength(array);k++)
					{
						serialize(Array.get(array,k);
					}
				}
			}
			else
			{
				Class<?> tmpClass = c;
				while(tmpClass != null)
				{
					Field[] fields = tmpClass.getDeclaredFields();
					ArrayList<Element> fieldXML = serializeFields(fields, object);
					for(Element element:fieldXML)
						objectElement.addContent(element);
					
					tmpClass = tmpClass.getSuperclass();
				}
			}
		}
		if(currentElement == 0)
		{
			serializedObjects.clear();
			referenceID = 0;
		}
		
		return doc;
	}

	private ArrayList<Element> serializeFields(Field[] fields, Object object)
	{
		ArrayList<Element> elements = new ArrayList<Element>;
		for(Field f : fields)
		{
			try
			{
				if(!f.isAccessible())
					f.setAccessible(true);
				
				// Add code here for recursion if the Field is not primitive
			}
			catch(Exception e)
			{
				
			}
		}
	}
	
	private int getID(Object object)
	{
		Integer id = referenceID;
		
		if(referenceMap.containsKey(object))
			id = referenceMap.get(object);
		else
		{
			referenceMap.put(object, id);
			referenceID++;
		}
		
		return id;
	}

}
