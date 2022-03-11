package packXMLparser;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JComponent;
import javax.swing.KeyStroke;
import javax.swing.border.Border;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.AbstractAction;
import javax.swing.Action;

import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Image;
 
public class MainJFrame extends JFrame {
	private static final long serialVersionUID = -157886601035491334L;
	
	// Menu items
	private JMenuBar menubar;
	private JMenu fileMenu, helpMenu, settingsMenu;
	private JMenuItem showItem, addItem, removeItem, guideItem, keybindsItem, lightItem, darkItem;
	private List<JMenuItem> menuItems;
	private List<JMenu> menus; 
	
	// Panels
	public ParsingJPanel parsingPanel;
	public HtmlJPanel guidePanel, keybindsPanel;
	private JFileChooser jFileChooser;
	
	// Actions
	private Action showAction, addAction, removeAction, guideAction, keybindsAction, darkModeAction, lightModeAction;
	
	// Colors and Borders
	private Border menubarBorder, menuBorder, popupMenuBorder, menuItemBorder;
	private Color menubarBack, menuFore, menuBack, menuItemFore, menuItemBack, background;
	
	public MainJFrame() {
		// SetUp Frame
		this.setupFrame();
		  
		// File Chooser
		this.jFileChooser = new JFileChooser(System.getProperty("user.dir"));
		this.jFileChooser.setMultiSelectionEnabled(true);
		
		// Create Menus
		this.menubar = new JMenuBar();
		this.fileMenu = new JMenu("Files");
		this.helpMenu = new JMenu("Help");
		this.settingsMenu = new JMenu("Settings");
		
		this.menus = new ArrayList<JMenu>();
		this.menus.add(fileMenu);
		this.menus.add(helpMenu);
		this.menus.add(settingsMenu);
		
		// Create Menu Items
		this.showItem = new JMenuItem("Show All");
		this.addItem = new JMenuItem("Add New");
		this.removeItem = new JMenuItem("Remove All");
		this.guideItem = new JMenuItem("User Guide");
		this.keybindsItem = new JMenuItem("Keybinds");
		this.darkItem = new JMenuItem("Dark Mode");
		this.lightItem = new JMenuItem("Light Mode");
		
		this.menuItems = new ArrayList<JMenuItem>();
		this.menuItems.add(showItem);
		this.menuItems.add(addItem);
		this.menuItems.add(removeItem);
		this.menuItems.add(guideItem);
		this.menuItems.add(keybindsItem);
		this.menuItems.add(darkItem);
		this.menuItems.add(lightItem);
		
		// Build Menu Bar
		this.fileMenu.add(showItem);
		this.fileMenu.add(addItem);
		this.fileMenu.add(removeItem);
		
		this.helpMenu.add(guideItem); 
		this.helpMenu.add(keybindsItem);
		this.settingsMenu.add(darkItem);
		this.settingsMenu.add(lightItem);
		
		this.menubar.add(fileMenu);
		this.menubar.add(helpMenu);
		this.menubar.add(settingsMenu);
		this.setJMenuBar(menubar);
		
		// JPanels
		this.parsingPanel = new ParsingJPanel();
		this.guidePanel = new HtmlJPanel("guide.html", "guideDark.html");
		this.keybindsPanel = new HtmlJPanel("keybinds.html", "keybindsDark.html");
		
		this.getContentPane().add(this.parsingPanel);
		this.getContentPane().add(this.guidePanel);
		this.getContentPane().add(this.keybindsPanel);
		
		// Setup Actions
		this.showAction = new showAction();
		this.addAction = new addAction();
		this.removeAction = new removeAction();
		this.guideAction = new guideAction();
		this.keybindsAction = new keybindsAction();
		this.darkModeAction = new darkModeAction();
		this.lightModeAction = new lightModeAction();
		
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
		
		this.lightItem.addActionListener(this.lightModeAction);
		this.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke('1'), "press_1");
		this.getRootPane().getActionMap().put("press_1", this.lightModeAction);
		
		this.darkItem.addActionListener(this.darkModeAction); 
		this.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke('2'), "press_2");
		this.getRootPane().getActionMap().put("press_2", this.darkModeAction);
		
		// Save original LightMode parameters
		this.menubarBack = this.menubar.getBackground();
		this.menubarBorder = this.menubar.getBorder();
		this.background = this.getContentPane().getBackground();
		
		for (JMenu e: this.menus) {
			this.menuFore = e.getForeground();
			this.menuBack = e.getBackground();
			this.menuBorder = e.getBorder();
			this.popupMenuBorder = e.getPopupMenu().getBorder();
		}
		
		for (JMenuItem e : this.menuItems) {
			this.menuItemFore = e.getForeground();
			this.menuItemBack = e.getBackground();
			this.menuItemBorder = e.getBorder();
		}
		
	}
	
	// ----------------------------------
	//    Class Methods and SubClasses
	// ---------------------------------- 
	
	public void setupFrame() {
		// Initialize Frame Parameters
		this.setTitle("XML Parser");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(ConstantValues.xPixelsLarge, ConstantValues.yPixelsLarge);
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
	
	public void setDarkMode() {
		this.menubar.setOpaque(true);
		this.menubar.setBackground(ConstantValues.grey);
		this.menubar.setBorder(ConstantValues.greyBorder);
		this.getContentPane().setBackground(ConstantValues.darkBlue);
		
		for (JMenu e: this.menus) {
			e.setOpaque(true);
			e.setForeground(ConstantValues.white);
			e.setBackground(ConstantValues.grey);
			e.setBorder(ConstantValues.greyBorder);
			e.getPopupMenu().setBorder(ConstantValues.greyBorder);
		}
		
		for (JMenuItem e : this.menuItems) {	
			e.setOpaque(true);
			e.setForeground(ConstantValues.white);
			e.setBackground(ConstantValues.grey);
			e.setBorder(ConstantValues.greyBorder);
		}
		
		this.guidePanel.setDarkMode();
		this.keybindsPanel.setDarkMode();
		this.parsingPanel.setDarkMode();
			
	}
	
	public void setLightMode() {
		this.getContentPane().setBackground(this.background);
		this.menubar.setBackground(this.menubarBack);
		this.menubar.setBorder(this.menubarBorder);
		
		for (JMenu e: this.menus) {
			e.setForeground(this.menuFore);
			e.setBackground(this.menuBack);
			e.setBorder(this.menuBorder);
			e.getPopupMenu().setBorder(this.popupMenuBorder);
		}
		
		for (JMenuItem e : this.menuItems) {	
			e.setForeground(this.menuItemFore);
			e.setBackground(this.menuItemBack);
			e.setBorder(this.menuItemBorder);
		}
		
		this.guidePanel.setLightMode();
		this.keybindsPanel.setLightMode();
		this.parsingPanel.setLightMode();
		
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
	
	public class darkModeAction extends AbstractAction {
		private static final long serialVersionUID = -2526670799400411009L;
		@Override
	    public void actionPerformed(ActionEvent e) {
			setDarkMode();
	    }
	}
	
	public class lightModeAction extends AbstractAction {
		private static final long serialVersionUID = -4284866599328090903L;
		@Override
	    public void actionPerformed(ActionEvent e) {
			setLightMode();
	    }
	}
}
