package at.er.ytbattle.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

public class EditControl implements ActionListener {

	private EditView view;
	
	private EditControl(EditView v) {
		view = v;
	}
	
	public void actionPerformed(ActionEvent e) {
		if (view.checkForSaveLifes(e)) {
			String l = view.getLifes().getText();
			int lifes = 0;
			
			try {
				lifes = Integer.parseInt(l);
			} catch(Exception ex) {
				JOptionPane.showMessageDialog(view, "Error on saving lifes: Text is not a number!");
			}
		}
	}

}
