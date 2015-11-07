import java.util.Vector;
import java.util.Scanner;

public class ObjectCreator
{
	private String className;
	private Scanner in;
	private Class userClass = null;

	public ObjectCreator()
	{
		in = new Scanner(System.in);
		System.out.print("Enter the name of your class file: ");
		className = in.nextLine();
	}
	
	public Object createObject()
	{	
		try
		{
			userClass = Class.forName(className);
			Object object = userClass.newInstance();
			setFields(object);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return object;
	}
	
	private setFields(Object obj)
	{
		Field[] myFields = userClass.getDeclaredFields();
		for(int f=0;f<myFields.length;f++)
		{
			if (!myFields[f].isAccessible()) {
				myFields[f].setAccessible(true); }
			if(myFields[f].getType().isPrimitive())
			{
				Object fieldType = myFields[f].getType();
				System.out.print("Set the value of the "+myFields[f].getType()+" named "+myFields[f].getName()+" : ");
			}
		}
	}

	private Field getTypeClassification(Field fld, Object obj)
	{
		if (fld.getType() == "")
			
	}

	private SimpleObject createSimpleObject()
	{
		int a = GUI.getIntInput("Enter value for field \"a\":");
		int b = 
		
		return SimpleObject(a,b)
	}
	
	private ObjectCollectionsObject createObjectsCollectionObject()
	{
		Vector<Object> list = new Vecotr<Object>();
		
		int collectionSize = GUI.getIntInput(.....);
		
		for(int i = 0; i < collectionSize; i++)
		{
			list.add(createObject());
		}
		
		return new ObjectCollectionObject(list);
	}
}
