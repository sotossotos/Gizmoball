package controller;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import model.IModel;
import view.IView;

public class ChangeGravitySliderListener implements ChangeListener {

	IModel model;
	IView view;
	public ChangeGravitySliderListener(IModel model, IView view) {
		this.model = model;
		this.view = view;
	}

	@Override
	public void stateChanged(ChangeEvent arg0) 
	{
		int value = view.getGravitySlider().getValue();
		model.getEnvironment().setGravity(value);
	}

}
