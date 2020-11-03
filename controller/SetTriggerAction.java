package controller;

import java.awt.Point;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import model.IModel;
import view.IView;

public class SetTriggerAction 
{
	private IModel model;
	private Point coord;
	private IView view;
	
	public SetTriggerAction(IModel m,IView v, Point coord) {
		model = m;
		this.coord = coord;
		view = v;
	}
	public void performAction() 
	{
		String action = view.getTriggerAction().getSelectedItem().toString();
		
		if(model.getGizmoAtLocation(coord) != null)
		{
			if(action.contains("Red")) 
			{ 
				model.getGizmoAtLocation(coord).setTriggerAction("changecolor", "red"); 
			}
			else if(action.contains("Green")) {
				model.getGizmoAtLocation(coord).setTriggerAction("changecolor", "green"); 
			}
			else if(action.contains("Blue")) {
				model.getGizmoAtLocation(coord).setTriggerAction("changecolor", "blue"); 
			}
			else {
				model.getGizmoAtLocation(coord).setTriggerAction("rotate", ""); 
			}
			JOptionPane.showMessageDialog(new JFrame(), "Trigger Action set", "Success",JOptionPane.INFORMATION_MESSAGE);
		}
		else
		{
			JOptionPane.showMessageDialog(new JFrame(), "No gizmo at current location !","Set Trigger Action Failed",JOptionPane.ERROR_MESSAGE);
		}
		
		
	}
}
