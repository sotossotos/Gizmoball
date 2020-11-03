package model;

import java.awt.Point;
import java.util.List;
import java.util.Observer;
import java.util.Set;

public interface IModel {

	public Boolean addGizmo(Point location, String type);
	public Boolean removeGizmo(Point location);
	public IGizmo getGizmoAtLocation(Point location);
	public List<IGizmo> getGizmos();
	public Boolean addAbsorber(Point topLeft, Point botRight);
	public Boolean removeAbsorber(Point topLeft);
	public IAbsorber getAbsorber(Point topLeft);
	public boolean loadModel(String path);
	public void saveModel(String path);
    public IBall getBall();
    public void moveBall();
    public List<IAbsorber> getAbsorbers();
    public EnvironmentInteraction getEnvironment();
    public void addObserver(Observer o);
    public boolean couldGizmoBePlaced(String type, Point location);
    public boolean couldAbsorberBePlaced(Point locTL, Point locBR);
	public boolean rotateGizmo(Point location);	
	public boolean moveGizmo(Point oldLocation,Point newLocation);
    public IAbsorber getAbsorber(String name);
    public IGizmo getGizmo(String name);
    public void timeTick(Set<Character> keysPressed);
    public boolean addBall(Point center,double radius);
    public void resetBall();
    public void clearModel();
    public void removeBall();
	void ballLaunchAbsorber(Set<Character> keysPressed,IAbsorber ab);
}
