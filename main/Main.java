package main;

import javax.swing.UIManager;
import model.IModel;
import model.Model;
import view.IView;
import view.View;

public class Main {

	public static void main(String[] args) {
		try {
			// Use the platform look and feel
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			System.out.println("Look and Feel error in Main");
		}

		IModel model = new Model();
		IView view = new View(model);


	}
}
