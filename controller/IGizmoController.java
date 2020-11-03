package controller;


public interface IGizmoController 
{
	public GridPanelListener getGridPanelListener();
	public RunKeyListener getRunKeyListener();
	public PlayModeListener getPlayModeListener();
	public SaveListener getSaveListener();
	public LoadListener getLoadListener();
	public ChangeGravitySliderListener getGravitySliderListener();
	public ChangeFrictionSliderListener getFrictionSliderListener();
}
