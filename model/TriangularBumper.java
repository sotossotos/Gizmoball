package model;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import physics.Angle;
import physics.Circle;
import physics.Geometry;
import physics.LineSegment;
import physics.Vect;

public class TriangularBumper extends Gizmo{
	
	private ArrayList<LineSegment> lines;
	private ArrayList<Circle> corners;
	private int currentAngle;
	public TriangularBumper(Point loc) {
		super(loc);
		super.type = "Triangle";
		super.color = Color.GREEN;
		super.initialColor = Color.GREEN;
		super.name = "T" + loc.x + "," + loc.y;
		currentAngle = 0;
		constructTriangle();
	}
	
	private void constructTriangle() {
		lines = new ArrayList<>();
		corners = new ArrayList<>();
		
		double x = super.location.getX();
		double y = super.location.getY();
		
		lines.add(new LineSegment(x, y, x+Constants.L, y));
		lines.add(new LineSegment(x+Constants.L, y, x, y+Constants.L));
		lines.add(new LineSegment(x, y+Constants.L, x, y));
		
		corners.add(new Circle(x, y, 0));
		corners.add(new Circle(x+Constants.L, y, 0));
		corners.add(new Circle(x, y+Constants.L, 0));
		needRotation();
	}
	
	private void needRotation() {
		Angle rotateAngle = Angle.ZERO;
		if(this.currentAngle==1){
			rotateAngle = Angle.DEG_90;
		}else if(this.currentAngle==2){
			rotateAngle = Angle.DEG_180;
		}else if(this.currentAngle==3){
			rotateAngle =Angle.DEG_270;
		}
		resolveRotation(rotateAngle);
	}
	
	private void resolveRotation(Angle rotateAngle){
		Vect center = new Vect(super.location.getX() + Constants.L/2, super.location.getY() + Constants.L/2);
		ArrayList<Circle> rotatedCorners = new ArrayList<>();
		ArrayList<LineSegment> rotatedLines = new ArrayList<>();
		for(Circle c : corners)
			rotatedCorners.add(Geometry.rotateAround(c, center, rotateAngle));
		
		corners.clear();
		corners.addAll(rotatedCorners);
		for(LineSegment l : lines)
			rotatedLines.add(Geometry.rotateAround(l, center, rotateAngle));	
		
		lines.clear();
		lines.addAll(rotatedLines);
	
	}
	
	@Override
	public void rotate() {
		double x = super.location.getX();
		double y = super.location.getY();
		System.out.println(x + "     " + y);
		this.currentAngle=(this.currentAngle+1)%4;
		resolveRotation(Angle.DEG_90);
	}

	@Override
	public ArrayList<LineSegment> getLines() {
		return lines;
	}

	@Override
	public ArrayList<Circle> getCorners() {
		return corners;
	}
	@Override
	public void move(Point newLocation){
		super.location=newLocation;
		this.constructTriangle();
	}
	@Override
    public Point getEndLocation() {
        return location;
    }

	@Override
	public List<Point> getAllPoints() {
		ArrayList<Point> points = new ArrayList<>();
		for(Circle c : corners) {
			 Point p = new Point();
			 p.setLocation(c.getCenter().x(), c.getCenter().y());
			 points.add(p);
		}

		return points;
	}

	@Override
	public int rotations() {
		return currentAngle;
	}

	
	

}
