package controller;

import java.awt.Point;

import model.Constants;
import model.IModel;

public class AddBallAction 
{
	private IModel model;
	private Point coord;
	public AddBallAction(IModel model, Point coord) {
		this.model = model;
		this.coord = coord;
	}
	public void performAction()
	{
		
		model.addBall(coord,Constants.BALLRADIUS);
	}
}
