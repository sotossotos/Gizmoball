package controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashSet;
import java.util.Set;

public class RunKeyListener implements KeyListener {

	private final Set<Character> keysPressed;

	public RunKeyListener() {
		keysPressed = new HashSet<Character>();
	}
	
	public Set<Character> getKeyPressed() {
		return keysPressed;
	}

	@Override
	public void keyPressed(KeyEvent e) {		
		keysPressed.add(e.getKeyChar());		
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		keysPressed.remove(e.getKeyChar());
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

}
