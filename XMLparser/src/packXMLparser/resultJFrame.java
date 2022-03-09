package packXMLparser;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

public class resultJFrame extends JFrame {
	private static final long serialVersionUID = 7103983301595358115L;
	private JPanel tab1, tab2, tab3;
	
	final static String BUTTONPANEL = "Card with JButtons";
	final static String TEXTPANEL = "Card with JTextField";
	
	public resultJFrame(String fname, String html) {
		// Set Frame
		this.setTitle(fname);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(ConstantValues.xPixels, ConstantValues.yPixels);
					
		// Set Logo
		ImageIcon logo = new ImageIcon("src/appData/logo_small.png");
		Image scaledLogo = logo.getImage().getScaledInstance(120,120,java.awt.Image.SCALE_SMOOTH);
		ImageIcon newLogo = new ImageIcon(scaledLogo);
		this.setIconImage(newLogo.getImage());
		
		JTabbedPane tabbedPane = new JTabbedPane();
		
		this.tab1 = new JPanel();
		this.tab2 = new JPanel();
		this.tab3 = new JPanel();
		
		tab1.setLayout(new CardLayout());
		tab2.setLayout(new CardLayout());
		tab3.setLayout(new CardLayout());
		
		tab2.add(new JLabel("kissa"));
		tab3.add(new JLabel("koira"));

        setTab1(html);
        setTab2();
		
		tabbedPane.addTab( "Tab 1", tab1 );
        tabbedPane.addTab( "Tab 2", tab2 );
        tabbedPane.addTab( "Tab 3", tab3 );
        
        this.add(tabbedPane, BorderLayout.CENTER );
	}
	
	private void setTab1(String html) {
		// Add JEditorPane
		JEditorPane jEditorPane = new JEditorPane();
		jEditorPane.setEditable(false);	
		jEditorPane.setContentType("text/html");
		jEditorPane.setText(html);
			
		// Add JScrollPane 
		JScrollPane jScrollPane = new JScrollPane(jEditorPane);
		this.tab1.add(jScrollPane);
	}
	
	private void setTab2() {
		
		
		
		
		//System.out.println("kissa");
	}
}
