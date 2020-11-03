package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import model.IModel;

public class SaveListener implements ActionListener{

    IModel model;
    public SaveListener(IModel model){
        this.model = model;
    }
    @Override
    public void actionPerformed(ActionEvent arg0) {
        JFileChooser fc = new JFileChooser();
        if (fc.showSaveDialog(new JOptionPane()) == JFileChooser.APPROVE_OPTION) {
            
            String path = fc.getSelectedFile().getPath();
            model.saveModel(path);
        }
        
    }

}
