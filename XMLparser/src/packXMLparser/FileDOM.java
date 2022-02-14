package packXMLparser;

import java.io.File;
import java.io.IOException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class FileDOM {
	DocumentBuilderFactory dbf;
	DocumentBuilder db;
	Document doc;
	
	public FileDOM(File file) {
		
		// Instantiate the Factory
		this.dbf = DocumentBuilderFactory.newInstance();

		try {
	        // Process XML securely (recommended)
			this.dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);

	        // Parse XML file
	        this.db = dbf.newDocumentBuilder();

	        this.doc = db.parse(file);

	        // Normalization (Explained in Link below):
	        this.doc.getDocumentElement().normalize();

		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}
		
		// (Normalization): / http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
	}
	
	public void getBalance() {
		double totalSum = 0;
		
		// Get nodes under <balance>
		NodeList balanceList = this.doc.getElementsByTagName("balance");
		
	    for (int i=0; i<balanceList.getLength(); i++) {
	    	Node node = balanceList.item(i);
	    		
	        if (node.getNodeType() == Node.ELEMENT_NODE) {
	        	Element element = (Element) node;
	                  
	        	NodeList creditList = element.getElementsByTagName("credit");
	        	NodeList debitList = element.getElementsByTagName("debit");
	                  
	        	for (int j=0; j<creditList.getLength(); j++) {
	        		String value = element.getElementsByTagName("credit").item(j).getTextContent();
	        		totalSum += Double.parseDouble(value);
	        	}
	        	for (int j=0; j<debitList.getLength(); j++) {
	        		String value = element.getElementsByTagName("debit").item(j).getTextContent();
	        		totalSum += Double.parseDouble(value);
	        	}
	        }
	    }
	    
	    System.out.println("Total Balance:");
	    System.out.println(totalSum);
	}
	
	public void getBalanceByMonth() {
		
		System.out.print("Kesken");
	}

}
