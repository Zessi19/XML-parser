package packXMLparser;

import javax.swing.*;

public class ParsingPanel {
	private JPanel mainJPanel;
	private JLabel test;
	
	public ParsingPanel() {
		this.mainJPanel = new JPanel();
		this.test = new JLabel("No selected files");
		
		
		this.mainJPanel.add(test);
		
		this.show();
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
