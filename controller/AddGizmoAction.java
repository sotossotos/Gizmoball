package controller;

import java.awt.Point;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import model.IModel;


public class AddGizmoAction{

	private IModel model;
	private Point coord;
	private String gizmoType;
	
	public AddGizmoAction(IModel m, Point coord, String type) {
		model = m;
		this.coord = coord;
		gizmoType = type;
	}
	
	public void performAction() {
		if(!model.addGizmo(coord, gizmoType))
		{
			JOptionPane.showMessageDialog(new JFrame(), "There is already a gizmo at this location!","Add failed",JOptionPane.ERROR_MESSAGE);
		}
	}

}
