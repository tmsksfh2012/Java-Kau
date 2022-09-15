package Paintbrush;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JPanel;

public class DrawPanel extends JPanel {
	protected int x = 0;
	protected int y = 0;
	protected int pixel = 0;
	protected Color color = Color.BLACK;
	
	@Override
	public void paint(Graphics g) {
		g.setColor(color);
		g.fillOval(x, y, pixel, pixel);
	}
}
