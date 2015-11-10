import java.lang.reflect.*;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Map;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.ArrayList;

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
	private IdentityHashMap myMap = null;
	
	private int currentElement = 0;
	private ArrayList<Object> serializedObjects = new ArrayList<Object>();
	
	public Serializer()
	{
		myMap = new IdentityHashMap();
	}
	
	public Document serialize(Object object, Document target) throws Exception
	{
		String current = Integer.toString(myMap.size());
		myMap.put(object,current);
		Class objectClass = object.getClass();

		Element thisObject = new Element("object");
		thisObject.setAttribute("class",objectClass.getName());
		thisObject.setAttribute("id",current);
		target.getRootElement().addContent(thisObject);

		Field[] myFields = objectClass.getDeclaredFields();
		for(int f = 0; f < myFields.length; f++)
		{
			if (!myFields[f].isAccessible())
				myFields[f].setAccessible(true);

			Element fieldElement = new Element("field");
			fieldElement.setAttribute("name",myFields[f].getName());
			fieldElement.setAttribute("declaringclass",myFields[f].getDeclaringClass().getName());
			Class type = myFields[f].getType();
			Object inner = myFields[f].get(object);
			if (Modifier.isTransient(myFields[f].getModifiers()))
			{
				inner = null;
			}
			fieldElement.addContent(innerSerialize(type, inner, target));

			thisObject.addContent(fieldElement);
		}
		return target;
/*
		if(object == null)
		{
			
		}
		else if(!serializedObjects.contains(object))
		{
			serializedObjects.add(object);
			
			if(currentElement++ == 0)
			{
				doc = new Document();
				root = new Element("serialized");
				doc.setRootElement(root);
			}
			Class<?> c = object.getClass();
			Integer id = getID(object);
		
			Element objectElement = new Element("object");
			objectElement.setAttribute(new Attribute("class", c.getName()));
			objectElement.setAttribute(new Attribute("id", id.toString()));
			doc.getRootElement().addContent(objectElement);

			if(c.isArray())
			{
				Object array = object;
				objectElement.setAttribute(new Attribute("length", Integer.toString(Array.getLength(array))));
				
				if(c.getComponentType().isPrimitive())
				{
					for(int i = 0;i<Array.getLength(array);i++)
					{
						Element value = new Element("value");
						value.setText(Array.get(c,i).toString());
						objectElement.addContent(value);
					}
				}
				else
				{
					for(int j = 0;j < Array.getLength(array);j++)
					{
						Element ref = new Element("reference");
						id = getID(Array.get(c,j));
						if(id != -1)
						{
							ref.setText("");
							// add each array element
						}
					}
					for(int k = 0;k<Array.getLength(array);k++)
					{
						serialize(Array.get(array,k));
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
*/
	}

	private Element innerSerialize(Class type, Object owner, Document target) throws Exception
	{
		if (owner == null)
			return new Element("null");
		else if (!type.isPrimitive())
		{
			Element reference = new Element("reference");
			if(myMap.containsKey(owner))
				reference.setText(myMap.get(owner).toString());
			else
			{
				reference.setText(Integer.toString(myMap.size()));
				serialize(owner, target);
			}
			return reference;
		}
		else
		{
			Element value = new Element("value");
			value.setText(owner.toString());
			return value;
		}
	}

	private ArrayList<Element> serializeFields(Field[] fields, Object object)
	{
		ArrayList<Element> elements = new ArrayList<Element>();
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
		return elements;
	}
}
