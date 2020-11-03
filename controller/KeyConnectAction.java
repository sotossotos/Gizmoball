package controller;

import java.awt.Point;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import model.IModel;


public class KeyConnectAction {
	
	private IModel model;
	private Point coord;

	public KeyConnectAction(IModel model, Point coord) {
		this.model = model;
		this.coord = coord;
	}
	
	public void performAction() {
		if(model.getGizmoAtLocation(coord) != null || model.getAbsorber(coord)!=null)
		{
			String key = JOptionPane.showInputDialog("Choose a key");
			while(true && key != null)
			{
			if(key.toCharArray().length == 1)
			{
				if(key.toCharArray()[0] >= 97 && key.toCharArray()[0] <=122)
				{
					if(model.getGizmoAtLocation(coord) != null){
						model.getGizmoAtLocation(coord).setKeyConnect((int)key.toCharArray()[0]);
						break;
					}else{
						model.getAbsorber(coord).setKeyConnect((int)key.toCharArray()[0]);
						break;
					}
					
				}
				else
				{
					key = JOptionPane.showInputDialog(new JFrame(), "Invalid character!","Key Connect Failed",JOptionPane.ERROR_MESSAGE);
				}
			}
			else
			{
				key = JOptionPane.showInputDialog(new JFrame(), "You need to enter only 1 character!","Key Connect Failed",JOptionPane.ERROR_MESSAGE);
			}
			}
		}else if(model.getAbsorber(coord)!=null){
			
		}
		else
		{
			JOptionPane.showMessageDialog(new JFrame(), "No gizmo at current location !","Key Connect Failed",JOptionPane.ERROR_MESSAGE);
		}
		
	}

}
