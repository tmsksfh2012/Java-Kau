package Paintbrush;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class Paintbrush extends JFrame {
	JPanel p1;
	JPanel p2;
	DrawPanel paintPanel;
	RadioButtonFrame radioButtonFrame;
	ListFrame listFrame;
	JFrame application;
	
	public Paintbrush() {
		
		application = new JFrame("±×¸²ÆÇ");
		application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		paintPanel = new DrawPanel();
		application.getContentPane().setBackground(Color.WHITE);
		
		listFrame = new ListFrame();
		p1 = listFrame.getPanel();
		p1.setBorder(new LineBorder(Color.BLACK));
		
		radioButtonFrame = new RadioButtonFrame();
		p2 = radioButtonFrame.getPanel();
		p2.setBorder(new LineBorder(Color.BLACK));
		
		application.getContentPane().add(paintPanel,BorderLayout.CENTER);
		application.getContentPane().add(p1, BorderLayout.NORTH);
		application.getContentPane().add(p2, BorderLayout.SOUTH);
		
		application.pack();
		application.setSize(800, 600);
		application.setVisible(true);	
		
		paintPanel.addMouseMotionListener(
			new MouseMotionAdapter()
			{
				public void mouseDragged(MouseEvent event)
				{
					int xx = event.getX();
					int yy = event.getY();
					paintPanel.x = xx;
					paintPanel.y = yy;
					
					paintPanel.repaint();
				}
			}
		);
		
		listFrame.colorJList.addListSelectionListener(
			new ListSelectionListener()
			{
				@Override
				public void valueChanged(ListSelectionEvent event)
				{
					paintPanel.color = listFrame.colors[listFrame.colorJList.getSelectedIndex()];
				}			
			}
		);
		
		radioButtonFrame.button3.addActionListener(new MyListener());
		radioButtonFrame.button5.addActionListener(new MyListener());
		radioButtonFrame.button8.addActionListener(new MyListener());
		radioButtonFrame.button12.addActionListener(new MyListener());
		radioButtonFrame.button16.addActionListener(new MyListener());
		radioButtonFrame.button20.addActionListener(new MyListener());
	}
	
	class MyListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == radioButtonFrame.button3) paintPanel.pixel = 3;
			else if(e.getSource() == radioButtonFrame.button5) paintPanel.pixel = 5;		
			else if(e.getSource() == radioButtonFrame.button8) paintPanel.pixel = 8;		
			else if(e.getSource() == radioButtonFrame.button12) paintPanel.pixel = 12;		
			else if(e.getSource() == radioButtonFrame.button16) paintPanel.pixel = 16;		
			else if(e.getSource() == radioButtonFrame.button20) paintPanel.pixel = 20;		
		}
		
	}
	
	public static void main(String[] args) {
		new Paintbrush();
	}
}
