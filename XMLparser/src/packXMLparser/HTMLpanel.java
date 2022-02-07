package packXMLparser;

import java.io.File;
import javax.swing.JPanel;
import javax.swing.JEditorPane;
import javax.swing.JScrollPane;
import javax.swing.JOptionPane;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.LayoutManager;

public class HTMLpanel {
	private JPanel mainJPanel;
	private JEditorPane jEditorPane;
	private JScrollPane jScrollPane;
	private LayoutManager layout;
	
	public HTMLpanel(String filename, int xPixels, int yPixels) {
		// Set-up Main Panel
		this.mainJPanel = new JPanel();
	    this.layout = new FlowLayout();  
	    this.mainJPanel.setLayout(layout);
	    
	    this.jEditorPane = new JEditorPane();
		this.jEditorPane.setEditable(false);
		
		// Read HTML-file
        try {
        	File htmlFile = new File("src/appData/" + filename);
        	this.jEditorPane.setPage(htmlFile.toURI().toURL());
        	
        } catch (Exception e) {
        	e.printStackTrace();
        	JOptionPane.showMessageDialog(null, "Cannot open a file: " + filename, "Infobox", JOptionPane.INFORMATION_MESSAGE);
        }

        // Add attachments to Main Panel
        this.jScrollPane = new JScrollPane(jEditorPane);
        this.jScrollPane.setPreferredSize(new Dimension(xPixels-50, yPixels-75));
        
        this.mainJPanel.add(jScrollPane);
        this.hide();
	}
	
	public JPanel getJPanel() {
		return this.mainJPanel;
	}
	
	public void show() {
		this.mainJPanel.setVisible(true);
	}
	
	public void hide() {
		this.mainJPanel.setVisible(false);
	}
	
}
