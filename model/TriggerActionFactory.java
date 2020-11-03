package model;

public class TriggerActionFactory {
	
	public TriggerAction createTriggerAction(String action, String parameter) {
		switch(action.toLowerCase()) {
			case "changecolor": return new ChangeColorTrigger(parameter);
			case "rotate" : return new RotateTrigger();
		}
		return null;
	}
	
}
