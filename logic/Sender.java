package logic;

import java.io.IOException;
import java.io.StringWriter;
import java.net.MalformedURLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.xmlrpc.client.XmlRpcClient;

import messages.Footer;
import messages.Header;
import messages.UserMessage;
import messages.Userstructure;

public class Sender {
	public enum EntityType {visitor, admin, responsible};
    public enum SourceType {Front_End, Planning, Monitoring, Kassa, CRM, Facturatie};

    private final static String TASK_QUEUE_NAME = "kassa-queue";
    private final static String EXCHANGE_NAME = "rabbitexchange";

	public static void main(String[] args) throws JAXBException {
		
		// maak een header
		Header header = new Header("UserMessage", "desc", "kassa");
		// datastructure
		Userstructure userstructure = new Userstructure("UUID", "lastname", "firstname", "phonenumber", "gsmnumber", "email", "address", "company", "type", "payment status", "timestamp");
		// steek header en datastructure (userstructure) in message klasse
		UserMessage message = new UserMessage(header, userstructure);
		// genereer uit de huidige data de XML, de footer met bijhorende checksum wordt automatisch gegenereerd (via Footer Static functie)
		String xml = message.generateXML();
		
		System.out.println(xml);
		
		//stuur xml naar rabbitMQ
		/*try {
			sendMessage(xml);
		} catch (IOException | TimeoutException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		// kijk na welke messagetype de gekregen XML is
		String messageType = null;
		try {
			messageType = getMessageType(xml);
		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}
	    	
		// Map XML naar correct soort message
		if (messageType.equals("UserMessage")) {
			UserMessage userMessage = UserMessage.generateObject(xml);
			System.out.println(userMessage.getHeader().getMessageType());
		}

	}

	// returned de text die in de node 'messageType' zit, dit moet je weten en nakijken om JAXB zonder fouten naar de juiste klasse te laten mappen.
	public static String getMessageType(String xml) throws ParserConfigurationException, SAXException, IOException {
		InputSource is = new InputSource();
	    is.setCharacterStream(new StringReader(xml));
	    
	    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	    DocumentBuilder db = dbf.newDocumentBuilder();
	    
	    Document doc = db.parse(is);
	    NodeList nodes = doc.getElementsByTagName("messageType");
	    String messageType = nodes.item(0).getTextContent();
		
	    return messageType;
	}
	
	
	public static void sendMessage(String message) throws IOException, TimeoutException {
		
		ConnectionFactory factory = new ConnectionFactory();
        //factory.setHost("localhost");

        String username="test";
        String password="test";
        String virtualHost="/";
        String hostName="0:0:0:0:0:ffff:a03:3226";
        int portNumber=5672;

        factory.setUsername(username);
        factory.setPassword(password);
        factory.setVirtualHost(virtualHost);
        factory.setHost(hostName);
        factory.setPort(portNumber);

        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        //channel.exchangeDeclare(EXCHANGE_NAME, "fanout"); // other options: direct, topic, headers and fanout

        //channel.queueDeclare(TASK_QUEUE_NAME, false, false, false, null);
        channel.queueBind(TASK_QUEUE_NAME, EXCHANGE_NAME, "");

        channel.basicPublish(EXCHANGE_NAME, "", null, message.getBytes());


        System.out.println(" [x] Sending to exchange:   '" + EXCHANGE_NAME + "' message: '" + message + "'");

        channel.close();
        connection.close();
	}

}
