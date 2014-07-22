package at.er.ytbattle.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

public class AddPlayerControl implements ActionListener {

    private AddPlayerView view;

    public AddPlayerControl(AddPlayerView v) {
        view = v;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (view.checkForAdd(e)) {
            String name = view.getNameField().getText();

            if (name.length() == 0) {
                view.dispose();
                return;
            }

            for (int i = 0; i < view.getView().getPlayerModel().getSize(); i++) {
                String s = view.getView().getPlayerModel().get(i);

                if (s.equalsIgnoreCase(name)) {
                    JOptionPane.showMessageDialog(view, "Name was already used!");
                    return;
                }
            }

            view.getView().getPlayersModel().addElement(name);
            view.dispose();
        }
    }
}
