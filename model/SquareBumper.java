package model;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import physics.Circle;
import physics.LineSegment;

public class SquareBumper extends Gizmo{

	private ArrayList<LineSegment> lines;
	private ArrayList<Circle> corners;
	
	public SquareBumper(Point loc) {
		super(loc);
		super.type = "Square";
		super.color = Color.RED;
		super.initialColor = Color.RED;
		super.name = "S" + loc.x + "," + loc.y;
		constructSquare();
	}
	
	private void constructSquare() {
		lines = new ArrayList<>();
		corners = new ArrayList<>();
		
		double x = super.location.getX();
		double y = super.location.getY();
		
		lines.add(new LineSegment(x, y, x+Constants.L, y));
		lines.add(new LineSegment(x+Constants.L, y, x+Constants.L, y+Constants.L));
		lines.add(new LineSegment(x+Constants.L, y+Constants.L, x, y+Constants.L));
		lines.add(new LineSegment(x, y+Constants.L, x, y));
		
		corners.add(new Circle(x, y, 0));
		corners.add(new Circle(x+Constants.L, y, 0));
		corners.add(new Circle(x+Constants.L, y+Constants.L, 0));
		corners.add(new Circle(x, y+Constants.L, 0));
	} 
	@Override
	public void move(Point newLocation) {
		this.location = newLocation;
		constructSquare();
	}
	@Override
	public void rotate() {}

	@Override
	public ArrayList<LineSegment> getLines() {
		return lines;
	}

	@Override
	public ArrayList<Circle> getCorners() {
		// TODO Auto-generated method stub
		return corners;
	}

    @Override
    public Point getEndLocation() {
        return location;
    }
    
    @Override
	public List<Point> getAllPoints() {
		List<Point> points = new ArrayList<>();
		
		points.add(location);
		points.add(new Point((int)location.getX() + Constants.L,(int) location.getY()));
		points.add(new Point((int)location.getX(),(int) location.getY()+ Constants.L));
		points.add(new Point((int)location.getX() + Constants.L,(int) location.getY()+ Constants.L));
		
		return points;
	}

	@Override
	public int rotations() {
		return 0;
	}


}
