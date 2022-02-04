package packXMLparser;

import javax.swing.*;
import javax.swing.text.Document;
import javax.swing.text.html.HTMLEditorKit;
import java.awt.BorderLayout;

public class HTMLpanel {
	private JEditorPane jEditorPane;
	private JScrollPane jScrollPane;
	private HTMLEditorKit kit;
	private Document doc;
	
	String test = "<html>\n"
            + "<body>\n"
            + "<h1>Welcome</h1>\n"
            + "<h2>This is an H2 header</h2>\n"
            + "<p>This is some sample text</p>\n"
            + "<p><a href=\"http://devdaily.com/blog/\">devdaily blog</a></p>\n"
            + "</body>\n";
	
	public HTMLpanel(JFrame frame, String filename) {
		this.jEditorPane = new JEditorPane();
		
		this.jEditorPane.setEditable(false);
		this.jScrollPane = new JScrollPane(this.jEditorPane);
		
		this.kit = new HTMLEditorKit();
        jEditorPane.setEditorKit(kit);
        
        this.doc = kit.createDefaultDocument();
        jEditorPane.setDocument(doc);
        jEditorPane.setText(test);
        
	}
	
	public JScrollPane getScrollPane() {
		return this.jScrollPane;
	}
        
        
        

        
        
        //frame.getContentPane().add(this.jScrollPane, BorderLayout.CENTER);
	
	
	// HTML panel
			/*JEditorPane editorPane = new JEditorPane();
			editorPane.setEditable(false);
			
			this.add(editorPane);
			
			HTMLEditorKit kit = new HTMLEditorKit();
			editorPane.setEditorKit(kit);
			
			JScrollPane scrollPane = new JScrollPane(editorPane);
			
			Document doc = kit.createDefaultDocument();
			editorPane.setDocument(doc);
			editorPane.setText("<strong>This text is important!</strong>");

			
			editorPane.setVisible(true);
			*/
	
	

}
