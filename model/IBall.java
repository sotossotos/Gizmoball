package model;

import java.awt.Color;
import java.awt.Point;
import physics.Vect;
import physics.Circle;
public interface IBall {

	public Point getPoint();
	public Vect getVelocity();
	public Circle getCircle();
	public double getRadius();
	public void setVelocity(Vect velocity);
	public void setPoint(Point xy);
	public void stop();
	public void start();
	public boolean isStopped();
	public Color getColor();
	public void reset();
	
}
