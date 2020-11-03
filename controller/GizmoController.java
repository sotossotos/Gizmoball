package controller;

import view.IView;
import model.IModel;
//import view.IView;

public class GizmoController implements IGizmoController 
{
	private IView view;
	private IModel model;
	private RunKeyListener keyListener;
	private PlayModeListener playListener;
	private SaveListener saveListener;
	
	public GizmoController(IView v, IModel m)
	{
		view = v;
		model= m;
		keyListener = new RunKeyListener();
		playListener = new PlayModeListener(model, keyListener);
		saveListener = new SaveListener(model);
	}

	@Override
	public GridPanelListener getGridPanelListener() {
		return new GridPanelListener(view, model);
	}

	@Override
	public RunKeyListener getRunKeyListener() {
		return keyListener;
	}

	@Override
	public PlayModeListener getPlayModeListener() {
		return playListener;
	}

    @Override
    public SaveListener getSaveListener() {
        return saveListener;
    }

	@Override
	public LoadListener getLoadListener() {
		return new LoadListener(model);
	}

	@Override
	public ChangeGravitySliderListener getGravitySliderListener() {
		return new ChangeGravitySliderListener(model,view);
	}

	@Override
	public ChangeFrictionSliderListener getFrictionSliderListener() {
		return new ChangeFrictionSliderListener(model,view);
	}


	
}
