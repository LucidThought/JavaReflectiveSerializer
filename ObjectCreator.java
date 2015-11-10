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
	
	private SimpleClass makeSimpleClass()
	{
		System.out.println("Simple Class Object:: ");
		SimpleClass simple = new SimpleClass();

		System.out.print("Give me a number (integer): ");
		simple.numberOne = in.nextInt();
		in.nextLine();
		System.out.print("Give me some letters (String): ");
		simple.lettersTwo = in.nextLine();
		
		return simple;
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
