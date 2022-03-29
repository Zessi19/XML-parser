package packXMLparser;

import java.io.File;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.JEditorPane;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.basic.BasicScrollBarUI;
import javax.swing.JOptionPane;

/*
## Class implements extended JPanel for guide.html and keybinds.html ##
	-  Uses JSrollPane -> no HTML-file max size
	
	- Public Methods
		* void setDarkMode()
		* void SetLightMode()
*/

public class HtmlJPanel extends JPanel {
	private static final long serialVersionUID = 1076966878409007128L;
	private File originalFile, darkFile;
	private JEditorPane jEditorPane;
	private JScrollPane jScrollPane;
	
	private Color lightEditorPane, lightScrollPane, lightBackground;
	private Border lightBorder;
	
	public HtmlJPanel(String fname, String darkFname) {
	    this.jEditorPane = new JEditorPane();
		this.jEditorPane.setEditable(false);
		this.jEditorPane.setContentType("text/html");
		
		// Read + Setup Light HTML-file
        try {
        	this.originalFile = new File("src/appData/" + fname);
        	this.jEditorPane.setPage(this.originalFile.toURI().toURL());
        	
        } catch (Exception e) {
        	e.printStackTrace();
        	JOptionPane.showMessageDialog(null, "Cannot open a file: " + fname, "Infobox", JOptionPane.INFORMATION_MESSAGE);
        }
        
        // Read Dark HTML-file
        try {
        	this.darkFile = new File("src/appData/" + darkFname);
        	
        } catch (Exception e) {
        	e.printStackTrace();
        	JOptionPane.showMessageDialog(null, "Cannot open a file: " + darkFname, "Infobox", JOptionPane.INFORMATION_MESSAGE);
        }

        // Add JScrollPane
        this.jScrollPane = new JScrollPane(jEditorPane);
        this.jScrollPane.setPreferredSize(new Dimension(ConstantValues.xPixelsLarge-45, ConstantValues.yPixelsLarge-80));
        
        this.add(jScrollPane);
        this.setVisible(false);
        
        // Save light mode parameters
        this.jEditorPane.setOpaque(true);
        this.jScrollPane.setOpaque(true);
        
        this.lightBackground = this.getBackground();
        this.lightEditorPane = this.jEditorPane.getBackground();
        this.lightScrollPane = this.jScrollPane.getBackground();
        this.lightBorder = this.jScrollPane.getBorder();
	}
	
	/*
	void SetDarkMode()
	- Set Component to the Dark mode
	*/
	public void setDarkMode() {
		// Change to DarkMode HTML file
		try {
			this.jEditorPane.setPage(this.darkFile.toURI().toURL());
		} catch (Exception e) {
			e.printStackTrace();
        	JOptionPane.showMessageDialog(null, "Cannot change into dark mode: " + this.darkFile.getName(), "Infobox", JOptionPane.INFORMATION_MESSAGE);
		}
		
		this.jEditorPane.setBackground(ConstantValues.darkBlue);
		this.jScrollPane.setBackground(ConstantValues.darkBlue);
		this.jScrollPane.setBorder(ConstantValues.darkBlueBorder);
		this.jScrollPane.setBorder(BorderFactory.createEmptyBorder());
		
		UIManager.put("ScrollBar.thumb", new ColorUIResource(ConstantValues.darkRed));
		UIManager.put("ScrollBar.track", new ColorUIResource(ConstantValues.darkBlue));
		UIManager.put("ScrollBar.thumbShadow", new ColorUIResource(ConstantValues.darkBlue));
		UIManager.put("ScrollBar.thumbDarkShadow", new ColorUIResource(ConstantValues.darkBlue));
		UIManager.put("ScrollBar.thumbHighlight", new ColorUIResource(ConstantValues.darkBlue));
		this.jScrollPane.getVerticalScrollBar().setUI(new BasicScrollBarUI());
		
		this.setOpaque(true);
		this.setBackground(ConstantValues.darkBlue);
	}
	
	/*
	void SetLightMode()
		- Set Component to the Light mode
	*/
	public void setLightMode() {
		// Change to LightMode HTML file
		try {
			this.jEditorPane.setPage(this.originalFile.toURI().toURL());
		} catch (Exception e) {
			e.printStackTrace();
        	JOptionPane.showMessageDialog(null, "Cannot change into dark mode: " + this.darkFile.getName(), "Infobox", JOptionPane.INFORMATION_MESSAGE);
		}
		
		this.jEditorPane.setBackground(this.lightEditorPane);
		this.jScrollPane.setBackground(this.lightScrollPane);
		this.jScrollPane.setBorder(this.lightBorder);
		
		UIManager.put("ScrollBar.thumb", null);
		UIManager.put("ScrollBar.track", null);
		UIManager.put("ScrollBar.thumbShadow", null);
		UIManager.put("ScrollBar.thumbDarkShadow", null);
		UIManager.put("ScrollBar.thumbHighlight", null);
		this.jScrollPane.getVerticalScrollBar().setUI(new BasicScrollBarUI());

		this.setBackground(this.lightBackground);
	}
}
