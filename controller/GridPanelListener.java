package controller;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import model.IModel;
import view.IView;

public class GridPanelListener implements MouseListener{

	public IView view;
	public IModel model;
	private String action;
	private Point absorberTopLeft;
	private Point oldLocation;
	private Point newLocation;
	private Point connectLocation;

	public GridPanelListener(IView v, IModel m) {
		view = v;
		model = m;
		action="";
	}
	
	private Point getPreciseLocation(Point p) {
		Point precisePoint = p;
		
		double x = precisePoint.getX();
		double y = precisePoint.getY();
		
		x = x - x%40;
		y = y - y%40;
		
		precisePoint.setLocation(x, y);
		return precisePoint;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {	
		
		Point precisePoint = getPreciseLocation(e.getPoint());
		Point ballPoint = e.getPoint();
		action = view.getSelectedAction().toLowerCase();
		switch(action) {
		case "square": 	   
		case "triangle":
		case "circle":
		case "rightflipper":
		case "leftflipper": new AddGizmoAction(model, precisePoint, action).performAction();
		break;
		case "delete" : new DeleteGizmoAction(model, precisePoint).performAction();
		break;
		case "rotate" : new RotateGizmoAction(model,precisePoint).performAction();
		break;		
		case "settrigger" : new SetTriggerAction(model, view, precisePoint).performAction();
		break;
		case "keyconnect" : new KeyConnectAction(model, precisePoint).performAction();
		break;
		case "keydisconnect" : new KeyDisconnectAction(model, precisePoint).performAction();
		break;
		case "ball": new AddBallAction(model, ballPoint).performAction();
		break;
		case "connect": if(connectLocation == null)
		{
			if(model.getGizmoAtLocation(precisePoint)!= null)
			{
				connectLocation = precisePoint;
			}
			else
			{
				JOptionPane.showMessageDialog(new JFrame(), "No gizmo at current location !","Connect failed",JOptionPane.ERROR_MESSAGE);
			}
		}
		else
		{
			new ConnectGizmoAction(precisePoint, connectLocation, model).performAction();
			connectLocation = null;
		}
		break;
		case "disconnect": new DisconnectGizmoAction(model, precisePoint).performAction();
		break;
		case "setvelocity": new SetVelocityAction(model,precisePoint).performAction();
		break;
		default : 	JOptionPane.showMessageDialog(new JFrame(), "Please select a button!", "Failed", JOptionPane.ERROR_MESSAGE);
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		action = view.getSelectedAction().toLowerCase();
		if(action.equals("absorber")) {
			Point precisePoint = getPreciseLocation(e.getPoint());
			absorberTopLeft = precisePoint;
			
		}else if(action.equals("move")){
			Point precisePoint = getPreciseLocation(e.getPoint());
			if(model.getGizmoAtLocation(precisePoint) == null)
			{
				JOptionPane.showMessageDialog(new JFrame(), "No gizmo at current location !","Move failed",JOptionPane.ERROR_MESSAGE);
			}
			oldLocation = precisePoint;
		}
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		if(action.equals("absorber")) { 
			Point absorberBotRight = getPreciseLocation(e.getPoint());
			new AddAbsorberAction(model, absorberTopLeft, absorberBotRight).performAction();
			
		}else if(action.equals("move")){
			Point precisePoint = getPreciseLocation(e.getPoint());
			newLocation = precisePoint;
			if(!model.moveGizmo(oldLocation, newLocation))
			{
				JOptionPane.showMessageDialog(new JFrame(), "There is already a gizmo at this location !","Move failed",JOptionPane.ERROR_MESSAGE);
			}
		}
	}

}
