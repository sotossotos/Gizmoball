package controller;

import java.awt.Point;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import model.IModel;
import physics.Vect;

public class SetVelocityAction {
	private IModel model;
	public SetVelocityAction(IModel model, Point coord) {
		this.model = model;
	}
	public void performAction(){
		int xValue=0;
		int yValue=0;
	if(model.getBall()!=null){
		String x = (String)JOptionPane.showInputDialog(
                new JFrame(),
                "Please enter x for velocity\n"
                + "\"Cartesian point\"",
                "Velocity for X",
                JOptionPane.PLAIN_MESSAGE,
                null,
                null,
                "0");
		String y= (String)JOptionPane.showInputDialog(
                new JFrame(),
                "Please enter x for velocity\n"
                + "\"Cartesian point\"",
                "Velocity for Y",
                JOptionPane.PLAIN_MESSAGE,
                null,
                null,
                "0");
		try{
		 xValue=Integer.parseInt(x);
		 yValue=Integer.parseInt(y);
		}catch(NumberFormatException e){
			JOptionPane.showMessageDialog(new JFrame(),
				    "Your input for X or Y FAILED!\nTry again",
				    "Input Error !",
				    JOptionPane.ERROR_MESSAGE);
			return;
		}
		model.getBall().setVelocity(new Vect(xValue,yValue));
		}
	}

}
