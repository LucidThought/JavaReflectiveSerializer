 public class SimpleReference
 {
	public SimpleClass classOne;
	public SimpleClass classTwo;
	
	public SimpleReference()
	{
	}

	public SimpleReference(int one, int two, int three, int four)
	{
		classOne = new SimpleClass(one,two);
		classTwo = new SimpleClass(three,four);
	}
 }
