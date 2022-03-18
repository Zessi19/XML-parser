package packXMLparser;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.text.DateFormatSymbols;
import java.util.Locale;
import java.util.stream.DoubleStream;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

import javax.swing.BorderFactory;
//import javax.swing.JFrame;
//import javax.swing.JPanel;
//import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;

import javax.swing.ImageIcon;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

public class resultJFrame extends JFrame {
	private static final long serialVersionUID = 7103983301595358115L;
	private String[] months;
	private JPanel tab1, tab2, tab3;
	
	final static String BUTTONPANEL = "Card with JButtons";
	final static String TEXTPANEL = "Card with JTextField";
	
	public resultJFrame(String fname, double[] sums, double totalSum, String[][] dataTable) {
		// Set Frame
		this.setTitle(fname);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(ConstantValues.xPixels, ConstantValues.yPixels);
					
		// Set Logo
		ImageIcon logo = new ImageIcon("src/appData/logo_small.png");
		Image scaledLogo = logo.getImage().getScaledInstance(120,120,java.awt.Image.SCALE_SMOOTH);
		ImageIcon newLogo = new ImageIcon(scaledLogo);
		this.setIconImage(newLogo.getImage());
		
		DateFormatSymbols dfs = new DateFormatSymbols(Locale.US);
		this.months = dfs.getShortMonths();
		
		JTabbedPane tabbedPane = new JTabbedPane();
		
		this.tab1 = new JPanel();
		this.tab2 = new JPanel();
		this.tab3 = new JPanel();
		
		tab1.setLayout(new CardLayout());
		tab2.setLayout(new CardLayout());
		tab3.setLayout(new CardLayout());

        setTab1(this.outputHTML(totalSum, sums));
        setTab2(dataTable);
        setTab3(totalSum, sums);
		
		tabbedPane.addTab( "Tab 1", tab1 );
        tabbedPane.addTab( "Tab 2", tab2 );
        tabbedPane.addTab( "Tab 3", tab3 );
        
        this.add(tabbedPane, BorderLayout.CENTER );
	}
	
	public String outputHTML(double total, double[] sums) {
		String output = "";

		DateFormatSymbols dfs = new DateFormatSymbols(Locale.US);
		String[] months = dfs.getShortMonths();
		
		double monthAttr = DoubleStream.of(sums).sum();
		double noAttr = total - monthAttr;
		
		// Total sums
		output += "<h2>Total Balance</h2>";
		output += "<ul>";
		output += "<li><b>Total = </b>" + String.format("%.2f", total) + "</li>";
		output += "<li><b>Total (Month Attr) = </b>" + String.format("%.2f", monthAttr) + "</li>";
		output += "<li><b>Total (No Attr) = </b>" + String.format("%.2f", noAttr) + "</li>";
		output += "</ul>";

		// Month values
		output += "<h2>Total Balance by Month</h2>";
		output += "<ul>";
		for (int i=0; i<months.length-1; i++) {
			output += "<li><b>" + months[i] + "</b> = " + String.format("%.2f",sums[i+1]) + "</li>";
		}
		output += "<li><b>Invalid Month</b> = " + String.format("%.2f", sums[0]) + "</li>";
		output += "</ul>";
		
		return output;
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
	
	private void setTab2(String[][] dataTable) {
		JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createEtchedBorder(), "All Transactions", TitledBorder.CENTER, TitledBorder.TOP));
		      
		String[] header = { "Type", "Amount", "Month" };      
		JTable table = new JTable(dataTable, header);
		
		JScrollPane jScrollPane = new JScrollPane(table);
		jScrollPane.setPreferredSize(new Dimension(ConstantValues.xPixels-100, ConstantValues.yPixels-100));
		      
		panel.add(jScrollPane);
		this.tab2.add(panel);
	}
	
	private void setTab3(double totalSum, double[] sums) {
		DefaultPieDataset dataset = new DefaultPieDataset();
		
		double noMonth = totalSum - DoubleStream.of(sums).sum();
		if (Math.abs(noMonth) > 0.001) {
			String id = "No Month";
			if (noMonth < 0) id += " (Neg)";
			dataset.setValue(id, Math.abs(noMonth));
		}
		
		for (int i=0; i<months.length-1; i++) {
			if (Math.abs(sums[i+1]) > 0.001 ) {
				String id = months[i];
				if (sums[i+1] < 0) id += " (Neg)";
				dataset.setValue(months[i], Math.abs(sums[i+1]));
			}
		} 
		
		if (Math.abs(sums[0]) > 0.001) {
			String id = "Invalid Month";
			if (sums[0] < 0) id += " (Neg)";
			dataset.setValue(id, Math.abs(sums[0]));
		}
		
		JFreeChart chart = ChartFactory.createPieChart(
				"|Credit + Debit| per Month",	// Title 
		         dataset,	// Data    
		         false,		// Legend
		         true, 
		         false);
		
		
		this.tab3.add(new ChartPanel( chart ));
	}

}
