package packXMLparser;

import java.io.File;
import java.util.List;
import java.util.ArrayList;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.BorderLayout;

import javax.swing.*;

public class ParsingPanel extends JPanel {
	private int selected;
	
	private JPanel filePanel, commandPanel;
	private JButton runButton, deleteButton;
	private JScrollPane jScrollPane;
	
	private List<File> fileList;
	private List<JButton> fileButtons;
	private List<JRadioButton> radioButtons;
	private ButtonGroup radioGroup;
	
	public ParsingPanel(int xPixels, int yPixels) {
		this.fileList = new ArrayList<File>();
		this.fileButtons = new ArrayList<JButton>();
		this.radioButtons = new ArrayList<JRadioButton>();
		
		this.filePanel = new JPanel();
		this.commandPanel = new JPanel();
		this.radioGroup = new ButtonGroup();
		
		// SetUp JPanel Layouts
		this.setLayout(new BorderLayout());
		this.setPreferredSize(new Dimension(xPixels-20, yPixels-70));
		
		filePanel.setLayout(new BoxLayout(filePanel, BoxLayout.PAGE_AXIS));
		
		commandPanel.setLayout(new FlowLayout());
		commandPanel.setPreferredSize(new Dimension(xPixels-20, yPixels-450));
		
		// Add Command Panel Buttons
		runButton = new JButton("Run Parser");
		runButton.addActionListener( e -> {
			this.runParser();
		});
		
		deleteButton = new JButton("Delete File");
		deleteButton.addActionListener( e -> {
			this.deleteSelected();
		});
		
		commandPanel.add(this.runButton);
		commandPanel.add(this.deleteButton);
		
		// Add JScrollPane to Files
		this.jScrollPane = new JScrollPane(filePanel);		
		this.add(jScrollPane, BorderLayout.CENTER);
		this.add(commandPanel, BorderLayout.PAGE_END);
		
		// Set Panels Visible
		filePanel.setVisible(true);
		commandPanel.setVisible(true);
		this.setVisible(true);
	}
	
	// -------------------------------
	//   ParsingPanel Class Methods
	// -------------------------------
	
	private void updateFilePanel() {
		for (int i=0; i<this.radioButtons.size(); i++) {
			JPanel row = new JPanel();
			row.setLayout(new BoxLayout(row, BoxLayout.LINE_AXIS));
			
			row.add(this.radioButtons.get(i));
			row.add(Box.createRigidArea(new Dimension(25,0)));
			row.add(this.fileButtons.get(i));
			
			this.filePanel.add(row);
		}
	}
	
	public void addFile(File file) {
		this.fileList.add(file);
		
		// Set JRadioButton
		JRadioButton nrb = new JRadioButton();
		nrb.setSelected(true);
		this.radioGroup.add(nrb);
		
		nrb.setName(String.valueOf(radioButtons.size()));
		nrb.addActionListener( e -> {
			this.selected = Integer.parseInt(nrb.getName());
			System.out.println(selected);
		});
		this.radioButtons.add(nrb);
		
		// Set JButton
		JButton nb = new JButton(file.getName());
		nb.setName(String.valueOf(fileButtons.size()));
		nb.addActionListener( e -> {
			this.openXMLfile(Integer.parseInt(nb.getName()));
		});
		this.fileButtons.add(nb);
		
		// Update JPanel
		this.updateFilePanel();
		this.filePanel.revalidate();
		this.filePanel.repaint();
	}
	
	public void removeFiles() {
		this.fileList.clear();
		this.fileButtons.clear();
		this.radioButtons.clear();
		
		this.filePanel.removeAll();
		this.filePanel.revalidate();
		this.filePanel.repaint();
	}
	
	private void openXMLfile(int nElement) {
		File file = this.fileList.get(nElement);
		XMLframe newFrame = new XMLframe(file);
	}
	
	private void runParser() {
		
		System.out.println("Parseroidaan: " + fileList.get(selected).getName());
		
	}
	
	private void deleteSelected() {
		
		System.out.println("Poistetaan: " + fileList.get(selected).getName());
		
	}
	
}