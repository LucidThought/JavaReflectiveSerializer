
public class Deserializer
{
	public Deserializer()
	{
		in = new Scanner(System.in);
	}

	public Document stringToDoc(String message)
	{
		Document doc = null;
		try
		{
			SAXBuilder docBuilder = new SAXBuilder();
			InputStream socStrem = new ByteArrayInputStream(documentString.getBytes("UTF-8"));
			doc = docBuilder.build(docStream);
		}
		catch (Exception e)
		{
			e.printStackTrace()
		}
		return doc;
	}
	
	public Object parseDocument()
	{
		List<Element> objectElements = doc.getRootElement().getChildren("object");
	}

	public Object deserialize(Document doc)
	{
		Object obj = null;
		try
		{
			initialiazeReferenceMap(doc);
			obj = parseDocument(doc);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
