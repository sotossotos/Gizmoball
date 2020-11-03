package unittests;

import static org.junit.Assert.*;

import java.awt.Color;
import java.awt.Point;

import org.junit.Before;
import org.junit.Test;

import model.CircularBumper;
import model.Constants;
import model.IGizmo;
import model.SquareBumper;
import model.TriangularBumper;

public class TestGizmo {
	
	IGizmo circle;
	IGizmo triangle;
	IGizmo square;

	@Before
	public void preTest() {
		circle = new CircularBumper(new Point(0,0));
		triangle = new TriangularBumper(new Point(1*Constants.L,1*Constants.L));
		square = new SquareBumper(new Point(2*Constants.L,2*Constants.L));
	}
	
	@Test
	public void testGetSetName(){
		circle.setName("test");
		assertEquals(circle.getName(), "test");
	}
	
	@Test
	public void testGetSetColor(){
		triangle.setColor(Color.BLUE);
		assertEquals(triangle.getColor(),Color.BLUE);
	}
	
	@Test
	public void testGetLocation(){
		assertEquals(circle.getLocation(),new Point(0,0));
	}
	
	@Test
	public void testMove(){
		circle.move(new Point(10*Constants.L,10*Constants.L));
		assertEquals(circle.getLocation(),new Point(10*Constants.L,10*Constants.L));
		
		triangle.move(new Point(15*Constants.L,15*Constants.L));
		assertEquals(triangle.getLocation(),new Point(15*Constants.L,15*Constants.L));
		
		square.move(new Point(20*Constants.L,20*Constants.L));
		assertEquals(square.getLocation(), new Point(20*Constants.L,20*Constants.L));
	}
	
	@Test
	public void testAddConnected(){
		circle.addConnectedGizmo(square);
		assertTrue(circle.getConnectedGizmos().contains(square));
	}
	
	@Test
	public void testRemoveConnected(){
		circle.addConnectedGizmo(square);
		circle.removeConnectedGizmo(square);
		assertFalse(circle.getConnectedGizmos().contains(square));
	}
	
	@Test
	public void testGetType(){
		assertEquals(square.getType(), "Square");
		assertEquals(triangle.getType(), "Triangle");
		assertEquals(circle.getType(), "Circle");
	}
	
	@Test
	public void testGetSetKeyConnect(){
		square.setKeyConnect(10);
		assertEquals(square.getKeyConnect(),10);
	}
	
	
	@Test
	public void testTriggered(){
		square.setTriggered(true);
		assertTrue(square.isTriggered());
	}
	
	@Test
	public void testPerformAction(){
		square.setTriggerAction("changecolor", "red");
		square.performAction();
		assertEquals(square.getColor(), Color.RED);
	}
}
