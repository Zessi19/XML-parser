package packXMLparser;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JEditorPane;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import javax.swing.text.BadLocationException;
import javax.swing.JOptionPane;

public class DataJFrame extends JFrame {
	private static final long serialVersionUID = 7526472295622776147L;
	private JTextPane jTextPane;
	private JEditorPane jEditorPane;
	private JScrollPane jScrollPane;
	
	// Constructor 1. (Show XML File)
	public DataJFrame(File file, boolean darkMode) {
		String content = "";
		
		// Setup Frame parameters
		this.setupFrame(file.getName());
		
		// Setup JTextPane + Styles
		this.jTextPane = new JTextPane();
		this.jTextPane.setEditable(false);
        StyledDocument doc = this.jTextPane.getStyledDocument();
        
        Style style = this.jTextPane.addStyle("white", null);
        StyleConstants.setBackground(style, ConstantValues.darkBlue);
        StyleConstants.setForeground(style, ConstantValues.lightBlue);
		
        // Read File
		try {
			content += Files.readString(Paths.get(file.getPath()));
		} catch (Exception e) {
			e.printStackTrace();
        	JOptionPane.showMessageDialog(null, "Cannot open a file: " + file.getName(), "Infobox", JOptionPane.INFORMATION_MESSAGE);
		}
		
		// Set Styled Text
		try {
			if (darkMode) {
				doc.insertString(doc.getLength(), content, style);
			} else {
				doc.insertString(doc.getLength(), content, null);
			}
		} catch (BadLocationException e){
			e.printStackTrace();
		}
		
		// Set JScrollPane + Background Colors
		this.jScrollPane = new JScrollPane(this.jTextPane);
		this.add(this.jScrollPane);
		
		if (darkMode) {
			this.jTextPane.setBackground(ConstantValues.darkBlue);
			this.jScrollPane.setBackground(ConstantValues.darkBlue);
			this.setBackground(ConstantValues.darkBlue);
		}
		
		// Set location and visibility
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	// Constructor 2. (Show Parsing Results HTML String)
	public DataJFrame(String fname, String html) {
		this.setupFrame(fname);
		
		// Set Input Text		
	    this.jEditorPane = new JEditorPane();
		this.jEditorPane.setEditable(false);
		
		this.jEditorPane.setContentType("text/html");
		this.jEditorPane.setText(html);
		
		// Add Scroll bar 
		this.jScrollPane = new JScrollPane(jEditorPane);
		this.add(this.jScrollPane);
				
		// Set location and visibility
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	// Frame setup
	private void setupFrame(String filename) {
		this.setTitle(filename);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(ConstantValues.xPixels, ConstantValues.yPixels);
				
		// Set Logo
		ImageIcon logo = new ImageIcon("src/appData/logo_small.png");
		Image scaledLogo = logo.getImage().getScaledInstance(120,120,java.awt.Image.SCALE_SMOOTH);
		ImageIcon newLogo = new ImageIcon(scaledLogo);
		this.setIconImage(newLogo.getImage());
	}
	
}
