package model;

import java.awt.Color;
import java.awt.Point;
import java.util.List;

import physics.Circle;
import physics.LineSegment;

public interface IGizmo {
	
	public String getName();
	
	public void setName(String name);
	
	public Color getColor();
	
	public String getType();
	
	public void setColor(Color color);
	
	public Point getLocation();
	
	public void move(Point newLocation);
	
	public void rotate();
	
	public List<LineSegment>getLines();
	
	public List<Circle> getCorners();
	
	public void setTriggerAction(String triggerAction, String triggerParameter);
	
	public boolean addConnectedGizmo(IGizmo gizmo);
	
	public boolean removeConnectedGizmo(IGizmo gizmo);
	
	public void performAction();
	
	public void rollbackFlipper();

    public Point getEndLocation();
    
    public List<Point> getAllPoints();
    
    public void setKeyConnect(int key);
    
    public int getKeyConnect();

    public int rotations();

    public List<IGizmo> getConnectedGizmos();
    
    public void removeConnectedGizmos();
    
    public boolean isTriggered();
    
    public void setTriggered(boolean trigger);
    
    public void addTick();
}
