package Paintbrush;

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class ListFrame extends JFrame{
	protected JList colorJList;
	private static final String[] colorNames = {"Black","Red","Green","Blue",
			"Yellow", "White"};
	protected static final Color[] colors = {Color.BLACK,Color.RED,Color.GREEN,Color.BLUE,
			Color.YELLOW, Color.WHITE};
	private JPanel panel;
	private Color color = Color.BLACK;
	private JScrollPane Scroll;
	
	public ListFrame() {
		super("PaintBrush");
		panel = new JPanel();
		
		colorJList = new JList(colorNames);
		colorJList.setVisibleRowCount(1);
		
		colorJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		Scroll = new JScrollPane(colorJList);
		
		panel.add(Scroll);
		
		colorJList.addListSelectionListener(
			new ListSelectionListener()
			{
				@Override
				public void valueChanged(ListSelectionEvent event)
				{
					color = colors[ colorJList.getSelectedIndex() ];
				}			
			}
		);
	}
	public JPanel getPanel() {
		return panel;
	}
	public Color getColor() {
		return color;
	}
	public JScrollPane getScroll() {
		return Scroll;
	}
}
