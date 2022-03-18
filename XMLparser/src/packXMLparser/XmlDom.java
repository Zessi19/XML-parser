package packXMLparser;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.swing.JOptionPane;
import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class XmlDom {
	private String fname;
	private DocumentBuilderFactory dbf;
	private DocumentBuilder db;
	private Document doc;
	private List<String[]> dataTable;
	
	// DOM parser: "In order to be well-formed, XML must have exactly one root element". Therefore, we have
	// added <root> element to all files located in the folder testData.
	
	// More info: https://stackoverflow.com/questions/46355454/how-to-fix-error-the-markup-in-the-document-following-the-root-element-must-be
	
	// Constructor
	public XmlDom(File file) {
		this.fname = file.getName();
		
		// Instantiate the Factory
		this.dbf = DocumentBuilderFactory.newInstance();

		try {
	        // Process XML securely (recommended)
			this.dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);

	        // Parse XML file
	        this.db = dbf.newDocumentBuilder();
	        this.doc = db.parse(file);

	        // Normalization (Explained in Link below):
	        // http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
	        this.doc.getDocumentElement().normalize();  

		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Cannot parse DOM from a file: " + fname, "Infobox", JOptionPane.INFORMATION_MESSAGE);
		}
		
		this.dataTable = new ArrayList<>();
	}
	
	// -------------------
	//    Class Methods
	// -------------------
	
	public String getFilename() {
		return this.fname;
	}
	
	public double getBalance() {
		double total = 0;
    	
    	NodeList creditList = this.doc.getElementsByTagName("credit");
    	NodeList debitList = this.doc.getElementsByTagName("debit");
    	
    	for (int i=0; i<creditList.getLength(); i++) {
	    	Node node = creditList.item(i);
	        if (node.getNodeType() == Node.ELEMENT_NODE) {
	        	Element element = (Element) node;
	        	total += Double.parseDouble(element.getTextContent());
	        }
    	}
    	for (int i=0; i<debitList.getLength(); i++) {
	    	Node node = debitList.item(i);
	        if (node.getNodeType() == Node.ELEMENT_NODE) {
	        	Element element = (Element) node;
	        	total += Double.parseDouble(element.getTextContent());
	        }
    	}
	    return total;
	}
	
	public double[] getBalanceByMonth() {
		double[] sums = new double[13];
		
		// Get <month> NodeList
		NodeList monthList = this.doc.getElementsByTagName("month");
		
		// Loop through <month> NodeList
		for (int i=0; i<monthList.getLength(); i++) {
			Node node = monthList.item(i);
    		
	        if (node.getNodeType() == Node.ELEMENT_NODE) {
	        	Element element = (Element) node;
	        	
	        	int iMonth = Integer.parseInt(element.getAttribute("number"));
	        	
	        	NodeList creditList = element.getElementsByTagName("credit");
	        	NodeList debitList = element.getElementsByTagName("debit");
	        	
	        	for (int j=0; j<creditList.getLength(); j++) {
	        		if (iMonth <=12 && iMonth > 0) {
	        			sums[iMonth] += Double.parseDouble(creditList.item(j).getTextContent());
	        			
	        		} else {
	        			sums[0] += Double.parseDouble(creditList.item(j).getTextContent());
	        		}
	        	}
	        	
	        	for (int j=0; j<debitList.getLength(); j++) {
	        		if (iMonth <=12 && iMonth > 0) {
	        			sums[iMonth] += Double.parseDouble(debitList.item(j).getTextContent());
	        		} else {
	        			sums[0] += Double.parseDouble(debitList.item(j).getTextContent());
	        		}
	        	}
	        }
		}
		
		return sums;
	}
	
	public String[][] getDataTable() {
		this.dataTable.clear();
		
		NodeList creditList = this.doc.getElementsByTagName("credit");
		NodeList debitList = this.doc.getElementsByTagName("debit");
		
		for (int i=0; i<creditList.getLength(); i++) {
			Node node = creditList.item(i);
			String attrValue = "";
			
			if (node.getParentNode().hasAttributes()) {
				attrValue = node.getParentNode().getAttributes().item(0).getNodeValue();
			} else {
				attrValue = "-";
			}

			this.dataTable.add(new String[]{"Credit", node.getTextContent(), attrValue});
		}
		
		for (int i=0; i<debitList.getLength(); i++) {
			Node node = debitList.item(i);
			String attrValue = "";
			
			if (node.getParentNode().hasAttributes()) {
				attrValue = node.getParentNode().getAttributes().item(0).getNodeValue();
			} else {
				attrValue = "-";
			}

			this.dataTable.add(new String[]{"Debit", node.getTextContent(), attrValue});
		}
	
		String[][] array = new String[this.dataTable.size()][];
		for (int i = 0; i < this.dataTable.size(); i++) {
		    array[i] = this.dataTable.get(i);
		}
		
		return array;
	}

}
