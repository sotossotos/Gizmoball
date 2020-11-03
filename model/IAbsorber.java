package model;

import java.awt.Color;
import java.awt.Point;
import physics.Vect;

public interface IAbsorber {
	public void setColor(Color color);
	public Color getColor();
	public void setName(String name);
	public String getName();
	public void setTopLeftPoint(Point p);
	public void setBotRightPoint(Point p);
	public Point getTopLeftPoint();
	public Point getBotRightPoint();
	public Vect launchBall();
	public Point absorbBall(double radius);
	public boolean insideMe(Point point, double radius);
    public void setKeyConnect(int key);
    public int getKeyConnect();
	void setWasItemOuside(boolean assignment);
	boolean getWasItemOuside();
}
