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

public class Flipper extends Gizmo{

	private ArrayList<LineSegment> outline;
	private Circle topCircle;
	private Circle botCircle;
	private String specificator;
	private int currentAngle;
	private boolean isMovingUp;
	private boolean isMovingDown;
	private int flipperTicks;
	
	public Flipper(Point loc, String specificator) {
		super(loc);
		this.specificator = specificator.toLowerCase();
		super.type = Character.toUpperCase(specificator.charAt(0))+ specificator.substring(1) + "Flipper";
		super.color = Color.gray;
		super.initialColor = Color.gray;
		super.name = "F" + loc.x + "," + loc.y;
		this.currentAngle=0;		
		isMovingUp = false;
		isMovingDown = false;
		flipperTicks = 0;
		constructFlipper();
	} 
	
	private void constructFlipper() {
		outline = new ArrayList<>();
		double x = super.location.getX();
		if(specificator.equals("left")){
			constructOrientedFlipper(x+Constants.L/4, x, x+Constants.L/2);
			super.keyConnect = 'z';
		}
		else {
			constructOrientedFlipper(x+3*Constants.L/4, x+Constants.L/2, x+Constants.L);		
			super.keyConnect = 'x';
		}
	}
	
	private void constructOrientedFlipper(double xTopCenter, double xFirstLine, double xSecondLine) {
		double x = super.location.getX();
		double y = super.location.getY();
		
		this.topCircle = new Circle(xTopCenter, y+Constants.L/4, Constants.L/4);
		this.botCircle = new Circle(xTopCenter, y+7*Constants.L/4, Constants.L/4);
		
		outline.add(new LineSegment(xFirstLine, y+Constants.L/4, xFirstLine, y+7*Constants.L/4));
		outline.add(new LineSegment(xSecondLine, y+Constants.L/4, xSecondLine , y+7*Constants.L/4));
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
		Vect center;
		if(this.specificator.equals("left")){
			 center = new Vect(super.location.getX()+Constants.L, super.location.getY()+Constants.L);
		}else {
		     center = new Vect(super.location.getX(), super.location.getY()+Constants.L);
		}
		ArrayList<LineSegment> rotatedLines = new ArrayList<>();
		
		this.botCircle = Geometry.rotateAround(botCircle, center, rotateAngle);
		this.topCircle = Geometry.rotateAround(topCircle, center, rotateAngle);
		
		int i = 0;
		for(LineSegment l : outline){
			rotatedLines.add(Geometry.rotateAround(l, center, rotateAngle));	
			i++;
		}
		outline.clear();
		outline.addAll(rotatedLines);
	
	}
	
	@Override
	public void rotate() {
		this.currentAngle=(this.currentAngle+1)%4;
		resolveRotation(Angle.DEG_90);
	}

	@Override
	public void move(Point newLocation) {
		super.location=newLocation;
		this.constructFlipper();
	}
	
	@Override
	public void performAction() {
		
		super.performAction();
		
		if(flipperTicks < 18) {
		
			if(specificator.equals("left"))
				flipOriented(-Constants.rotationAngleRad);
			else
				flipOriented(Constants.rotationAngleRad);
			
			isMovingUp = true;
			isMovingDown = false;

			flipperTicks++;
		}
	}
	
	public void rollbackFlipper() {
		
		if(flipperTicks > 0) {
			
			if(specificator.equals("left"))
				flipOriented(Constants.rotationAngleRad);
			else
				flipOriented(-Constants.rotationAngleRad);
			isMovingDown = true;
			isMovingUp = false;
			flipperTicks--;
		}
	}
	
	private void flipOriented(double angleRad) {
		Vect rotationCenter = this.topCircle.getCenter();
		
		ArrayList<LineSegment> rotatedLines = new ArrayList<>();
		
		this.botCircle = Geometry.rotateAround(botCircle, rotationCenter, new Angle(angleRad));
		
		for(LineSegment l : outline){
			rotatedLines.add(Geometry.rotateAround(l, rotationCenter, new Angle(angleRad)));	
		}
		
		outline.clear();
		outline.addAll(rotatedLines);
	}

	@Override
	public ArrayList<LineSegment> getLines() {
		return outline;
	}

	@Override
	public ArrayList<Circle> getCorners() {
		ArrayList<Circle> circles = new ArrayList<>();
		circles.add(topCircle);
		circles.add(botCircle);
		return circles;
	}
	
	@Override
    public Point getEndLocation() {
        return new Point(location.x + Constants.L, location.y + Constants.L);
    }

	@Override
	public List<Point> getAllPoints() {
		ArrayList<Point> points = new ArrayList<>();
		Point topC = new Point();
		topC.setLocation(topCircle.getCenter().x(), topCircle.getCenter().y());
		Point botC = new Point();
		botC.setLocation(botCircle.getCenter().x(), botCircle.getCenter().y());
		points.add(topC);
		points.add(botC);
		
		boolean isSecond = false;
		for(LineSegment l : outline) {
			Point p1 = new Point();
			p1.setLocation(l.p1().x(), l.p1().y());
			Point p2 = new Point();
			p2.setLocation(l.p2().x(), l.p2().y());
			if(!isSecond) {
				points.add(p1);
				points.add(p2);
			}
			else {
				points.add(p2);
				points.add(p1);
			}
			isSecond = true;
		}
		
		return points;
	}
	@Override
	public int rotations() {
		return currentAngle;
	}
	public Vect getTopCircle() {
		return this.topCircle.getCenter();
	}

	public boolean isMoving() {
		return flipperTicks>0 && flipperTicks<6;
	}
	
	public boolean isMovingUp() {
		return isMovingUp && isMoving();
	}
	
	public boolean isMovingDown() {
		return isMovingUp && isMoving();
	}
}
