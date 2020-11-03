package model;

import java.awt.Point;

public class GizmoFactory {
	
	public IGizmo createGizmo(Point loc, String type) {
		type = type.toLowerCase();
		switch(type) {
			case "square": return new SquareBumper(loc);
			case "triangle": return new TriangularBumper(loc);
			case "circle": return new CircularBumper(loc);
			case "leftflipper": return new Flipper(loc, "left");
			case "rightflipper": return new Flipper(loc, "right");
		}
		return null;
	}
	
}
