package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.List;

import model.Constants;

public class CirclePainter implements IGizmoPainter{

	private Point[] points;
	private Color c;
	
	public CirclePainter(List<Point> p) {
		points = new Point[1];
		points = p.toArray(new Point[0]);
		c = Color.BLACK;
	}
	
	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(c);
		g.fillOval(points[0].x, points[0].y, Constants.L, Constants.L);
	}

	@Override
	public void setColor(Color c) {
		this. c = c;
	}

}
