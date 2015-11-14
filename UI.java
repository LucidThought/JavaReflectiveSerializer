
public class UI
{
	public static void printObjectTypes()
	{
		System.out.println("Select your desired Class type");
		System.out.println("from the list below:");
		System.out.println("1 - Simple Object (primiives)");
		System.out.println("2 - Reference Object(s)");
		System.out.println("3 - Array of Primitives Object");
		System.out.println("4 - Array of Objects Object");
		System.out.println("5 - Java Collection Object");
	}

	public static void printSent()
	{
		System.out.println("Your current java runtime environment has been sent");
	}

	public static void confirmReceived()
	{
		System.out.println("\n:: Data received, deserializing... ::\n");
	}
	public static void confirmBuild()
	{
		System.out.println("\n:: Document deserialized into Object(s) ::\n");
	}
}
