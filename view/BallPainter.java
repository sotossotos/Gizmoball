package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import model.Constants;

public class BallPainter implements IGizmoPainter{

	private Color c;
	private Point center;
	public BallPainter(Point center) {
		this.center = center;
	}
	@Override
	public void paint(Graphics g) {
		g.setColor(c);
		g.fillOval((int)(center.x-Constants.ballRADIUS), (int)(center.y-Constants.ballRADIUS), (int)Constants.ballRADIUS*2, (int)Constants.ballRADIUS*2);
		
	}
	@Override
	public void setColor(Color c) {
		this. c = c;
	}

}
