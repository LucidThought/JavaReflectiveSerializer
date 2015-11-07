import java.util.Vector;

public class ObjectCreator
{
	public ObjectCreator()
	{
	}
	
	public Object createObject()
	{
		int selection = GUI.displayObjectMenu()
		
		Object object = null;
		
		switch(selection)
		{
			case 0:
				Sender.connected = false;
				brask;
			case 1:
				object = createSimpleObject();
				break;
			case 2:
			case 3:
			case 4:
			case 5:
				object = createObjectCollectionObject();
				break;
		}
			return object;
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
