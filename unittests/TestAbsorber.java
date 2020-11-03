package unittests;

import static org.junit.Assert.*;

import java.awt.Color;
import java.awt.Point;

import org.junit.Before;
import org.junit.Test;

import model.Absorber;
import model.Ball;
import model.Constants;

public class TestAbsorber {

	private Ball ball;
	private Absorber abs;

	@Before
	public void preTest() {
		abs = new Absorber(new Point(3,10*Constants.L), new Point(10*Constants.L,20*Constants.L));
		ball = new Ball(new Point(5*Constants.L,5*Constants.L), 5);
	
	}
	
	@Test
	public void testInsideMe(){
		
		ball.setPoint(new Point(5*Constants.L,15*Constants.L));
		assertTrue(abs.insideMe(ball.getPoint(), ball.getRadius()));
		
		ball.setPoint(new Point(2*Constants.L,5*Constants.L));
		assertFalse(abs.insideMe(ball.getPoint(), ball.getRadius()));
	}
	
	@Test
	public void testAbsorbBall(){
		ball.setPoint(abs.absorbBall(ball.getRadius()));
		assertEquals(new Point((int)abs.getBotRightPoint().getX()-(int)ball.getRadius(), (int)abs.getBotRightPoint().getY() - (int)ball.getRadius()),ball.getPoint());
	}
	
	@Test
	public void testSetGetName(){
		abs.setName("test");
		assertEquals(abs.getName(),"test");
	}
	
	@Test
	public void testSetGetColor(){
		abs.setColor(Color.RED);
		assertEquals(abs.getColor(),Color.RED);
	}
	
	@Test
	public void testSetGetTopLeft(){
		abs.setTopLeftPoint(new Point(0,0));
		assertEquals(abs.getTopLeftPoint(), new Point(0,0));
	}
	
	@Test
	public void testSetGetBotRight(){
		abs.setBotRightPoint(new Point(100,100));
		assertEquals(abs.getBotRightPoint(), new Point(100,100));
	}
	
	@Test
	public void testSetGetKeyConnect(){
		abs.setKeyConnect(10);
		assertEquals(abs.getKeyConnect(),10);
	}
	

}
