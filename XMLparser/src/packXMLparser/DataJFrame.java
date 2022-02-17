package packXMLparser;

import java.io.File;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JEditorPane;
import javax.swing.JScrollPane;
import javax.swing.JOptionPane;

public class DataJFrame extends JFrame {
	// Aspect Ratio 4:3 Frame: [480,640]
	private final int xPixels = 640;
	private final int yPixels = 480;
	private static final long serialVersionUID = 7526472295622776147L;
	
	private JEditorPane jEditorPane;
	private JScrollPane jScrollPane;
	
	// Constructor 1. (Open XML file)
	public DataJFrame(File file) {
		
		// Setup Frame parameters
		this.setupFrame(file.getName());
		
		// Read XML-file
		this.jEditorPane = new JEditorPane();
		this.jEditorPane.setEditable(false);
		
		try {
			this.jEditorPane.setPage(file.toURI().toURL());
			
        } catch (Exception e) {
        	e.printStackTrace();
        	JOptionPane.showMessageDialog(null, "Cannot open a file: " + file.getName(), "Infobox", JOptionPane.INFORMATION_MESSAGE);
        }
		
		this.finishFrame();
	}
	
	// Constructor 2. (Show HTML String)
	public DataJFrame(String fname, String html) {
		this.setupFrame(fname);
		
		// Set Input Text		
	    this.jEditorPane = new JEditorPane();
		this.jEditorPane.setEditable(false);
		
		this.jEditorPane.setContentType("text/html");
		this.jEditorPane.setText(html);
		
		this.finishFrame();
	}
	
	// Frame setup
	private void setupFrame(String filename) {
		this.setTitle(filename);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(this.xPixels, this.yPixels);
				
		// Set Logo
		ImageIcon logo = new ImageIcon("src/appImages/logo_small.png");
		Image scaledLogo = logo.getImage().getScaledInstance(120,120,java.awt.Image.SCALE_SMOOTH);
		ImageIcon newLogo = new ImageIcon(scaledLogo);
		this.setIconImage(newLogo.getImage());
	}
	
	private void finishFrame() {
		// Add Scroll bar 
		this.jScrollPane = new JScrollPane(jEditorPane);
		this.add(this.jScrollPane);
		
		// Set location and visibility
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
}
