package model;

import java.awt.Color;
import java.awt.Point;
import physics.Angle;
import physics.Vect;

public class Absorber implements IAbsorber {
	private String name;
	private Point topleft;
	private Point bottomright;
	private Color colour;
	private int keyConnect;
	private boolean wasBallOutside;

	public Absorber(Point xy1,Point xy2) {
		topleft = xy1;
		bottomright= xy2;
		colour = Color.YELLOW;
		name = "A" + xy1.x + "," + xy1.y;
		keyConnect = Constants.DEFAULT_Absorber_Key;
		wasBallOutside = true;
	}
	
	public boolean insideMe(Point xy, double radius){
		if(checkPoint(xy.getX(),xy.getY()+radius) && checkPoint(xy.getX()-radius,xy.getY()) 
		   && checkPoint(xy.getX(),xy.getY()-radius) && checkPoint(xy.getX()+radius,xy.getY())){
			return true;
		}
		return false;
	}
	
	private boolean checkPoint(double x, double y){
		if(x>=topleft.getX() && y>=topleft.getY() && x<=bottomright.getX() && y<=bottomright.getY()){
			return true;
		}
		return false;
	}

	public Vect launchBall() {
		return new Vect(Angle.DEG_270,Constants.L*Constants.DEFAULT_GRAVITY);
	}
	public Point absorbBall(double radius){
		Point newBallPos = new Point();
		newBallPos.setLocation(bottomright.getX() - radius, bottomright.getY() -radius);
		return newBallPos;
	}
	
	public Color getColor() {
		return colour;
	}
	
	@Override
	public void setColor(Color color) {
		colour = color;
	}
	
	public String getName() {
		return name;
	}  
	
	@Override
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public void setTopLeftPoint(Point p) {
		topleft = p;
	}
	
	@Override
	public void setBotRightPoint(Point p) {
		bottomright = p;
	}
	
	@Override
	public Point getTopLeftPoint() {
		return topleft;
	}
	
	@Override
	public Point getBotRightPoint() {
		return bottomright;
	}
	
	@Override
	public void setKeyConnect(int key) {
		this.keyConnect = key;
	}
	
	@Override
	public int getKeyConnect() {
		return keyConnect;
	}
	@Override
	public void setWasItemOuside(boolean assignment) {
		this.wasBallOutside=assignment;
	}
	@Override
	public boolean getWasItemOuside() {
		return this.wasBallOutside;
	}

}
