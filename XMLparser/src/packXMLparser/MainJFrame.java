package packXMLparser;

import java.io.File;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JComponent;
import javax.swing.KeyStroke;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.AbstractAction;
import javax.swing.Action;

import java.awt.event.ActionEvent;
import java.awt.FlowLayout;
import java.awt.Image;

public class MainJFrame extends JFrame {
	// 4:3 Aspect Ratio Frame: 1.2*[480,640]
	public final int xPixels = 768;
	public final int yPixels = 576;
	private static final long serialVersionUID = -157886601035491334L;
	
	// Menu items
	private JMenuBar menubar;
	private JMenu fileMenu, helpMenu;
	private JMenuItem showItem, addItem, removeItem, guideItem, keybindsItem;
	
	// Panels
	public ParsingJPanel parsingPanel;
	public HtmlJPanel guidePanel, keybindsPanel;
	private JFileChooser jFileChooser;
	
	// Actions
	private Action showAction, addAction, removeAction, guideAction, keybindsAction;
	
	public MainJFrame() {
		// SetUp Frame
		this.setupFrame();
		  
		// File Chooser
		this.jFileChooser = new JFileChooser(System.getProperty("user.dir"));
		this.jFileChooser.setMultiSelectionEnabled(true);
		
		// Create Menu Items
		this.menubar = new JMenuBar();
		this.fileMenu = new JMenu("Files");
		this.helpMenu = new JMenu("Help");
		
		this.showItem = new JMenuItem("Show All");
		this.addItem = new JMenuItem("Add New");
		this.removeItem = new JMenuItem("Remove All");
		this.guideItem = new JMenuItem("User Guide");
		this.keybindsItem = new JMenuItem("Keybinds");
		
		// Build Menu Bar
		this.fileMenu.add(showItem);
		this.fileMenu.add(addItem);
		this.fileMenu.add(removeItem);
		
		this.helpMenu.add(guideItem); 
		this.helpMenu.add(keybindsItem);
		
		this.menubar.add(fileMenu);
		this.menubar.add(helpMenu);
		this.setJMenuBar(menubar);
		
		// JPanels
		this.parsingPanel = new ParsingJPanel(this.xPixels, this.yPixels);
		this.guidePanel = new HtmlJPanel("guide.html", this.xPixels, this.yPixels);
		this.keybindsPanel = new HtmlJPanel("keybinds.html", this.xPixels, this.yPixels);
		
		this.getContentPane().add(this.parsingPanel);
		this.getContentPane().add(this.guidePanel);
		this.getContentPane().add(this.keybindsPanel);
		
		// Setup Actions
		this.showAction = new showAction();
		this.addAction = new addAction();
		this.removeAction = new removeAction();
		this.guideAction = new guideAction();
		this.keybindsAction = new keybindsAction();
		
		// Add ActionListeners and Key Mappings
		this.showItem.addActionListener(this.showAction);
		this.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke('s'), "press_s");
		this.getRootPane().getActionMap().put("press_s", this.showAction);
		
		this.addItem.addActionListener(this.addAction);
		this.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke('a'), "press_a");
		this.getRootPane().getActionMap().put("press_a", this.addAction);
		
		this.removeItem.addActionListener(this.removeAction);
		this.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("BACK_SPACE"), "press_bs");
		this.getRootPane().getActionMap().put("press_bs", this.removeAction);
		
		this.guideItem.addActionListener(this.guideAction);
		this.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke('g'), "press_g");
		this.getRootPane().getActionMap().put("press_g", this.guideAction);
		
		this.keybindsItem.addActionListener(this.keybindsAction);
		this.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke('k'), "press_k");
		this.getRootPane().getActionMap().put("press_k", this.keybindsAction);
		
		// Finish Frame
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	// ----------------------------------
	//    Class Methods and SubClasses
	// ---------------------------------- 
	
	public void setupFrame() {
		// Initialize Frame Parameters
		this.setTitle("XML Parser");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(this.xPixels, this.yPixels);
		this.setLayout(new FlowLayout());
				
		// Set Logo
		ImageIcon logo = new ImageIcon("src/appData/logo_small.png");
		Image scaledLogo = logo.getImage().getScaledInstance(120,120,java.awt.Image.SCALE_SMOOTH);
		ImageIcon newLogo = new ImageIcon(scaledLogo);
		this.setIconImage(newLogo.getImage());
	}
	
	public void selectFile() {
		int returnValue = this.jFileChooser.showOpenDialog(null);
		
		if (returnValue == JFileChooser.APPROVE_OPTION) {
			File[] files = this.jFileChooser.getSelectedFiles();
			
			for (int i=0; i<files.length; i++) {
				this.parsingPanel.addFile(files[i]);
			}
		}
	}
	
	// Action SubClasses
	public class showAction extends AbstractAction {
		private static final long serialVersionUID = 2514744125519996671L;
		@Override
	    public void actionPerformed(ActionEvent e) {
			parsingPanel.setVisible(true);
			guidePanel.setVisible(false);
			keybindsPanel.setVisible(false);
	    }
	}
	
	public class addAction extends AbstractAction {
		private static final long serialVersionUID = -5471047479374893867L;
		@Override
	    public void actionPerformed(ActionEvent e) {
			selectFile();
	    }
	}
	
	public class removeAction extends AbstractAction {
		private static final long serialVersionUID = 900485786159495571L;
		@Override
	    public void actionPerformed(ActionEvent e) {
			parsingPanel.removeFiles();
	    }
	}
	
	public class guideAction extends AbstractAction {
		private static final long serialVersionUID = 5262577173914800374L;
		@Override
	    public void actionPerformed(ActionEvent e) {
			parsingPanel.setVisible(false);
			guidePanel.setVisible(true);
			keybindsPanel.setVisible(false);
	    }
	}
	
	public class keybindsAction extends AbstractAction {
		private static final long serialVersionUID = -8707077341205677148L;
		@Override
	    public void actionPerformed(ActionEvent e) {
			guidePanel.setVisible(false);			
			parsingPanel.setVisible(false);
			keybindsPanel.setVisible(true);
	    }
	}
}
