package packXMLparser;

import java.io.File;
import java.util.List;
import java.util.ArrayList;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;

import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.border.Border;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.BoxLayout;

/*
## Class extends JPanel which handles GUI parsing functionality of application ##
	- 

	- Private methods
		* void updateFilePanel()
		* void openXMLfile()
		* void runParser()
		* void deleteSelected()
	- Public methods
		* void addFile()
		* void removeFiles()
		* void SetDarkMode()
		* void SetLightMode()
*/

public class ParsingJPanel extends JPanel {
	// ----------------------
	//     Class Variables
	// ----------------------
	
	// Eclipse recommendation
	private static final long serialVersionUID = 358722791501541073L;
	
	// Saves current selected radio button iterator in Lists 
	private int selected;
	
	// Dark/Light mode Colors and Borders, darkMode flag variable
	private boolean darkMode;
	private Color jPanelBack, buttonBack, buttonFore;
	private Border jScrollBaneBorder, buttonBorder;
	
	// Java Swing Components
	public JLabel note;
	public JPanel filePanel, commandPanel;
	public JButton runButton, deleteButton;
	public JScrollPane jScrollPane;
	public ButtonGroup radioGroup;
	
	// Memo Lists to later access Swing/Other components
	private List<File> fileList;
	private List<XmlDom> domList;
	private List<JButton> fileButtons;
	private List<JRadioButton> radioButtons;
	
	
	// -------------------
	//     Constructor
	// -------------------
	public ParsingJPanel() {		
		this.fileList = new ArrayList<File>();
		this.domList = new ArrayList<XmlDom>();
		this.fileButtons = new ArrayList<JButton>();
		this.radioButtons = new ArrayList<JRadioButton>();
		
		this.filePanel = new JPanel();
		this.commandPanel = new JPanel();
		this.radioGroup = new ButtonGroup();
		
		// SetUp JPanel Layouts
		this.setLayout(new BorderLayout());
		this.setPreferredSize(new Dimension(ConstantValues.xPixelsLarge-20, ConstantValues.yPixelsLarge-70));
		
		this.filePanel.setLayout(new BoxLayout(filePanel, BoxLayout.PAGE_AXIS));
		this.updateFilePanel();
		
		commandPanel.setLayout(new FlowLayout());
		commandPanel.setPreferredSize(new Dimension(ConstantValues.xPixelsLarge-20, ConstantValues.yPixelsLarge-450));
		
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
		
		// Save Original Colors and Borders
		this.jPanelBack = this.getBackground();
		this.jScrollBaneBorder = this.jScrollPane.getBorder();
		
		this.buttonBack = this.runButton.getBackground();
		this.buttonFore = this.runButton.getForeground();
		this.buttonBorder = this.runButton.getBorder();
		
		// Set Panels Visible
		filePanel.setVisible(true);
		commandPanel.setVisible(true);
		this.setVisible(true);
	}
	
	// ---------------------
	//    Private Methods
	// ---------------------
	
	/*
	void UpdateFilePanel()
		- Redraw all "rows" of (radioButton, fileButton) pairs to this.filePanel
	 */
	private void updateFilePanel() {
		if (this.radioButtons.size() > 0) {
			for (int i=0; i<this.radioButtons.size(); i++) {
				JPanel row = new JPanel();
				row.setLayout(new BoxLayout(row, BoxLayout.LINE_AXIS));
				
				if (darkMode) {
					row.setBackground(ConstantValues.darkBlue);
				}
			
				row.add(this.radioButtons.get(i));
				row.add(Box.createRigidArea(new Dimension(25,0)));
				row.add(this.fileButtons.get(i));
			
				this.filePanel.add(row);
			}
		} else {
			this.note = new JLabel("No Files Selected", JLabel.CENTER);
		    this.note.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		    this.filePanel.add(note);
		}
	}
	
