package view;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import model.IAbsorber;
import model.IGizmo;
import model.IModel;
import model.Model;

public class GamePanel extends JPanel {

	private IModel model;
	
	public GamePanel() 
	{
		super();
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		repaint();
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
	}
	public void setModel(IModel m) {
		model = new Model();
		model = m;
	}
	public void paintGizmos(Graphics g){
		IGizmoPainter painter;
		for(IGizmo gizmo : model.getGizmos()) {
			switch(gizmo.getType().toLowerCase()) {
				case "triangle": 	painter = new TrianglePainter(gizmo.getAllPoints());
									painter.setColor(gizmo.getColor());
							     	painter.paint(g);
							     	break;
				case "square": 	 	painter = new SquarePainter(gizmo.getAllPoints());
									painter.setColor(gizmo.getColor());
							     	painter.paint(g);
							     	break;	    
				case "circle":	 	painter = new CirclePainter(gizmo.getAllPoints());
									painter.setColor(gizmo.getColor());
								 	painter.paint(g);
								 	break;
				case "leftflipper": painter = new LeftFlipperPainter(gizmo.getAllPoints());
									painter.setColor(gizmo.getColor());
									painter.paint(g);
									break;					
				case "rightflipper":painter = new RightFlipperPainter(gizmo.getAllPoints());
									painter.setColor(gizmo.getColor());
								 	painter.paint(g);
								 	break;
			}

	}
		
		for(IAbsorber absorber : model.getAbsorbers()) {
			painter  = new AbsorberPainter(absorber.getTopLeftPoint(), absorber.getBotRightPoint());
			
			painter.setColor(absorber.getColor());
			painter.paint(g);
		}
		if(model.getBall() != null)
		{
		painter = new BallPainter(model.getBall().getPoint());
		painter.setColor(model.getBall().getColor());
		painter.paint(g);
		}
	}
}
