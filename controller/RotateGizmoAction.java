package controller;

import java.awt.Point;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import model.IModel;

public class RotateGizmoAction {

	private IModel model;
	private Point coord;
	
	public RotateGizmoAction(IModel m, Point coord) {
		this.model=m;
		this.coord=coord;
	}
	
	public void performAction(){
		if(model.getGizmoAtLocation(coord)!=null){
			model.rotateGizmo(coord);
		}
		else{
			JOptionPane.showMessageDialog(new JFrame(), "No gizmo at current location !","Failed Rotation",JOptionPane.ERROR_MESSAGE);
		}		
	}

}
