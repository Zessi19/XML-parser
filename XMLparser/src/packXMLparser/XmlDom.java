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
	// ----------------------
	//     Class Variables
	// ----------------------
	private String fname;
	
	// Document Object Model (Java objects)
	private DocumentBuilderFactory dbf;
	private DocumentBuilder db;
	private Document doc;
	
	// List of String {Transaction type, Amount, Month}
	private List<String[]> dataTable;
	
	/*
	## Class creates DOM object from Java File and includes methods to parser DOM object ##
		- XML files (read to Java File) should have following notation
			<root>
				<credit>10.00</credit>
				<month number="1">
					<credit>100.00</credit>
				</month>
			 </root>
		
		- Public methods
			* Return filename						--> String     getFilename()
			* total credit vs debit sum 			--> double     getBalance()
			* total credit vs debit sum per month	--> double[]   getBalanceByMonth()
			* data table of all transactions		--> String[][] getDataTable()
			
	**DOM parser: "In order to be well-formed, XML must have exactly one root element". Therefore, we have
	added <root> element to all files located in the folder testData.
	More info: https://stackoverflow.com/questions/46355454/how-to-fix-error-the-markup-in-the-document-following-the-root-element-must-be
	*/

	// -------------------
	//     Constructor
	// -------------------
	
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
	
	// --------------------
	//    Public Methods
	// --------------------
	
	public String getFilename() {
		return this.fname;
	}
	
	/*
	double getBalance()
		- Calculate a total sum of all credit and debit transactions (include all months, invalid months and no month argument)
	 */
	public double getBalance() {
		double total = 0;
    	
    	NodeList creditList = this.doc.getElementsByTagName("credit");
    	NodeList debitList = this.doc.getElementsByTagName("debit");
    	
    	// Loop through credit nodes
    	for (int i=0; i<creditList.getLength(); i++) {
	    	Node node = creditList.item(i);
	        if (node.getNodeType() == Node.ELEMENT_NODE) {
	        	Element element = (Element) node;
	        	total += Double.parseDouble(element.getTextContent());
	        }
    	}
    	
    	// Loop through debit nodes
    	for (int i=0; i<debitList.getLength(); i++) {
	    	Node node = debitList.item(i);
	        if (node.getNodeType() == Node.ELEMENT_NODE) {
	        	Element element = (Element) node;
	        	total += Double.parseDouble(element.getTextContent());
	        }
    	}
    	
	    return total;
	}
	
	/*
	double[] getBalanceByMonth()
		- Return array, where sums[i] indicates a total credit and debit sum of i-th month
		- sums[0] corresponds to invalid months
		- sum[1] to sums[12] corresponds to valid months
	 */
	public double[] getBalanceByMonth() {
		double[] sums = new double[13];
		
		// Get <month> NodeList
		NodeList monthList = this.doc.getElementsByTagName("month");
		
		// Loop through <month> NodeList
		for (int i=0; i<monthList.getLength(); i++) {
			Node node = monthList.item(i);
    		
	        if (node.getNodeType() == Node.ELEMENT_NODE) {
	        	Element element = (Element) node;
	      
	        	// Get current month Integer value
	        	int iMonth = Integer.parseInt(element.getAttribute("number"));
	        	
	        	// Get current month node's child Credit/Debit Lists
	        	NodeList creditList = element.getElementsByTagName("credit");
	        	NodeList debitList = element.getElementsByTagName("debit");
	        	
	        	// Loop through Credit List
	        	for (int j=0; j<creditList.getLength(); j++) {
	        		// Valid month
	        		if (iMonth <=12 && iMonth > 0) {
	        			sums[iMonth] += Double.parseDouble(creditList.item(j).getTextContent());
	        		// Invalid month	
	        		} else {
	        			sums[0] += Double.parseDouble(creditList.item(j).getTextContent());
	        		}
	        	}
	        	
	        	// Loop through Debit List
	        	for (int j=0; j<debitList.getLength(); j++) {
	        		// Valid month
	        		if (iMonth <=12 && iMonth > 0) {
	        			sums[iMonth] += Double.parseDouble(debitList.item(j).getTextContent());
	        		// Invalid month
	        		} else {
	        			sums[0] += Double.parseDouble(debitList.item(j).getTextContent());
	        		}
	        	}
	        }
		}
		
		return sums;
	}
	
	/*
	String[][] getDataTable()
		- Return 2D table of all Transactions
		- One transaction -> One row
			* { Transaction type, money amount, month }
			* Invalid month = "-"
	 */
	public String[][] getDataTable() {
		this.dataTable.clear();
		
		// Get Credit/Debit Lists
		NodeList creditList = this.doc.getElementsByTagName("credit");
		NodeList debitList = this.doc.getElementsByTagName("debit");
		
		// Loop through Credit List
		for (int i=0; i<creditList.getLength(); i++) {
			Node node = creditList.item(i);
			String attrValue = "";
			
			// Check possible parent node month-attribute value
			if (node.getParentNode().hasAttributes()) {
				attrValue = node.getParentNode().getAttributes().item(0).getNodeValue();
			} else {
				attrValue = "-";
			}

			this.dataTable.add(new String[]{"Credit", node.getTextContent(), attrValue});
		}
		
		// Loop through Debit List
		for (int i=0; i<debitList.getLength(); i++) {
			Node node = debitList.item(i);
			String attrValue = "";
			
			// Check possible parent node month-attribute value
			if (node.getParentNode().hasAttributes()) {
				attrValue = node.getParentNode().getAttributes().item(0).getNodeValue();
			} else {
				attrValue = "-";
			}

			this.dataTable.add(new String[]{"Debit", node.getTextContent(), attrValue});
		}
	
		// Form 2D array
		String[][] array = new String[this.dataTable.size()][];
		for (int i = 0; i < this.dataTable.size(); i++) {
		    array[i] = this.dataTable.get(i);
		}
		
		return array;
	}

}
