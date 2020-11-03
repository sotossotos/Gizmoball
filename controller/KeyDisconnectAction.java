package controller;
import java.awt.Point;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import model.IModel;

public class KeyDisconnectAction 
{
	private IModel model;
	private Point coord;

	public KeyDisconnectAction(IModel model, Point coord) {
		this.model = model;
		this.coord = coord;
	}
	
	public void performAction()
	{
		if(model.getGizmoAtLocation(coord) != null)
		{
			model.getGizmoAtLocation(coord).setKeyConnect(' ');
			JOptionPane.showMessageDialog(new JFrame(), "Success");
		}
		else
		{
			JOptionPane.showMessageDialog(new JFrame(), "No gizmo at current location !","Key Connect Failed",JOptionPane.ERROR_MESSAGE);
		}
	}
}
