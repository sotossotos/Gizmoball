package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class AbsorberPainter implements IGizmoPainter{
	
	private Point topL;
	private Point botR;
	private Color c;
	
	public AbsorberPainter(Point topL, Point botR) {
		this.topL = topL;
		this.botR = botR;
		c = Color.BLACK;
	}

	@Override
	public void paint(Graphics g) {
		int tlx = topL.x;
		int tly = topL.y;
		
		int brx = botR.x;
		int bry = botR.y;
		
		g.setColor(c);
		g.fillRect(tlx, tly, brx  - tlx, bry - tly);
	}

	@Override
	public void setColor(Color c) {
		this.c = c;
	}
	
	
}
