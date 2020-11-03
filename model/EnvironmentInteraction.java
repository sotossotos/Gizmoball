package model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import physics.Angle;
import physics.Circle;
import physics.Geometry;
import physics.LineSegment;
import physics.Vect;

public class EnvironmentInteraction extends Observable{
	private Vect gravity;

	private double mu1 = Constants.MU1; 
	private double mu2 = Constants.MU2;
	
	public EnvironmentInteraction(){
		this.gravity = new Vect(Angle.DEG_270,Constants.DEFAULT_GRAVITY*Constants.L*Constants.tickTimer);
		this.mu1 = Constants.MU1*Constants.L;
		this.mu2 = Constants.MU2*Constants.tickTimer;
	}
	public boolean checkBallInsideAbsorber(IBall ball,IAbsorber ab) { 
		
			this.notInsideAbs(ab, ball);
			if(this.insideAbs(ab, ball)){
				ball.setPoint(ab.absorbBall(ball.getRadius()));
				ball.setVelocity(ab.launchBall());
				ball.stop();
				return true;
				}			
		
		return false;
	}
	
	
private List<Flipper>getAllMovingFlippers(List<IGizmo>gizmos){
	List<Flipper>flippers = new ArrayList<>();
	for(IGizmo gizmo:gizmos){
		if(gizmo.getType().toLowerCase().contains("flipper")){
			Flipper flipper = (Flipper) gizmo;
			if(flipper.isMoving())
				flippers.add(flipper);
		}
	}
	return flippers;
}
public boolean moveBall(IBall ball,List<IGizmo>gizmos,List<IAbsorber>abs,Walls wall) {
		
		double moveTime = Constants.tickTimer; // 0.05 = 20 times per second as per Gizmoball
		
		if (ball != null && ball.isStopped()) {
			for(IAbsorber ab:abs){
				checkBallInsideAbsorber(ball,ab);
			}
			}else if(ball!=null){
				    ball.start();
					CollisionDetails cd = this.timeUntilCollision(wall, gizmos ,getAllMovingFlippers(gizmos),ball.getCircle(),ball.getVelocity());
					double tuc = cd.getTuc();
					if (tuc > moveTime) {
						// No collision ...
						
						this.moveBallForTime(ball, moveTime);
						ball.setVelocity(ball.getVelocity().minus(this.gravity));
						
					} else {
						// We've got a collision in tuc
						this.moveBallForTime(ball, tuc);
						// Post collision velocity ...
						

						//get triggeredGizmo from the collision details
						IGizmo gizmo = cd.getTriggeredGizmo();
						if(gizmo != null) {
							//if(!gizmo.getType().toLowerCase().contains("flipper")) {
								gizmo.setTriggered(true);
								gizmo.performAction();
							//}
						}
						ball.setVelocity(cd.getVelo().minus(this.gravity));
						ball.setVelocity(this.getBallFriction(ball.getVelocity().x(), ball.getVelocity().y()));
					}
				}
		return true;
		}
	
	public boolean getWasOutSide(IAbsorber ab) {
			if(ab.getWasItemOuside()){
				return true;
			}
		return false;
	}

	public double getGravity(){
		System.out.println(this.gravity.length());
		return this.gravity.length()/Constants.L*Constants.tickTimer;
		
	}

	public boolean insideAbs( IAbsorber abs, IBall ball) {
		if(abs.getWasItemOuside()){
			if (abs.insideMe(ball.getPoint(), ball.getRadius()) && abs.getWasItemOuside()){
				abs.setWasItemOuside(false);
				return true;
			}
		}
		return false;
	}
	public boolean notInsideAbs(IAbsorber abs , IBall ball) {
		if (!abs.insideMe(ball.getPoint(), ball.getRadius())) {
			abs.setWasItemOuside(true);
			return true;
		}
		return false;
	}
	public void setGravity(double value){
		this.gravity = new Vect(Angle.DEG_270,value*Constants.L*Constants.tickTimer);
	}
	
	public Vect getBallFriction(double xvelocity, double yvelocity ) { 
		double friction = new Vect (xvelocity,yvelocity).length();
		friction = (1-mu1*Constants.FRICTION_DELTA-mu2*Math.abs(friction)*Constants.FRICTION_DELTA);
		System.out.println(this.mu1+"<-mu1  mu2->"+this.mu2);
		return new Vect(xvelocity*friction,yvelocity*friction);
	}
	
	public void setFriction(double mu1 ,double mu2) {
		this.mu1 = mu1;
		this.mu2 = mu2;
	}
    
    public double getMU1() {
        return mu1;
    }
    
    public double getMU2() {
        return mu2;
    }
	
