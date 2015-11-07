import java.lang.reflect.Method;
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
			object = setFields(object);

			return object;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
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
				if(myFields[f].getType().getSimpleName() == "Integer") {
					int value = in.nextInt();
					myFields[f].set(obj,value); }
				else if(myFields[f].getType().getSimpleName() == "String") {
					String value = in.nextLine();
					myFields[f].set(obj,value); }
				else if(myFields[f].getType().getSimpleName() == "Boolean") {
					boolean value = in.nextBoolean();
					myFields[f].set(obj,value); }
				else if(myFields[f].getType().getSimpleName() == "Double") {
					double value = in.nextDouble();
					myFields[f].set(obj,value); }
				else if(myFields[f].getType().getSimpleName() == "Float") {
					float value = in.nextFloat();
					myFields[f].set(obj,value); }
				else if(myFields[f].getType().getSimpleName() == "Long") {
					long value = in.nextLong();
					myFields[f].set(obj,value); }
				else if(myFields[f].getType().getSimpleName() == "Short") {
					short value = in.nextShort();
					myFields[f].set(obj,value); }
				else if(myFields[f].getType().getSimpleName() == "Byte") {
					byte value = in.nextByte();
					myFields[f].set(obj,value); }
			}
		}
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
