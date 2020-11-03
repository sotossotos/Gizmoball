package model;

import java.awt.event.KeyEvent;

public final class Constants {
	public static final int DEFAULT_Absorber_Key= KeyEvent.VK_R;
	public static final double MU1 = 0.025;
	public static final double MU2 = 0.025;
	public static final int L = 40;
	public static final int DEFAULT_GRAVITY = 50;
	public static final double ballRADIUS = L/4;
	public static final double RADIUS = L/2;
	public static final double BALLRADIUS = L/4;
	public static final double width = L;
	public static final int timerMilliseconds =50;
	public static final double tickTimer =(timerMilliseconds / 1000.0);
	public static final double height = L;
	public static final double frames = 20;
	public static final double FRICTION_DELTA =tickTimer/2;
	public static final double fps = 1/frames;
	public static final double rotationAngleRad =  0.261799/3;
}
