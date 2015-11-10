import java.lang.reflect.*;
import java.lang.Class;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Vector;
import java.util.Scanner;

public class ObjectCreator
{
	private String className;
	private Scanner in;
	private Class userClass = null;
	private Object userObject = null;
	public UI messages = new UI();
	

	public ObjectCreator()
	{
		in = new Scanner(System.in);
	}
	
	public Object createObject()
	{	
		Object object = null;
		messages.printObjectTypes();
		String selection = in.nextLine();
		
		if(selection.equalsIgnoreCase("1")) {
			return makeSimpleClass();
		}
		else if(selection.equalsIgnoreCase("2")) {
						System.out.println("make this");
		}
		else if(selection.equalsIgnoreCase("3")) {
						System.out.println("make this");
		}
		else if(selection.equalsIgnoreCase("4")) {
						System.out.println("make this");
		}
		else if(selection.equalsIgnoreCase("5")) {
						System.out.println("make this");
		}
	
		return object;
	}
	
	private Object makeSimpleClass()
	{
		Class cl = null;
		Object simple = null;
		int[] myIntegers = new int[2];
		System.out.print("Enter an integer value: ");
		myIntegers[0] = in.nextInt();
		System.out.print("Enter another integer value: ");
		myIntegers[1] = in.nextInt();
		try
		{
			cl = Class.forName("SimpleClass");
			simple = userClass.newInstance();
			
			Field[] myFields = cl.getDeclaredFields();
			for(int f = 0; f < myFields.length; f++)
			{
				myFields[f].setInt(simple,myIntegers[f]);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return simple;
	}
	
	private Object setFields(Object obj)
	{
		Field[] myFields = userClass.getDeclaredFields();
		for(int f=0;f<myFields.length;f++)
		{
			if (!myFields[f].isAccessible()) {
				myFields[f].setAccessible(true); }
			if(myFields[f].getType().isPrimitive())
			{
				Object fieldType = myFields[f].getType();
				System.out.print("Set the value of the "+myFields[f].getType().getSimpleName()+" named "+myFields[f].getName()+" : ");

			}
		}
		return obj;
	}

/*	private SimpleObject createSimpleObject()
	{
		int a = GUI.getIntInput("Enter value for field \"a\":");
		int b = 
		
		return SimpleObject(a,b)
	} */
	
/*	private ObjectCollectionsObject createObjectsCollectionObject()
	{
		Vector<Object> list = new Vecotr<Object>();
		
		int collectionSize = GUI.getIntInput(.....);
		
		for(int i = 0; i < collectionSize; i++)
		{
			list.add(createObject());
		}
		
		return new ObjectCollectionObject(list);
	} */
}
