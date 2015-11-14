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

	public ObjectCreator()
	{
		in = new Scanner(System.in);
	}
	
	public Object createObject()
	{	
		Object object = null;
		UI.printObjectTypes();
		String selection = in.nextLine();
		
		if(selection.equalsIgnoreCase("1")) {
			return makeSimpleClass();
		}
		else if(selection.equalsIgnoreCase("2")) {
			return makeReferenceClass();
		}
		else if(selection.equalsIgnoreCase("3")) {
			return makePrimitiveArray();
		}
		else if(selection.equalsIgnoreCase("4")) {
			return makeComplexArray();
		}
		else if(selection.equalsIgnoreCase("5")) {
			return makeCollectionObject();
		}
	
		return object;
	}
	
	private SimpleClass makeSimpleClass()
	{
		System.out.println("Simple Class Object:: ");
		SimpleClass simple = new SimpleClass();

		System.out.print("Give me a number (integer): ");
		simple.numberOne = in.nextInt();
		//in.nextLine();
		System.out.print("Give me another number (integer): ");
		simple.numberTwo = in.nextInt();
		
		return simple;
	}

	private SimpleReference makeReferenceClass()
	{
		System.out.println("Simple Reference Object:: ");
		System.out.print("Give me the first number (integer): ");
		int one = in.nextInt();
		System.out.print("Give me the second number (integer): ");
		int two = in.nextInt();
		System.out.print("Give me the thrid number (integer): ");
		int three = in.nextInt();
		System.out.print("Give me the fourth number (integer): ");
		int four = in.nextInt();

		SimpleReference refer = new SimpleReference(one,two,three,four);
		return refer;
	}
	
	private PrimitiveArray makePrimitiveArray()
	{
		System.out.println("How many integers would you like to store? ");
		int length = in.nextInt();
		int[] simpleArray = new int[length];
		for(int n = 0; n<length; n++)
		{
			System.out.print("Give me an integer: ");
			simpleArray[n] = in.nextInt();
		}
		System.out.println("I am satisfied with your array of " + length + " integers");
		PrimitiveArray prim = new PrimitiveArray(simpleArray);
		return prim;
	}

	private ComplexArray makeComplexArray()
	{
		System.out.println("How many objects would you like to make? ");
		int length = in.nextInt();
		SimpleClass[] complexArray = new SimpleClass[length];
		for(int n = 0; n<length; n++)
		{
			complexArray[n] = makeSimpleClass();
		}
		ComplexArray comp = new ComplexArray(complexArray);
		return comp;
	}

	private CollectionObject makeCollectionObject()
	{
		int temp;
		CollectionObject collectNums = new CollectionObject();
		System.out.println("Enter numbers to add to your List, and a negative number finalize the list");
		while(true)
		{
			temp = in.nextInt();
			if (temp < 0)
				break;
			collectNums.addToList(temp);
		}
		return collectNums;
	}
}
