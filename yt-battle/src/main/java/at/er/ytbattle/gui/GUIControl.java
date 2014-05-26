package at.er.ytbattle.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;

import at.er.ytbattle.battle.Game;
import at.er.ytbattle.battle.TeamColor;
import at.rene8888.serilib.Deserialize;
import at.rene8888.serilib.Serialize;

public class GUIControl implements ActionListener, ListSelectionListener {

	private GUIView view;

	public GUIControl() {
		this.view = new GUIView(this);
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {

		if (!e.getValueIsAdjusting())
			return;

		if (e.getSource() == view.getTeams()) {
			if (view.getGame() == null) {
				JOptionPane.showMessageDialog(null, "No Game Data found!");
			} else {
				String color = view.getTeams().getSelectedValue().split(" ")[1];
				new EditView(view, view.getGame().getTeamManager().getTeam(TeamColor.getTeamByLongName(color)));
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (view.checkForOpen(e)) {
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setDialogTitle("Open Battle");
			fileChooser.setCurrentDirectory(new File("./"));

			FileNameExtensionFilter battleFilter = new FileNameExtensionFilter("Battle files", "save");

			fileChooser.setAcceptAllFileFilterUsed(false);
			fileChooser.addChoosableFileFilter(battleFilter);

			int userSelection = fileChooser.showOpenDialog(view);

			if (userSelection == JFileChooser.APPROVE_OPTION) {
				File fileToOpen = fileChooser.getSelectedFile();
				try {
					Game g = (Game) Deserialize.readFromFile(fileToOpen, false);
					JOptionPane.showMessageDialog(view, g.toString());
					view.setGame(g);
				} catch (ClassNotFoundException ex) {
					ex.printStackTrace();
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
		}

		if (view.checkForSave(e)) {
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setDialogTitle("Save Battle");
			fileChooser.setCurrentDirectory(new File("./"));

			FileNameExtensionFilter battleFilter = new FileNameExtensionFilter("Battle files", "save");

			fileChooser.setAcceptAllFileFilterUsed(false);
			fileChooser.addChoosableFileFilter(battleFilter);

			int userSelection = fileChooser.showSaveDialog(view);

			if (userSelection == JFileChooser.APPROVE_OPTION) {

				if (fileChooser.getSelectedFile().getPath().endsWith("battle.save")) {
					File fileToSave = fileChooser.getSelectedFile();
					try {
						Serialize.writeToFile(view.getGame(), fileToSave, false);
					} catch (IOException ex) {
						ex.printStackTrace();
					}
				} else {
					File f = new File(fileChooser.getSelectedFile().getAbsolutePath() + ".save");
					try {
						Serialize.writeToFile(view.getGame(), f, true);
					} catch (IOException ex) {
						ex.printStackTrace();
					}
				}
			}
		}
	}
}
