package model;

public class RotateTrigger implements TriggerAction {

	@Override
	public void preformAction(IGizmo gizmo) {
		gizmo.rotate();		
	}

}
