package model;

import java.awt.Color;

public class ChangeColorTrigger implements TriggerAction {

	private String color;
	
	public ChangeColorTrigger(String color) {
		this.color = color;
	}
	
	@Override
	public void preformAction(IGizmo gizmo) {
		Color col;
		try {
			col = (Color) Class.forName("java.awt.Color").getField(color).get(null);
			gizmo.setColor(col);
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
