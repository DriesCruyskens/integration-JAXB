package messages;

import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "message")
@XmlType(propOrder = { "header", "datastructure", "footer" })

public class UserMessage {
	private Header header;
	private Userstructure datastructure;
	private Footer footer;
	
	public UserMessage(Header header, Userstructure userstruct) {
		super();
		this.header = header;
		this.datastructure = userstruct;
	}
	
	public UserMessage() {
		
	}
	
	public Header getHeader() {
		return header;
	}
	public void setHeader(Header header) {
		this.header = header;
	}
	public Footer getFooter() {
		return footer;
	}
	public void setFooter(Footer footer) {
		this.footer = footer;
	}
	

	
	public Userstructure getDatastructure() {
		return datastructure;
	}

	public void setDatastructure(Userstructure datastructure) {
		this.datastructure = datastructure;
	}

	public String generateXML() throws JAXBException {
		JAXBContext context = JAXBContext.newInstance(this.getClass());
		Marshaller m = context.createMarshaller();
		//m.setProperty(Marshaller.JAXB_ENCODING, "UTF-8"); //string
		m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE); // formatted
		java.io.StringWriter sw = new StringWriter();
		
		String checksum = Footer.generateChecksum(datastructure);
		footer = new Footer(checksum);
		
		m.marshal(this, sw);
		
		return sw.toString();
	}
}
