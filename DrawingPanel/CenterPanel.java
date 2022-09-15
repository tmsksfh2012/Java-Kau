package Paintbrush;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JPanel;

public class CenterPanel extends JPanel {
	protected int x = 0;
	protected int y = 0;
	protected int pixel = 0;
	protected Color color = Color.BLACK;
	
	public CenterPanel() {
		addMouseMotionListener(
			new MouseMotionAdapter()
			{
				public void mouseDragged(MouseEvent event)
				{
					int xx = event.getX();
					int yy = event.getY();
					x = xx;
					y = yy;			
					repaint();
				}
			}
		);
	}
	@Override
	public void paint(Graphics g) {
		g.setColor(color);
		g.fillOval(x, y, pixel, pixel);
	}
}
