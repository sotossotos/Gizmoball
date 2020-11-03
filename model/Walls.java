package model;

import java.awt.Point;
import java.util.ArrayList;
import physics.LineSegment;

public class Walls {

	private double xpos1;
	private double ypos1;
	private double ypos2;
	private double xpos2;
	// Walls are the enclosing Rectangle - defined by top left corner and bottom
	// right
	public Walls(Point xy1, Point xy2) {
		xpos1 = xy1.getX();
		ypos1 = xy1.getY();
		xpos2 = xy2.getX();
		ypos2 = xy2.getY();
	}

	public ArrayList<LineSegment> getLineSegments() {
		ArrayList<LineSegment> ls = new ArrayList<LineSegment>();
		LineSegment l1 = new LineSegment(xpos1, ypos1, xpos2, ypos1);
		LineSegment l2 = new LineSegment(xpos1, ypos1, xpos1, ypos2);
		LineSegment l3 = new LineSegment(xpos2, ypos1, xpos2, ypos2);
		LineSegment l4 = new LineSegment(xpos1, ypos2, xpos2, ypos2);
		ls.add(l1);
		ls.add(l2);
		ls.add(l3);
		ls.add(l4);
		return ls;
	}
	public boolean wallIntersection(Point xy, double radius) {
		//calculate line equation with two point of line ax+by+c

			for(int i=0;i<4;i++){
				double a = Math.abs(this.getLineSegments().get(i).p1().x()-this.getLineSegments().get(i).p2().x());
				double b = Math.abs(this.getLineSegments().get(i).p1().y()-this.getLineSegments().get(i).p2().y());
				double c = (Math.abs(this.getLineSegments().get(i).p1().x()-this.getLineSegments().get(i).p2().x()))*this.getLineSegments().get(i).p1().y()+this.getLineSegments().get(i).p1().x()*(Math.abs(this.getLineSegments().get(i).p2().y()-this.getLineSegments().get(i).p1().y()));
				double check = Math.abs(a*xy.getX()+b*xy.getY()+c)/Math.sqrt(Math.pow(a, 2)+Math.pow(b, 2));
				if (i>=2)
				{
					if(check>=(2*(Constants.L*20))-Constants.BALLRADIUS){return true;}
				}
				if(check<=radius){return true;}		
			}
			System.out.println();
			System.out.println();
			System.out.println();

		return false;
	}

}
