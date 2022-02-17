package packXMLparser;

import java.io.File;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JEditorPane;
import javax.swing.JScrollPane;
import javax.swing.JOptionPane;

public class HtmlJPanel extends JPanel {
	private static final long serialVersionUID = 1076966878409007128L;
	private JEditorPane jEditorPane;
	private JScrollPane jScrollPane;
	
	public HtmlJPanel(String fname, int xPixels, int yPixels) {
	    this.jEditorPane = new JEditorPane();
		this.jEditorPane.setEditable(false);
		
		// Read HTML-file
        try {
        	File htmlFile = new File("src/appData/" + fname);
        	this.jEditorPane.setPage(htmlFile.toURI().toURL());
        	
        } catch (Exception e) {
        	e.printStackTrace();
        	JOptionPane.showMessageDialog(null, "Cannot open a file: " + fname, "Infobox", JOptionPane.INFORMATION_MESSAGE);
        }

        // Add JScrollPane
        this.jScrollPane = new JScrollPane(jEditorPane);
        this.jScrollPane.setPreferredSize(new Dimension(xPixels-45, yPixels-80));
        
        this.add(jScrollPane);
        this.setVisible(false);
	}
}