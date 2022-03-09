package packXMLparser;

public class Main {
	
	private static void createAndShowUI() {
		System.out.println("Opening App");
		MainJFrame frame = new MainJFrame();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	   }

	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowUI();
			}
		});
	}
}