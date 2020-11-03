package model;

import physics.Vect;

/**
 * @author Murray Wood Demonstration of MVC and MIT Physics Collisions 2014
 */

public  class CollisionDetails {
	private double tuc;
	private Vect velo;
	private IGizmo triggeredGizmo;

	public IGizmo getTriggeredGizmo() {
		return triggeredGizmo;
	}

	public void setTriggeredGizmo(IGizmo triggeredGizmo) {
		this.triggeredGizmo = triggeredGizmo;
	}

	public CollisionDetails(double t, Vect v) {
		tuc = t;
		velo = v;
	}

	public double getTuc() {
		return tuc;
	}
	
	public Vect getVelo() {
		return velo;
	}

}
