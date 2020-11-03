package model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Set;


public class Model extends Observable implements IModel{
	
	private Walls walls;
	private List<IAbsorber> absorbers;
	private List<IGizmo> gizmos;
	private GizmoFactory gizmoFactory;
	private IBall ball;
	private EnvironmentInteraction env;
    private boolean[][] occupationGrid;
	
	public Model() {
		walls = new Walls(new Point(0, 0), new Point(20*Constants.L, 20*Constants.L));
		absorbers = new ArrayList<>();
		gizmos = new ArrayList<>();
		gizmoFactory = new GizmoFactory();
		env = new EnvironmentInteraction();
		//ball = new Ball();
		
		occupationGrid = new boolean[20][20];
		for(int i = 0; i < 20; i++) {
            for(int j = 0; j < 20; j++) {
                occupationGrid[i][j] = false;
            }
        }

	}
	
	@Override
	public Boolean addGizmo(Point location, String type) {
		
		String n = "G" + location.x + "," + location.y;
		String addN = "";
		int dupe = 1;
		while(getAbsorber(n + addN) != null || getGizmo(n + addN) != null){
		    addN = "(" + dupe + ")";
		    dupe++;
		}
        if(couldGizmoBePlaced(type, location) && gizmos.add(gizmoFactory.createGizmo(location, type))) {
        	occupyOrRemoveO(type, location, true);
        	getGizmoAtLocation(location).setName(n + addN);
            this.setChanged();
            this.notifyObservers();
			return true;
		}
		return false;
	}

	public void moveBall(){
		if(env.moveBall(ball,gizmos,absorbers,walls)){
			this.setChanged();
			this.notifyObservers();
		}
	}
	public void checkBallOccypancy(Point xy,boolean occupy){
		ArrayList<Point> positions = positionsOccupyByBall(xy);
		for(Point position: positions){
			
			this.occupationGrid[position.x][position.y]=occupy;	
			System.out.println("Added ball occypy");
		}
	}
	public boolean checkAlreadyOccupied(Point xy){
		ArrayList<Point> positions = positionsOccupyByBall(xy);
		for(Point position: positions){
				if(this.occupationGrid[position.x][position.y]==true){
					return true;
				}
		}
		return false;
	}
	private ArrayList<Point> positionsOccupyByBall(Point xy) {
		ArrayList<Point> positionsFound = new ArrayList<>();
		int y=0;
		for(int i =0 ;i<20;i++){
			int x=0;
			for(int j =0; j<20;j++)
			{	
				Point rectXY = new Point();
				double rectWidth = Constants.L;
				double rectHeight = Constants.L;
				rectXY.setLocation(x, y);
				
				double distX = Math.abs(xy.getX()-rectXY.getX()-rectWidth/2);
				double distY = Math.abs(xy.getY()-rectXY.getY()-rectHeight/2);
				 if (distX > (rectWidth / 2 + Constants.BALLRADIUS)) {
				        // false;
				 }
				 else if (distY > (rectHeight/ 2 + Constants.BALLRADIUS)) {
				       //false;
				}
				 else if(distX<=(rectWidth/2)){
//					this.occupationGrid[j][i]=occupy;
					positionsFound.add(new Point(i,j));
					
				}
				 else if(distY<=(rectHeight/2)){
//					this.occupationGrid[j][i]= occupy;
					 positionsFound.add(new Point(i,j));

				}
				double dx = distX-rectWidth/2;
				double dy = distY-rectHeight/2;
				if(dx * dx + dy * dy <= (Constants.BALLRADIUS * Constants.BALLRADIUS)){
//					this.occupationGrid[j][i]=occupy;
					positionsFound.add(new Point(i,j));
					
				}
				x+=Constants.L;
			}
			
			y+=Constants.L;
		}
		return positionsFound;
	}
    @Override
	public Boolean removeGizmo(Point location) {
		for (IGizmo gizmo : gizmos) 
		{
			if(location.equals(gizmo.getLocation())) {
				Boolean isRemoved = gizmos.remove(gizmo);
				if(isRemoved)
					occupyOrRemoveO(gizmo.getType(), location, false);
				this.setChanged();
				this.notifyObservers();
				return isRemoved;
			}
		}

		return false;
	}
    
    public boolean rotateGizmo(Point location){
    	for(IGizmo gizmo : gizmos){
    		if(location.equals(gizmo.getLocation())){
    			gizmo.rotate();
    			this.setChanged();
    			this.notifyObservers();
    			return true;
    		}
    	}
    	return false;
    }

