package unittests;

import static org.junit.Assert.*;

import java.awt.Point;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import model.Constants;
import model.Model;

public class TestModel {
	
	Model model;

	@Before
	public void preTest(){
		model = new Model();
	}
	
	@Test
	public void testAddGizmo(){
		model.addGizmo(new Point(50,50), "square");
		assertEquals(model.getGizmos().size(),1);
		
		model.addGizmo(new Point(50,50), "square");
		assertEquals(model.getGizmos().size(),1);
	}
	
	@Test
	public void testAddBall(){
		model.addBall(new Point(100,100), 4);
		assertEquals(model.getBall().getPoint(),new Point(100,100));
		assertEquals(model.getBall().getRadius(),4,0);
	}
	
	@Test
	public void testMoveGizmo(){
		model.addGizmo(new Point(50,50), "square");
		model.getGizmoAtLocation(new Point(50,50)).setName("test");
		model.moveGizmo(new Point(50,50), new Point(100,100));
		assertEquals(model.getGizmo("test").getLocation(), new Point(100,100));
	}
	
	@Test
	public void testOccupancy(){
		model.addGizmo(new Point(50,50),"square");
		assertTrue(model.checkAlreadyOccupied(new Point(50,50)));
	}
	
	@Test 
	public void testRemoveGizmo(){
		model.addGizmo(new Point(0,0), "square");
		assertTrue(model.removeGizmo(new Point(0,0)));
		assertFalse(model.removeGizmo(new Point(300,300)));
	}
	
	@Test
	public void testGetGizmoAtLocNull(){
		assertEquals(model.getGizmoAtLocation(new Point(100,100)),null);
	}
	
	@Test
	public void testAddAbsorber(){
		model.addAbsorber(new Point(0,0), new Point(100,100));
		assertEquals(model.getAbsorbers().size(), 1);
	}
	
	@Test
	public void testRemoveAbsorber(){
		model.addAbsorber(new Point(0,0), new Point(100,100));
		model.removeAbsorber(new Point(0,0));
		assertEquals(model.getAbsorbers().size(), 0);
	}
	
	@Test
	public void testResetBall(){
		model.addBall(new Point(100,100), 50);
		model.getBall().setPoint(new Point(50,50));
		model.resetBall();
		assertEquals(model.getBall().getPoint(),new Point(100,100));
	}

	@Test
	public void testRemoveBall(){
		model.addBall(new Point(100,100), 50);
		model.removeBall();
		assertEquals(model.getBall(),null);
	}
	
	@Test
	public void testClearModel(){
		model.addGizmo(new Point(20,20), "triangle");
		model.addGizmo(new Point(100,100), "square");
		model.addAbsorber(new Point(40,40), new Point(60,60));
		model.addBall(new Point(0,0), 5);
		model.clearModel();
		assertEquals(model.getGizmos().size(),0);
		assertEquals(model.getBall(),null);
		assertEquals(model.getAbsorbers().size(),0);
	}
	
	
	@Test
	public void testRotate(){
		model.addGizmo(new Point(0,0), "triangle");
		
		assertFalse(model.rotateGizmo(new Point(100,100)));
		model.rotateGizmo(new Point(0,0));
		List<Point> points = model.getGizmoAtLocation(new Point(0,0)).getAllPoints();
		
		assertEquals(points.get(0),new Point(Constants.L,0));
		assertEquals(points.get(1),new Point(Constants.L,Constants.L));
		assertEquals(points.get(2),new Point(0,0));
	}
}
