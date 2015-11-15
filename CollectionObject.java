import java.util.List;
import java.util.ArrayList;
import java.lang.Integer;

public class CollectionObject
{
	public List<Integer> collectionList = new ArrayList<Integer>();

	public CollectionObject()
	{
	}

	public void addToList(int number)
	{
		collectionList.add(number);
	}
}
