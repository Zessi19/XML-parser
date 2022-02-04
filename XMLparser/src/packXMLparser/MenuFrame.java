package packXMLparser;

import javax.swing.*;
import javax.swing.text.Document;
import javax.swing.text.html.HTMLEditorKit;

import java.awt.event.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;

public class MenuFrame extends JFrame implements ActionListener {
	public final int xPixels = 720, yPixels = 520;
	
	JMenuBar menubar;
	JMenu fileMenu, helpMenu;
	JMenuItem showItem, addItem, removeItem, guideItem, keybindItem;
	
	JPanel red;
	HTMLpanel guidePanel;
	
	
	public MenuFrame() {
		this.setTitle("XML Parser");
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		this.setSize(this.xPixels, this.yPixels);
		this.setLayout(new FlowLayout());
		
		// Profium logo
		ImageIcon logo = new ImageIcon("src/images/logo_small.png");
		Image scaledLogo = logo.getImage().getScaledInstance(120,120,java.awt.Image.SCALE_SMOOTH);
		ImageIcon newLogo = new ImageIcon(scaledLogo);
		this.setIconImage(newLogo.getImage());
		
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
		this.guidePanel = new HTMLpanel(this, "guide.html");
		
		this.add(this.guidePanel())
		f.getContentPane().add(this.jScrollPane, BorderLayout.CENTER);
		
		//blue = new JPanel();
		//red = new JPanel();
		//green = new JPanel();
		
		//this.add(blue);
		//this.add(red);
		//this.add(green);
		
		//red.setBackground(Color.RED);
		//red.setPreferredSize(new Dimension(this.xPixels, this.yPixels));
		//red.setVisible(true);
		
	 	//blue.setBackground(Color.BLUE);
		//blue.setPreferredSize(new Dimension(this.xPixels, this.yPixels));
		//blue.setVisible(false);
		
		//green.setBackground(Color.GREEN);
		//green.setPreferredSize(new Dimension(this.xPixels, this.yPixels));
		//green.setVisible(false);
		
		// Listeners
		showItem.addActionListener(this);
		addItem.addActionListener(this);
		removeItem.addActionListener(this);
		guideItem.addActionListener(this);
		keybindItem.addActionListener(this);
		
		//red.setVisible(true);
		this.setVisible(true);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == showItem) {
			//red.setVisible(true);
		
		} else if (e.getSource() == addItem) {
			System.out.println("Add File");
		
		} else if (e.getSource() == removeItem) {
			System.out.println("Remove Files");
			
		} else if (e.getSource() == guideItem) {
			//red.setVisible(false);
			
		} else if (e.getSource() == keybindItem) {
			//red.setVisible(false);
			
		}
	}
	
}
