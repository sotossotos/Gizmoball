package controller;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import model.Constants;
import model.IModel;
import view.IView;

public class ChangeFrictionSliderListener implements ChangeListener {

	IModel model;
	IView view;
	public ChangeFrictionSliderListener(IModel model, IView view) {
		this.model = model;
		this.view = view;
	}

	@Override
	public void stateChanged(ChangeEvent arg0) 
	{
		int value = view.getFrictionSlider().getValue()/100;
		model.getEnvironment().setFriction(value, Constants.MU2);
	}

}