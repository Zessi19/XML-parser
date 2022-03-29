package packXMLparser;

import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.border.Border;

/*
## Class includes constant values used in application ##
	- xPixelLArge, yPixelsLarge
		* The size of MainJFrame (pixels)
	- xPixels, yPixels
		* The size of parsing result JFrame (resultJFrame)
	- Custom colors are used in Dark Mode
*/

public final class ConstantValues {
	// 4:3 Aspect Ratio Frame: 1.2*[480,640] (MainFrame)
	public static final int xPixelsLarge = 768;
	public static final int yPixelsLarge = 576;
	
	// 4:3 Aspect Ratio Frame: [480,640] (DataFrame)
	public static final int xPixels = 640;
	public static final int yPixels = 480;
	
	// JComponent Colors
	public static final Color white = new Color(235,237,239);
	public static final Color grey = new Color(86,101,115);
	public static final Color lightBlue = new Color(159, 200, 240);
	public static final Color darkBlue = new Color(44,62,80);
	public static final Color darkRed = new Color(113,55,47);

	// JComponent Borders
	public static final Border darkBlueBorder = BorderFactory.createLineBorder(darkBlue, 2);
	public static final Border darkBlueBorderLarge = BorderFactory.createLineBorder(darkBlue, 7);
	public static final Border greyBorder = BorderFactory.createLineBorder(grey, 2);
}
