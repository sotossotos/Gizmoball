package controller;

import java.awt.Point;

import model.Constants;
import model.IModel;

public class AddAbsorberAction {
	private IModel model;
	private Point topLeft;
	private Point botRight;
	
	public AddAbsorberAction(IModel model, Point topL, Point botR) {
		this.model = model;
		topLeft = topL;
		botRight = botR;
	} 

	public void performAction() {
		if(topLeft != null && botRight != null) {
			botRight.setLocation(botRight.getX() + Constants.L, botRight.getY() + Constants.L);
			
			int brx = (int) botRight.getX();
			int tlx = (int) topLeft.getX();
			int bry = (int) botRight.getY();
			int tly = (int) topLeft.getY();
			
			if(brx < tlx)
			{
				double temp = botRight.getX() - Constants.L;
				botRight.setLocation(tlx + Constants.L, botRight.getY());
				topLeft.setLocation(temp, tly);
			}
			
			if(bry < tly) {
				double temp = botRight.getY() - Constants.L;
				botRight.setLocation(botRight.getX(), tly + Constants.L);
				topLeft.setLocation(tlx, temp);
			}
			
			model.addAbsorber(topLeft, botRight);
			
		}

	}
	
}
