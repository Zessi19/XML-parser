package packXMLparser;

import java.awt.GridLayout;
import java.io.File;
import java.util.List;
import java.util.ArrayList;

import javax.swing.*;

public class ParsingPanel extends JPanel {
	private List<File> fileList;
	
	private List<JButton> fileButtons;
	private List<JRadioButton> radioButtons;
	private ButtonGroup radioGroup;
	
	public ParsingPanel() {
		this.setLayout(new GridLayout(0,2));
		
		this.fileList = new ArrayList<File>();
		
		this.fileButtons = new ArrayList<JButton>();
		this.radioButtons = new ArrayList<JRadioButton>();		
		this.radioGroup = new ButtonGroup();
		
		//this.updateButtons();
		this.setVisible(true);
	}
	
	// JButton indexing starts from zero!	
	public void addFile(File file) {
		this.fileList.add(file);
		
		this.radioButtons.add(new JRadioButton());
		
		JButton nb = new JButton(file.getName());
		nb.setName(String.valueOf(fileButtons.size()));
		nb.addActionListener( e -> {
			this.openXMLfile(Integer.parseInt(nb.getName()));
		});
		this.fileButtons.add(nb);
		
		this.updateButtons();
		this.revalidate();
		this.repaint();
		
		//System.out.println("Added: " + file.getName());
		//System.out.println("Path: " + file.getPath());
		
	}
	
	public void removeFiles() {
		this.fileList.clear();
		this.fileButtons.clear();
		this.radioButtons.clear();
		
		this.removeAll();
		this.revalidate();
		this.repaint();
	}
	
	private void updateButtons() {
		for (int i=0; i<radioButtons.size(); i++) {
			JRadioButton rb = radioButtons.get(i);
			rb.setSelected(true);
			
			this.radioGroup.add(rb);
			this.add(rb);
			this.add(fileButtons.get(i));
		}
	}
	
	private void openXMLfile(int nElement) {
		File file = this.fileList.get(nElement);
		XMLframe newFrame = new XMLframe(file);
	}

}




//Test Case (Radio)
//JRadioButton r1 = new JRadioButton();
//r1.setSelected(true);
//radioButtons.add(r1);
			
// Test Case (File)
//JButton nb = new JButton("test1.xml");
//nb.setName("1");
//nb.addActionListener(e -> System.out.println(nb.getName()));
//fileButtons.add(nb);
