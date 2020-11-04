package Paintbrush;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class RadioButtonFrame extends JFrame {
	private ButtonGroup radioGroup;
	private JPanel panel;
	protected JRadioButton button3 = new JRadioButton("3",true);
	protected JRadioButton button5 = new JRadioButton("5",true);
	protected JRadioButton button8 = new JRadioButton("8",true);
	protected JRadioButton button12 = new JRadioButton("12",true);
	protected JRadioButton button16 = new JRadioButton("16",true);
	protected JRadioButton button20 = new JRadioButton("20",true);
	protected JRadioButton[]PixelButton = {button3, button5, button8, button12, button16, button20};
	
	public RadioButtonFrame() {			panel = new JPanel();
		radioGroup = new ButtonGroup();
		for(JRadioButton i : PixelButton) {
			panel.add(i);
			radioGroup.add(i);
		}
	}
	public JPanel getPanel() {
		return panel;
	}
}
