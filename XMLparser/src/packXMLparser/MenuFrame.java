package packXMLparser;

import javax.swing.*;
import java.awt.event.*;
import java.awt.Image;

public class MenuFrame extends JFrame implements ActionListener {
	JMenuBar menubar;
	JMenu fileMenu;
	JMenu helpMenu;
	
	MenuFrame() {	
		this.setTitle("XML Parser");
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		this.setSize(720,520);
		
		// Profium logo ImageIcon
		ImageIcon logo = new ImageIcon("src/images/logo_cropped.png");
		Image scaledLogo = logo.getImage().getScaledInstance(120,120,java.awt.Image.SCALE_SMOOTH);
		ImageIcon newLogo = new ImageIcon(scaledLogo);
		this.setIconImage(newLogo.getImage());
		
		// Menu Bar
		menubar = new JMenuBar();
		fileMenu = new JMenu("Add File");
		helpMenu = new JMenu("Help");
		
		fileMenu.addActionListener(this);
		helpMenu.addActionListener(this);
		
		menubar.add(fileMenu);
		menubar.add(helpMenu);
		this.setJMenuBar(menubar);
		
		
		this.setVisible(true);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == fileMenu) {
			System.out.println("kissa");
		}
	}
	
	
}
