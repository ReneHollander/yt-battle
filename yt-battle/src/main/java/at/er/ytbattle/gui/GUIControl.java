package at.er.ytbattle.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;

import at.er.ytbattle.battle.Game;
import at.er.ytbattle.gui.EditView.TeamType;
import at.er.ytbattle.util.ObjectSerialization;

public class GUIControl implements ActionListener, ListSelectionListener {

	private GUIView view;
	
	public GUIControl() {
		this.view = new GUIView(this);
	}
	
	public void valueChanged(ListSelectionEvent e) {
		if (!e.getValueIsAdjusting()) return;
		
		if (e.getSource() == view.getTeams()) {
			switch(view.getTeams().getSelectedValue()) {
			case "Team Red":
				new EditView(view, TeamType.RED);
				System.out.println("123");
				break;
			case "Team Blue":
				new EditView(view, TeamType.BLUE);
				break;
			case "Team Green":
				new EditView(view, TeamType.GREEN);
				break;
			case "Team Yellow":
				new EditView(view, TeamType.YELLOW);
				break;
			case "Team Purple":
				new EditView(view, TeamType.PURPLE);
				break;
			case "Team Cyan":
				new EditView(view, TeamType.CYAN);
				break;
			case "Team Black":
				new EditView(view, TeamType.BLACK);
				break;
			case "Team White":
				new EditView(view, TeamType.WHITE);
				break;
			}
		}
	}

	public void actionPerformed(ActionEvent e) {
		if (view.checkForOpen(e)) {
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setDialogTitle("Open Battle");
			fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
			
			FileNameExtensionFilter battleFilter = new FileNameExtensionFilter("Battle files", "save");
			
			fileChooser.setAcceptAllFileFilterUsed(false);
			fileChooser.addChoosableFileFilter(battleFilter);
			
			int userSelection = fileChooser.showOpenDialog(view);

			if (userSelection == JFileChooser.APPROVE_OPTION) {
				File fileToOpen = fileChooser.getSelectedFile();
				try {
					Game g = (Game) ObjectSerialization
							.fileToObject(fileToOpen);
					
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
			fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
			
			FileNameExtensionFilter battleFilter = new FileNameExtensionFilter("Battle files", "save");
			
			fileChooser.setAcceptAllFileFilterUsed(false);
			fileChooser.addChoosableFileFilter(battleFilter);
			
			int userSelection = fileChooser.showSaveDialog(view);

			if (userSelection == JFileChooser.APPROVE_OPTION) {
				
				if (fileChooser.getSelectedFile().getPath().endsWith("battle.save")) {
					File fileToSave = fileChooser.getSelectedFile();
					try {
						ObjectSerialization.objectToFile(view.getGame(), fileToSave);
					} catch (IOException ex) {
						ex.printStackTrace();
					}
				} else {
					File f = new File(fileChooser.getSelectedFile().getAbsolutePath() + ".save");
					try {
						ObjectSerialization.objectToFile(view.getGame(), f);
					} catch (IOException ex) {
						ex.printStackTrace();
					}
				}
			}
		}
	}
	
	public static void main(String[] args) {
		new GUIControl();
	}
}
