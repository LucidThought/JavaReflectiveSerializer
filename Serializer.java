import java.lang.reflect.*;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Map;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.ArrayList;
import java.lang.Integer;
import java.util.Vector;
import java.util.List;

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
		if (!objectClass.isArray())
		{
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
				fieldElement.addContent(serializeField(type, inner, target));

				thisObject.addContent(fieldElement);
			}
		}
		else 
		{
			Class componentType = objectClass.getComponentType();
			int length = Array.getLength(object);
			thisObject.setAttribute("length", Integer.toString(length));
			for(int l = 0; l < length; l++)
			{
				thisObject.addContent(serializeField(componentType,Array.get(object,l),target));
			}
		}
		return target;
	}

	private Element serializeField(Class type, Object owner, Document target) throws Exception
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
}
