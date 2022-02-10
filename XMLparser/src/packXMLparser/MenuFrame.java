package packXMLparser;

import javax.swing.*;

import java.awt.event.*;
import java.io.File;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;

public class MenuFrame extends JFrame implements ActionListener {
	public final int xPixels = 720, yPixels = 520;
	
	// Menu items
	JMenuBar menubar;
	JMenu fileMenu, helpMenu;
	JMenuItem showItem, addItem, removeItem, guideItem, keybindItem;
	
	// Panels
	ParsingPanel parsingPanel;
	HTMLpanel guidePanel, keybindsPanel;
	
	JFileChooser jFileChooser;
	
	
	public MenuFrame() {
		// Initialize Frame
		this.setTitle("XML Parser");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(this.xPixels, this.yPixels);
		this.setLayout(new FlowLayout());
		
		// Profium logo to Frame
		ImageIcon logo = new ImageIcon("src/appImages/logo_small.png");
		Image scaledLogo = logo.getImage().getScaledInstance(120,120,java.awt.Image.SCALE_SMOOTH);
		ImageIcon newLogo = new ImageIcon(scaledLogo);
		this.setIconImage(newLogo.getImage());
		
		// File Chooser
		this.jFileChooser = new JFileChooser(System.getProperty("user.dir"));
		
		// Menu Bars
		this.menubar = new JMenuBar();
		this.fileMenu = new JMenu("Files");
		this.helpMenu = new JMenu("Help");
		
		this.showItem = new JMenuItem("Show All");
		this.addItem = new JMenuItem("Add New");
		this.removeItem = new JMenuItem("Remove All");
		this.guideItem = new JMenuItem("User Guide");
		this.keybindItem = new JMenuItem("Keybinds");
		
		fileMenu.add(showItem); fileMenu.add(addItem); fileMenu.add(removeItem);
		helpMenu.add(guideItem); helpMenu.add(keybindItem);
		menubar.add(fileMenu); menubar.add(helpMenu);
		this.setJMenuBar(menubar);
		
		// JPanels
		this.parsingPanel = new ParsingPanel();
		
		this.guidePanel = new HTMLpanel("guide.html", this.xPixels, this.yPixels);
		this.keybindsPanel = new HTMLpanel("keybinds.html", this.xPixels, this.yPixels);
		
		//this.add(this.parsingPanel.getJPanel(), BorderLayout.CENTER);
		this.add(this.parsingPanel);
		
		this.add(this.guidePanel.getJPanel());
		this.add(this.keybindsPanel.getJPanel()); // BorderLayout.CENTER
		
		// Listeners
		showItem.addActionListener(this);
		addItem.addActionListener(this);
		removeItem.addActionListener(this);
		guideItem.addActionListener(this);
		keybindItem.addActionListener(this);
		
		this.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == showItem) {
			this.parsingPanel.setVisible(true);
			this.guidePanel.hide();
			this.keybindsPanel.hide();
		
		} else if (e.getSource() == addItem) {
			selectFile();
		
		} else if (e.getSource() == removeItem) {
			this.parsingPanel.removeFiles();
			
		} else if (e.getSource() == guideItem) {
			this.guidePanel.show();
			this.keybindsPanel.hide();
			this.parsingPanel.setVisible(false);
			
		} else if (e.getSource() == keybindItem) {
			this.keybindsPanel.show();
			this.guidePanel.hide();			
			this.parsingPanel.setVisible(false);
		}
	}
	
	public void selectFile() {
		int returnValue = this.jFileChooser.showOpenDialog(null);
		
		if (returnValue == JFileChooser.APPROVE_OPTION) {
			File file = this.jFileChooser.getSelectedFile();
			this.parsingPanel.addFile(file);
		}
	}
	
}
