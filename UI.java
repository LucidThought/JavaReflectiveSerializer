import java.util.Scanner;

public class UI
{
	public static void printObjectTypes()
	{
		System.out.println("Select your desired Class type");
		System.out.println("from the list below:");
		System.out.println("1 - Simple Object (primitives)");
		System.out.println("2 - Reference Object(s)");
		System.out.println("3 - Array of Primitives Object");
		System.out.println("4 - Array of Objects Object");
		System.out.println("5 - Java Collection Object");
	}

	public static void printSent()
	{
		System.out.println("\nYour current java runtime environment has been sent\n");
	}

	public static void confirmReceived()
	{
		System.out.println("\n:: Data received, deserializing... ::\n");
	}
	public static void confirmBuild()
	{
		System.out.println("\n:: Document deserialized into Object(s) ::\n");
	}
	public static boolean printMethods()
	{
		Scanner in = new Scanner(System.in);
		System.out.println("Would you like to show Methods and Constructors? (y)es/(n)o ");
		String showMe = in.nextLine();
		if (showMe.equalsIgnoreCase("n") || showMe.equalsIgnoreCase("no"))
		{
			return false;
		}
		else
		{
			return true;
		}
	}
}
