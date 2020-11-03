package controller;

import java.awt.Point;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import model.IModel;

public class ConnectGizmoAction 
{

	IModel model;
	Point coord;
	Point connectCoord;
	public ConnectGizmoAction(Point coord, Point connectCoord, IModel model) 
	{
		this.model = model;
		this.coord = coord;
		this.connectCoord = connectCoord;
	}
	public void performAction()
	{
		if(model.getGizmoAtLocation(coord) != null && model.getGizmoAtLocation(connectCoord) != null && !(connectCoord.equals(coord)))
		{
			model.getGizmoAtLocation(connectCoord).addConnectedGizmo(model.getGizmoAtLocation(coord));
			JOptionPane.showMessageDialog(new JFrame(), "Connection added!");
		}
		else
		{
			JOptionPane.showMessageDialog(new JFrame(), "No gizmo at current location !","Connect Failed",JOptionPane.ERROR_MESSAGE);
		}
	}
}