    @Override
	public IGizmo getGizmoAtLocation(Point location) {
		for (IGizmo gizmo : gizmos) {
			if(location.equals(gizmo.getLocation()))
				return gizmo;
		}
		return null;
	}
    
    @Override
    public IGizmo getGizmo(String name) {
        for (IGizmo gizmo : gizmos) {
            if(name.equals(gizmo.getName()))
                return gizmo;
        }
        return null;
    }

	@Override
	public List<IGizmo> getGizmos() {
		return gizmos;
	}

	@Override
	public Boolean addAbsorber(Point topLeft, Point botRight) {
		String n = "A" + topLeft.getX() + "," + topLeft.getY();
		String addN = "";
		int dupe = 1;
		while(getAbsorber(n) != null || getGizmo(n) != null){
		    addN = "(" + dupe + ")";
		    dupe++;
		}
		IAbsorber absorber = new Absorber(topLeft, botRight);
		if(couldAbsorberBePlaced(topLeft, botRight)) {
			absorbers.add(absorber);
			absorber.setName(n + addN);
			occupyOrRemoveOAbsorber(topLeft, absorber.getBotRightPoint(), true);
			this.setChanged();
			this.notifyObservers();
			return true;
		}
		return false;
	}

	@Override
	public Boolean removeAbsorber(Point p) {
		for (IAbsorber absorber : absorbers) {
			if(p.x>=absorber.getTopLeftPoint().getX() && p.x<=absorber.getBotRightPoint().getX() &&
			   p.y>=absorber.getTopLeftPoint().getY() && p.y<=absorber.getBotRightPoint().getY()) 
			{
				Boolean isRemoved = absorbers.remove(absorber);
				if(isRemoved)
					occupyOrRemoveOAbsorber(absorber.getTopLeftPoint(), absorber.getBotRightPoint(), false);
				this.setChanged();
				this.notifyObservers();
				return isRemoved;
			}
		}
		return false;
	}
    public void resetBall(){
    	if(ball!=null){
    	ball.reset();
    	}
    	this.setChanged();
    	this.notifyObservers();
    }
	@Override
	public IAbsorber getAbsorber(Point topLeft) {
		for (IAbsorber absorber : absorbers) {
			if(absorber.getTopLeftPoint().equals(topLeft))
				return absorber;
		}
		return null;
	}
	@Override
	public IAbsorber getAbsorber(String name) {
        for (IAbsorber absorber : absorbers) {
            if(absorber.getName().equals(name))
                return absorber;
        }
        return null;
    }

	@Override
	public boolean loadModel(String path) {
		clearModel();
	    return Loader.parseFile(path, this);
	}

	@Override
	public void saveModel(String path) {
	    Saver.writeFile(path, this);
		
	}

    @Override
    public IBall getBall() {
        return ball;
    }
    @Override
    public boolean addBall(Point center,double radius)
    {
    	boolean ballIntersects = this.walls.wallIntersection(center, radius);
    	if(checkAlreadyOccupied(center)){
    		return false;
    	}
    	if(ballIntersects){
    		return false;
    	}
    	this.checkBallOccypancy(center,true);
    	if(this.getBall()!=null){
    		this.checkBallOccypancy(ball.getPoint(),false);
    	}
    	if(!ballIntersects){
    		ball = new Ball(center,radius);	
    		this.setChanged();
        	this.notifyObservers();
    	}
    	return ballIntersects;
    }
    
    @Override
    public List<IAbsorber> getAbsorbers() {
        return absorbers;
    }

    @Override
    public EnvironmentInteraction getEnvironment() {
        return env;
    }

	@Override
	public boolean couldGizmoBePlaced(String type, Point p) {
		int x = (int) p.getX()/Constants.L;
		int y =(int) p.getY()/Constants.L;
		if(x>20||y>20||x<0||y<0){
			return false;
		}
		if(type.toLowerCase().equals("leftflipper")) {
			if(x!=19 && y!=19) {
				return (!occupationGrid[y][x]) && (!occupationGrid[y][x+1]) && (!occupationGrid[y+1][x]) && (!occupationGrid[y+1][x+1]);
			}
		}
		else if(type.toLowerCase().equals("rightflipper")) {
			if(x!=0 && y!=19) {
				return (!occupationGrid[y][x]) && (!occupationGrid[y][x-1]) && (!occupationGrid[y+1][x]) && (!occupationGrid[y+1][x-1]);
			}
		}
		else return !occupationGrid[y][x]; 
		
		return false;
	}

