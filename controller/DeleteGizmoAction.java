package controller;

import java.awt.Point;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import model.IModel;

public class DeleteGizmoAction {

	private IModel model;
	private Point coord;
	
	public DeleteGizmoAction(IModel m, Point coord) {
		model = m;
		this.coord = coord;
	}
	public void performAction(){
		boolean isRemoved = model.removeGizmo(coord);
		boolean isRemovedAbsorber = model.removeAbsorber(coord);
		if(isRemoved || isRemovedAbsorber) 
		{
			JOptionPane.showMessageDialog(new JFrame(), "Delete successful", "Success",JOptionPane.INFORMATION_MESSAGE);
		}
		else
		{
			JOptionPane.showMessageDialog(new JFrame(), "No gizmo at current location !","Connect Failed",JOptionPane.ERROR_MESSAGE);
		}
	}

}
