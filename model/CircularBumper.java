package model;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import physics.Circle;
import physics.LineSegment;

public class CircularBumper extends Gizmo{
	
	private Circle circle;

	public CircularBumper(Point loc) {
		super(loc);
		super.type = "Circle";
		super.name = "C" + loc.x + "," + loc.y;
		circle = new Circle(new Point(loc.x+Constants.L/2,loc.y+Constants.L/2), Constants.RADIUS); 
		super.color = Color.BLUE;
		super.initialColor = Color.BLUE;
	}
	@Override
	public void move(Point newLocation) {
		this.location = newLocation;
		this.circle = new Circle(new Point(newLocation.x+Constants.L/2,newLocation.y+Constants.L/2), Constants.RADIUS);
	}
	@Override
	public void rotate() {}

	@Override
	public ArrayList<LineSegment> getLines() {
		return new ArrayList<LineSegment>();
	} 

	@Override
	public ArrayList<Circle> getCorners() {
		ArrayList<Circle> corners = new ArrayList<>();
		corners.add(this.circle);
		return corners;
	}
	
	@Override
    public Point getEndLocation() {
        return location;
    }

	@Override
	public List<Point> getAllPoints() {
		ArrayList<Point> points = new ArrayList<Point>();
		points.add(location);
		return points;
	}

    @Override
    public int rotations() {
        return 0;
    }
	

}