	public void moveBallForTime(IBall ball, double time) {
		double newX = 0.0;
		double newY = 0.0;
		double xVel = ball.getVelocity().x();
		double yVel =ball.getVelocity().y();
		Point xy = ball.getPoint();
		newX = xy.getX() + (xVel * time);
		newY = xy.getY() + (yVel * time);
		ball.setPoint(new Point((int)newX,(int)newY));
	}
	public CollisionDetails timeUntilCollision(Walls wall, List<IGizmo> gizmos, List<Flipper>flippers ,Circle ballCircle ,Vect ballVelocity) {
		Vect newVelocity = new Vect(0,0);
		double shortestTime = Double.MAX_VALUE;
		double time = 0.0;
		IGizmo triggeredGizmo = null;
		
		for(LineSegment line:wall.getLineSegments())
		{
			time = Geometry.timeUntilWallCollision(line, ballCircle, ballVelocity);
			if (time < shortestTime) {
				shortestTime = time;
				newVelocity = Geometry.reflectWall(line, ballVelocity);
			}
		}
		
		for(IGizmo gizmo : gizmos)
		{
			List<LineSegment> lines = gizmo.getLines();
			List<Circle> circles = gizmo.getCorners();
					
			for (LineSegment line : lines) {
				time = Geometry.timeUntilWallCollision(line, ballCircle, ballVelocity);
				if (time < shortestTime) {
					triggeredGizmo = gizmo;
					shortestTime = time;
					newVelocity = Geometry.reflectWall(line, ballVelocity);
				}
			}
			
			for (Circle circle : circles) {
				time = Geometry.timeUntilCircleCollision(circle, ballCircle, ballVelocity);
				if (time < shortestTime) {
					triggeredGizmo = gizmo;
					shortestTime = time;
					newVelocity = Geometry.reflectCircle(circle.getCenter(), ballCircle.getCenter(), ballVelocity);
				}
			} 
		}
		
		for (Flipper f : flippers) {
			Angle rotationAngle=Angle.ZERO;
			if((f.getType().toLowerCase().equals("leftflipper") && f.isMovingUp()) || 
			   (f.getType().toLowerCase().equals("rightflipper") && f.isMovingDown()))
			{
				rotationAngle= new Angle(Constants.rotationAngleRad*60);
				time= Geometry.timeUntilRotatingCircleCollision(f.getCorners().get(1), f.getTopCircle(), rotationAngle.radians(), ballCircle, ballVelocity);
				if (time < shortestTime) {
					shortestTime = time;
					newVelocity = Geometry.reflectRotatingCircle(f.getCorners().get(1), f.getTopCircle(), 1080/180*0.05, ballCircle, ballVelocity);
				}
				time = Geometry.timeUntilRotatingWallCollision(f.getLines().get(0), f.getTopCircle(), rotationAngle.radians(), ballCircle, ballVelocity);
				if (time < shortestTime) {
					shortestTime = time;
					newVelocity = Geometry.reflectRotatingWall(f.getLines().get(0), f.getTopCircle(), 1080/180*0.05, ballCircle, ballVelocity);
				}
				time = Geometry.timeUntilRotatingWallCollision(f.getLines().get(1), f.getTopCircle(), rotationAngle.radians(), ballCircle, ballVelocity);
				if (time < shortestTime) {
					shortestTime = time;
					newVelocity = Geometry.reflectRotatingWall(f.getLines().get(1), f.getTopCircle(), 1080/180*0.05, ballCircle, ballVelocity);
				}
			}
			if((f.getType().toLowerCase().equals("leftflipper") && f.isMovingDown()) || 
			   (f.getType().toLowerCase().equals("rightflipper") && f.isMovingUp()))
			{
				rotationAngle= new Angle(-Constants.rotationAngleRad*60);
				time = Geometry.timeUntilRotatingCircleCollision(f.getCorners().get(1), f.getTopCircle(), rotationAngle.radians(), ballCircle, ballVelocity); 
				if (time < shortestTime) {
					shortestTime = time;
					newVelocity = Geometry.reflectRotatingCircle(f.getCorners().get(1), f.getTopCircle(), rotationAngle.radians()/180*0.05, ballCircle, ballVelocity);
				}
				time = Geometry.timeUntilRotatingWallCollision(f.getLines().get(0), f.getTopCircle(), rotationAngle.radians(), ballCircle, ballVelocity);
				if (time < shortestTime) {
					shortestTime = time;
					newVelocity = Geometry.reflectRotatingWall(f.getLines().get(0), f.getTopCircle(), rotationAngle.radians()/180*0.05, ballCircle, ballVelocity);
				}
				time = Geometry.timeUntilRotatingWallCollision(f.getLines().get(1), f.getTopCircle(), rotationAngle.radians(), ballCircle, ballVelocity);
				if (time < shortestTime) {
					shortestTime = time;
					newVelocity = Geometry.reflectRotatingWall(f.getLines().get(1), f.getTopCircle(), rotationAngle.radians()/180*0.05, ballCircle, ballVelocity);
				}
			}
		}

		CollisionDetails cd = new CollisionDetails(shortestTime ,newVelocity);
		cd.setTriggeredGizmo(triggeredGizmo);
		return cd;
		
	}


}
