package at.er.ytbattle.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddPlayerControl implements ActionListener {

	private AddPlayerView view;

	public AddPlayerControl(AddPlayerView v) {
		view = v;
	}

	public void actionPerformed(ActionEvent e) {
		if (view.checkForAdd(e)) {
			view.getView().getPlayersModel().addElement(view.getNameField().getText());
			view.dispose();
		}
	}
}