	private void occupyOrRemoveO(String type, Point p, Boolean forOccupy) {
		
		int x = (int) p.getX()/40;
		int y =(int) p.getY()/40;
		
		System.out.println(x + " " + y);
		
		if(type.toLowerCase().equals("leftflipper")) {
				occupationGrid[y][x] = forOccupy;
				occupationGrid[y][x+1] = forOccupy;
				occupationGrid[y+1][x] = forOccupy; 
				occupationGrid[y+1][x+1] = forOccupy;
		}
		else if(type.toLowerCase().equals("rightflipper")){
			occupationGrid[y][x] = forOccupy; 
			occupationGrid[y][x-1] = forOccupy; 
			occupationGrid[y+1][x] = forOccupy; 
			occupationGrid[y+1][x-1] = forOccupy;
		}
		else {
			occupationGrid[y][x] = forOccupy; 
		}
		
	}

	@Override
	public boolean couldAbsorberBePlaced(Point locTL, Point locBR) {
		
		int tlx = (int) locTL.getX()/40;
		int tly =(int) locTL.getY()/40;
		
		int brx = (int) locBR.getX()/40;
		int bry =(int) locBR.getY()/40;
		
		for(int i = 0; i < brx - tlx;  i++) {
			for(int j = 0; j < bry - tly; j++ ) {
				if(occupationGrid[tly+j][i+tlx])
					return false;
			}
		}
		
		return true;
	}
	
	private void occupyOrRemoveOAbsorber(Point locTL, Point locBR, Boolean forOccupy) {
		
		int tlx = (int) locTL.getX()/40;
		int tly =(int) locTL.getY()/40;
		
		int brx = (int) locBR.getX()/40;
		int bry =(int) locBR.getY()/40;
		
		System.out.println(tlx + " " + tly + " " +  brx + " " +  bry + " ");
		
		for(int i = 0; i < brx - tlx;  i++) {
			for(int j = 0; j < bry - tly; j++ ) {
				occupationGrid[tly+j][i+tlx] = forOccupy;
			}
		}		
	}

	@Override
	public boolean moveGizmo(Point oldLocation,Point newLocation) {
		
		IGizmo g = getGizmoAtLocation(oldLocation);
		this.occupyOrRemoveO(g.getType(),oldLocation , false);
		
		if(couldGizmoBePlaced(g.getType(),newLocation)) {
			this.occupyOrRemoveO(g.getType(),newLocation, true);
			System.out.println(newLocation.getX() + " " + newLocation.getY());
			g.move(newLocation);
			this.setChanged();
			this.notifyObservers();
			return true;
		}
		else {
			this.occupyOrRemoveO(g.getType(),oldLocation, true);
			return false;
		}		
	}

	public void gizmoPerformAction(Set<Character> keysPressed)
	{
		for(IGizmo gizmo:gizmos)
		{
			gizmo.addTick();
			if(keysPressed.contains((char)gizmo.getKeyConnect()))
			{
				gizmo.performAction();
				this.setChanged();
				this.notifyObservers();
			}
			
			else {
				gizmo.rollbackFlipper();
				this.setChanged();
				this.notifyObservers();
			}
		}
		
		
	}
	private void absorberPerformaAction(Set<Character>keysPressed,IAbsorber ab){
			if(keysPressed.contains((char) ab.getKeyConnect()))
			{
				this.ball.setVelocity(ab.launchBall());
				this.ball.start();
				this.setChanged();
				this.notifyObservers();
			}
	}
	@Override
	public void ballLaunchAbsorber(Set<Character>keysPressed,IAbsorber ab){
		absorberPerformaAction(keysPressed,ab);
	}
	@Override
	public void timeTick(Set<Character> keysPressed) {
		
		moveBall();
		
		gizmoPerformAction(keysPressed);

	}

     @Override
    public void clearModel() {
        List<Point> locs = new ArrayList<>();
        for (IGizmo g:gizmos) {
            locs.add(g.getLocation());
        }
        for (Point loc:locs) {
        	removeGizmo(loc);
        }
        for (IAbsorber a:absorbers) {
            locs.add(a.getTopLeftPoint());
        }
        for (Point loc:locs) {
        	removeAbsorber(loc);
        }
        removeBall();
        env = new EnvironmentInteraction();
    }

    @Override
    public void removeBall() {
        ball = null;
        this.setChanged();
        this.notifyObservers();
    }

}
