package model;

import java.awt.Color;
import java.awt.Point;

import physics.Circle;
import physics.Vect;

public class Ball implements IBall{

	private Vect velocity;
	private double radius;
	private Point startingCenter;
	private Vect startingVelocity;
	private Point center;
	private Color colour;

	private boolean stopped;

	// x, y coordinates and x,y velocity
	public Ball(Point center,double radius) {
		this.startingCenter = center;
		this.startingVelocity = new Vect(new Point(0,0));
		this.center = center;
		colour = Color.BLUE;
		this.velocity = new Vect(new Point(0,0));
		this.radius = radius;
		stopped = false;
	}
	public void reset() {
		this.center.setLocation(this.startingCenter.getX(),this.startingCenter.getY());
		this.velocity = startingVelocity;
		this.start();
	}
	public Point getPoint() {
		return center;
	}
	public Vect getVelocity() {
		return velocity;
	}
	public void setPoint(Point xy) {
		center = xy;
	}
	public void setVelocity(Vect v) {
		velocity = v;
	}

	public double getRadius() {
		return radius;
	}

	public Circle getCircle() {
		return new Circle(center.getX(), center.getY(), radius);

	}
	public Color getColor(){
		return colour;
	}

	// Ball specific methods that deal with double precision.
	public double getExactX() {
		return center.getX();
	}

	public double getExactY() {
		return center.getY();
	}


	public void stop() {
		stopped = true;
	}

	public void start() {
		stopped = false;
	}

	public boolean isStopped() {
		return stopped;
	}

	public Color getColour() {
		return colour;
	}

}
