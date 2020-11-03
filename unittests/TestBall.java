package unittests;

import static org.junit.Assert.*;

import java.awt.Color;
import java.awt.Point;

import org.junit.Before;
import org.junit.Test;

import model.Ball;
import model.Constants;
import model.IBall;
import physics.Circle;
import physics.Vect;

public class TestBall {
	
	Ball ball;

	@Before
	public void preTest() {
		ball = new Ball(new Point(5*Constants.L,5*Constants.L), 5);
	}
	
	@Test 
	public void testReset() {
		ball.setPoint(new Point(50,50));
		ball.reset();
		assertEquals(ball.getPoint(),new Point(5*Constants.L,5*Constants.L));
	}
	
	@Test
	public void testGetSetPoint() {
		ball.setPoint(new Point(50,50));
		assertEquals(ball.getPoint(),new Point(50,50));
	}
	
	@Test
	public void testGetSetVelocity(){
		ball.setVelocity(new Vect(2,2));
		assertEquals(ball.getVelocity(), new Vect(2,2));
	}
	
	@Test
	public void testGetColor(){
		assertEquals(ball.getColor(), Color.BLUE);
	}
	
	@Test
	public void testStart(){
		ball.start();
		assertFalse(ball.isStopped());
	}
	
	@Test
	public void testStop(){
		ball.stop();
		assertTrue(ball.isStopped());
	}

	@Test 
	public void testGetRadius(){
		assertEquals((int)ball.getRadius(),5);
	}
	
	@Test
	public void testGetCircle(){
		int x = (int)ball.getExactX();
		int y = (int)ball.getExactY();
		int r = (int)ball.getRadius();
		
		Circle circle = ball.getCircle();
		assertEquals(new Vect(x,y),circle.getCenter());
		assertEquals(r,(int)circle.getRadius());
	}
}
