package cs.assignments.graphs;

import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * A text area to show the MST in text from similar to the book's 
 * way
 * 
 * @author Robert Wilk
 *
 */
public class TextPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private static JScrollPane scroll;
	private static JTextArea text;
	
	public TextPanel(String mstText, Dimension dimension) {
		text = new JTextArea(mstText);
		text.setPreferredSize(null);
		scroll = new JScrollPane(text);
		Dimension pref = new Dimension(dimension.width - 35, dimension.height - 140);
		scroll.setPreferredSize(pref);
		add(scroll);
	}
}
