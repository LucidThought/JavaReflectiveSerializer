import java.io.IOException;
import java.io.FileWriter;
import org.jdom2.Element;
import org.jdom2.Document;
import org.jdom2.Attribute;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

public class CreateNewXMLFile
{
	public static void main(String[] args)
	{
		try
		{
			Element company = new Element("company");
			Document doc = new Document();
			doc.setRootElement(company);
			
			System.out.println(doc.getRootElement());
			
			Element staff = new Element("staff");
			staff.setAttribute(new Attribute("id","1"));
			staff.addContent(new Element("firstname").setText("ryan"));
			staff.addContent(new Element("lastname").setText("eggold"));
			staff.addContent(new Element("salary").setText("199999"));
			
			Element staff2 = new Element("staff");
			staff2.setAttribute(new Attribute("id","2"));
			staff2.addContent(new Element("firstname").setText("megan"));
			staff2.addContent(new Element("lastname").setText("boone"));
			staff2.addContent(new Element("salary").setText("188888"));
			
			doc.getRootElement().addContent(staff);
			doc.getRootElement().addContent(staff2);
			
			XMLOutputter outfile = new XMLOutputter();
			outfile.setFormat(Format.getPrettyFormat());
			outfile.output(doc, System.out);
			
			outfile.output(doc, new FileWriter("newxml.xml"));
			System.out.println("File Saved");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}