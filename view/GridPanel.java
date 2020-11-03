package view;


import java.awt.Graphics;

import model.Constants;


public class GridPanel extends GamePanel{
		
	public GridPanel() {
		super();
		repaint();
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		for(int i=1; i<20; i++) 
		{
			g.drawLine(i*Constants.L, 0, i*Constants.L, Constants.L*20);
			g.drawLine(0,  i*Constants.L, Constants.L*20, i*Constants.L);
		}
		super.paintGizmos(g);
	}

}
