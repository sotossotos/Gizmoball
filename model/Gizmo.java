package model;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import physics.Circle;
import physics.LineSegment;

public abstract class Gizmo implements IGizmo{

	protected Point location;
	protected String name;
	protected TriggerAction action;
	protected Color color;
	protected double coefReflection;
	protected String type;
	protected int keyConnect;
	protected List<IGizmo> connectedGizmos;
	protected boolean isTriggered;
	protected Color initialColor;
	protected int ticks;
	private boolean toRollBack = false;
	
	public Gizmo(Point loc) {
		this.location = loc;
		connectedGizmos = new ArrayList<>();
		isTriggered = false;
	}
	
	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public Color getColor() {
		return color;
	}

	@Override
	public void setColor(Color color) {
		this.color = color;
	}

	@Override
	public Point getLocation() {
		return location;
	}


	@Override
	public void move(Point newLocation) {
		System.out.println("jdjdjff");
		this.location = newLocation;
	}


	@Override
	public void setTriggerAction(String triggerAction, String triggerParameter) {
		TriggerActionFactory taFactory = new TriggerActionFactory();
		this.action = taFactory.createTriggerAction(triggerAction, triggerParameter);
	}

	@Override
	public boolean addConnectedGizmo(IGizmo gizmo) {
		return connectedGizmos.add(gizmo);
	}

	@Override
	public boolean removeConnectedGizmo(IGizmo gizmo) {
		return connectedGizmos.remove(gizmo);
	}
	
	@Override 
	public List<IGizmo> getConnectedGizmos() {
	    return connectedGizmos;
	}
	
	@Override
	public String getType() {
		return type;
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
	public void performAction() {
		if(action != null){
			action.preformAction(this);
		}
		
		if(isTriggered)
			for(IGizmo gizmo : connectedGizmos) {
				gizmo.performAction();
			}
		isTriggered = false;
		toRollBack = true;
	}
	@Override
	public void removeConnectedGizmos()
	{
		connectedGizmos.clear();
	}
	
	public boolean isTriggered() {
		return isTriggered;
	}
	
	public void setTriggered(boolean trigger) {
		isTriggered = true;
	}
	
	protected void setInitialColor() {
		color = initialColor;
	}
	
	public void addTick() {
		if(toRollBack && ticks==30)
		{
			setInitialColor();
			toRollBack = false;
		}
		else if (ticks < 30 && toRollBack){
			ticks++;
		}
		else 
			ticks = 0;
			
	}
	
	public void rollbackFlipper() {} 
	
	public abstract ArrayList<LineSegment> getLines();
	
	public abstract ArrayList<Circle> getCorners();

}