	/*
	void OpenXMLfile()
		- Open iElement-th XML-file in a new JFrame (class FileJFrame)
	 */
	private void openXMLfile(int iElement) {
		File file = this.fileList.get(iElement);
		
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() { 
				FileJFrame newFrame = new FileJFrame(file, darkMode);
				newFrame.setLocationRelativeTo(null);
				newFrame.setVisible(true);
			}
		});
	}
	
	/*
	void RunParser()
		- RunParser for selected radio button XML-file (this.selected)
		- Forms XMLDom object form the targeted XML-file and calls object's
		  methods to get parsing results
		- Results shown in a new JFrame (class resultsJFrame)
	 */
	private void runParser() {
		XmlDom targetDom = this.domList.get(this.selected);
		
		String fname = "Results: " + targetDom.getFilename();
		double[] sums = targetDom.getBalanceByMonth();
		double totalSum = targetDom.getBalance();
		String[][] dataTable = targetDom.getDataTable();
		
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() { 
				resultJFrame newFrame = new resultJFrame(fname, sums, totalSum, dataTable);
				newFrame.setLocationRelativeTo(null);
				newFrame.setVisible(true); 
			}
		});
	}
	
	/*
	void DeleteSelected()
		- Delete selected (radio button) XML-file form user view and memo Lists
		- All this.filePanel rows are redrawn after deletion
	 */
	private void deleteSelected() {
		this.fileList.remove(this.selected);
		this.domList.remove(this.selected);
		
		this.fileButtons.remove(this.selected);
		this.radioButtons.remove(this.selected);
		
		// Rename button "numbers"
		for (int i=0; i<radioButtons.size(); i++) {
			this.radioButtons.get(i).setName(String.valueOf(i));
			this.fileButtons.get(i).setName(String.valueOf(i));
		}
		
		this.filePanel.removeAll();
		this.updateFilePanel();
		this.filePanel.revalidate();
		this.filePanel.repaint();
	}
	
	// --------------------
	//    Public Methods
	// --------------------
	
	/*
	void  AddFile()
		- Forms XmlDOm from argument File Object
		- Add added XML-file to user view and save Swing components/XmlDom/File to
		  memo Lists
	 */
	public void addFile(File file) {
		this.fileList.add(file);
		this.domList.add(new XmlDom(file));
		
		// Set JRadioButton
		JRadioButton nrb = new JRadioButton();
		nrb.setSelected(true);
		this.radioGroup.add(nrb);
		
		nrb.setName(String.valueOf(radioButtons.size()));
		nrb.addActionListener( e -> {
			this.selected = Integer.parseInt(nrb.getName());
		});
		if (darkMode) {
			nrb.setBackground(ConstantValues.darkBlue);
		}
		this.radioButtons.add(nrb);
		
		// Set JButton
		JButton nb = new JButton(file.getName());
		nb.setName(String.valueOf(fileButtons.size()));
		nb.addActionListener( e -> {
			this.openXMLfile(Integer.parseInt(nb.getName()));
		});
		if (darkMode) {
			nb.setBackground(ConstantValues.grey);
			nb.setForeground(ConstantValues.white);
		}
		this.fileButtons.add(nb);
		
		// Update JPanel
		this.filePanel.remove(this.note);
		this.updateFilePanel();
		this.filePanel.revalidate();
		this.filePanel.repaint();
	}
	
	/*
	void RemoveFiles()
		- Removes every File from user view and memo Lists
	 */
	public void removeFiles() {
		this.fileList.clear();
		this.domList.clear();
		this.fileButtons.clear();
		this.radioButtons.clear();
		
		this.filePanel.removeAll();
		this.updateFilePanel();
		this.filePanel.revalidate();
		this.filePanel.repaint();
	}
	
	/*
	void SetDarkMode()()
		- Set Component to Dark mode
	 */
	public void setDarkMode() {
		this.darkMode = true;
		
		this.setBackground(ConstantValues.darkBlue);
		this.filePanel.setBackground(ConstantValues.darkBlue);
		this.commandPanel.setBackground(ConstantValues.grey);
		this.jScrollPane.setBorder(ConstantValues.darkBlueBorder);
		
		this.runButton.setBackground(ConstantValues.darkBlue);
		this.deleteButton.setBackground(ConstantValues.darkBlue);
		
		this.runButton.setForeground(ConstantValues.white);
		this.deleteButton.setForeground(ConstantValues.white);
		this.note.setForeground(ConstantValues.white);
		
		this.runButton.setBorder(ConstantValues.darkBlueBorderLarge);
		this.deleteButton.setBorder(ConstantValues.darkBlueBorderLarge);
		
		for (JButton e : this.fileButtons) {
			e.setBackground(ConstantValues.grey);
			e.setForeground(ConstantValues.white);
		}
		for (JRadioButton e : this.radioButtons) {
			e.setBackground(ConstantValues.darkBlue);
		}
		for (Component e : this.filePanel.getComponents()) {
			e.setBackground(ConstantValues.darkBlue);
		}		
	}
	
	/*
	void SetLightMode()()
		- Set Component to Light mode
	 */
	public void setLightMode() {
		this.darkMode = false;
		
		this.setBackground(this.jPanelBack);
		this.filePanel.setBackground(this.jPanelBack);
		this.commandPanel.setBackground(this.jPanelBack);
		
		this.runButton.setBackground(this.buttonBack);
		this.runButton.setForeground(this.buttonFore);
		this.runButton.setBorder(this.buttonBorder);
		
		this.deleteButton.setBackground(this.buttonBack);
		this.deleteButton.setForeground(this.buttonFore);
		this.deleteButton.setBorder(this.buttonBorder);
		
		this.note.setForeground(this.buttonFore);
		this.jScrollPane.setBorder(this.jScrollBaneBorder);
		
		for (JButton e : this.fileButtons) {
			e.setBackground(this.buttonBack);
			e.setForeground(this.buttonFore);
		}
		for (JRadioButton e : this.radioButtons) {
			e.setBackground(this.buttonBack);
		}
		for (Component e : this.filePanel.getComponents()) {
			e.setBackground(this.jPanelBack);
		}	
	}
	
}