package messages;

import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.codec.digest.DigestUtils;

@XmlRootElement(name = "footer")
public class Footer {
	private String checksum;

	public Footer(String checksum) {
		super();
		this.checksum = checksum;
	}
	
	public Footer() {
		
	}

	public String getChecksum() {
		return checksum;
	}

	public void setChecksum(String checksum) {
		this.checksum = checksum;
	}
	
	public static String generateChecksum(Object o) throws JAXBException {
		java.io.StringWriter sw = new StringWriter();
		JAXBContext pContext = JAXBContext.newInstance(o.getClass());
		Marshaller marshaller = pContext.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
		marshaller.marshal(o, sw);
		return DigestUtils.sha1Hex(sw.toString());
	}
	
}