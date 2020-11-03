package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

import model.Constants;
import model.IAbsorber;
import model.IModel;

/**
 * @author Murray Wood Demonstration of MVC and MIT Physics Collisions 2014
 */

public class PlayModeListener implements ActionListener {

	private Timer timer;
	private IModel model;
	private RunKeyListener keyListen;


	public PlayModeListener(IModel m, RunKeyListener keyListen) {
		this.keyListen = keyListen;
		model = m;
		timer = new Timer(Constants.timerMilliseconds, this);
	}

	@Override
	public final void actionPerformed( ActionEvent e) {
		
		for(IAbsorber ab:model.getAbsorbers()){
			if(model.getEnvironment().checkBallInsideAbsorber(model.getBall(), ab)){
		    	timer.start();
		    	model.ballLaunchAbsorber(keyListen.getKeyPressed(),ab);
		    }else if(!model.getEnvironment().getWasOutSide(ab)){
		    	model.ballLaunchAbsorber(keyListen.getKeyPressed(),ab);
	
		    }
		}
		if ( timer.isRunning()&& e.getSource()==timer) {
			model.timeTick(keyListen.getKeyPressed());
		} else{
			switch (e.getActionCommand()) {
			case "Start":
				timer.start();
				model.getBall().start();
				break;
			case "Pause":
				timer.stop();
				break;
			case "Reset":
				model.resetBall();
				break;
			case "Tick":
				model.timeTick(keyListen.getKeyPressed());
				break;
			case "Quit":
				System.exit(0);
				break;
			}
		
		}
	}
}
