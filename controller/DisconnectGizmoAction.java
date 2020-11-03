package controller;
import java.awt.Point;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import model.IModel;

public class DisconnectGizmoAction {

	IModel model;
	Point coord;
	public DisconnectGizmoAction(IModel model, Point coord) {
		this.model = model;
		this.coord = coord;
	}
	public void performAction()
	{
		if(model.getGizmoAtLocation(coord) != null)
		{
			model.getGizmoAtLocation(coord).removeConnectedGizmos();
			JOptionPane.showMessageDialog(new JFrame(), "Disconnect successful", "Success",JOptionPane.INFORMATION_MESSAGE);
		}
		else
		{
			JOptionPane.showMessageDialog(new JFrame(), "No gizmo at current location !","Disconnect failed",JOptionPane.ERROR_MESSAGE);
		}
	}

}
