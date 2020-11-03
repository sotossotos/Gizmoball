package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import model.IModel;

public class LoadListener implements ActionListener 
{
	IModel model;
	public LoadListener(IModel model){
		this.model = model;
	}
	@Override
	public void actionPerformed(ActionEvent e)
	{
		JFileChooser fileChooser = new JFileChooser();
		if (fileChooser.showOpenDialog(new JOptionPane()) == JFileChooser.APPROVE_OPTION) {
		  String path =  fileChooser.getSelectedFile().getPath();
		  if (!model.loadModel(path)) {
		      JOptionPane.showMessageDialog(new JFrame(), "Errors exist in file. Faulty elements have been omitted.","Load Error(s)",JOptionPane.WARNING_MESSAGE);
		  }
		}		
	}
}
