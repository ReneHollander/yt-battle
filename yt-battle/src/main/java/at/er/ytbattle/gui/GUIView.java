package at.er.ytbattle.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;

import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.ListSelectionModel;

import at.er.ytbattle.battle.Game;

@SuppressWarnings("serial")
public class GUIView extends JFrame {
	
	private Game game;
	
	private GUIControl l;
	
	private JMenu file;
	
	private JMenuItem open;
	private JMenuItem save;
	
	private JList<String> teams;
	private DefaultListModel<String> teamModel;
	
	public GUIView(GUIControl l) {
		super("battle.save Editor by EXSolo");
		
		this.l = l;
		
		this.setSize(500, 200);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.setJMenuBar(getMenu());
		
		this.teamModel = new DefaultListModel<String>();
		this.teams = new JList<String>(teamModel);
		
		teams.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		teamModel.addElement("Team Red");
		teamModel.addElement("Team Blue");
		teamModel.addElement("Team Green");
		teamModel.addElement("Team Yellow");
		teamModel.addElement("Team Purple");
		teamModel.addElement("Team Cyan");
		teamModel.addElement("Team Black");
		teamModel.addElement("Team White");
		
		teams.setBorder(BorderFactory.createEtchedBorder(Color.WHITE, Color.GRAY));
		
		teams.addListSelectionListener(l);
		
		teams.setCellRenderer(new DefaultListCellRenderer() {
			public int getHorizontalAlignment() {
				return CENTER;
			}
		});
		
		this.setLayout(new BorderLayout());
		
		this.add(teams, BorderLayout.CENTER);
		
		this.setVisible(true);
	}
	
	private JMenuBar getMenu() {
		JMenuBar bar = new JMenuBar();
		
		file = new JMenu("File");
		
		open = new JMenuItem("Open");
		save = new JMenuItem("Save");
		
		file.add(open);
		file.add(save);
		
		open.addActionListener(l);
		save.addActionListener(l);
		
		bar.add(file);
		
		return bar;
	}
	
	public boolean checkForOpen(ActionEvent e) {
		if (e.getSource() == open) return true;
		return false;
	}
	
	public boolean checkForSave(ActionEvent e) {
		if (e.getSource() == save) return true;
		return false;
	}
	
	public Game getGame() {
		return game;
	}

	public JList<String> getTeams() {
		return teams;
	}

	public DefaultListModel<String> getTeamModel() {
		return teamModel;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public void setTeams(JList<String> teams) {
		this.teams = teams;
	}

	public void setTeamModel(DefaultListModel<String> teamModel) {
		this.teamModel = teamModel;
	}
}
