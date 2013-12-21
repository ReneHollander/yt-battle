package at.er.ytbattle.gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import at.er.ytbattle.battle.Team;

public class EditView extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JList<String> players;
	private DefaultListModel<String> playersModel;
	
	private Team data;
	
	private EditControl l;
	
	private JTextField lifes;

	private JLabel lifesLabel;

	private JButton add;
	private JButton del;
	private JButton save;

	private GUIView view;
	private at.er.ytbattle.gui.TeamType type;
	
	public EditView(GUIView v, at.er.ytbattle.gui.TeamType type) {
		super("Edit Team " + type.getName());

		this.view = v;
		this.type = type;
		
		l = new EditControl(this);
		
		this.setSize(500, 250);

		this.setLocationRelativeTo(null);

		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		this.add = new JButton("Add Player to Team");
		this.del = new JButton("Remove Player from Team");
		this.save = new JButton("Save and close");

		playersModel = new DefaultListModel<String>();
		players = new JList<String>(playersModel);

		lifes = new JTextField(3);
		lifesLabel = new JLabel("Teamlifes: ");

		Container life = new Container();
		life.setLayout(new BorderLayout());

		life.add(lifesLabel, BorderLayout.NORTH);
		life.add(lifes, BorderLayout.CENTER);

		Container buttonContainer = new Container();
		buttonContainer.setLayout(new FlowLayout());

		add.addActionListener(l);
		del.addActionListener(l);
		save.addActionListener(l);

		buttonContainer.add(add);
		buttonContainer.add(del);
		buttonContainer.add(save);
		
		if (view.getGame() != null) {
			switch(type.getId()) {
			case 1:
				for (String player : view.getGame().getRed().getPlayers()) {
					playersModel.addElement(player);
				}
				
				lifes.setText("" + view.getGame().getRed().getLifes());
				break;
			case 2:
				for (String player : view.getGame().getBlue().getPlayers()) {
					playersModel.addElement(player);
				}
				
				lifes.setText("" + view.getGame().getBlue().getLifes());
				break;
			case 3:
				for (String player : view.getGame().getGreen().getPlayers()) {
					playersModel.addElement(player);
				}
				
				lifes.setText("" + view.getGame().getGreen().getLifes());
				break;
			case 4:
				for (String player : view.getGame().getYellow().getPlayers()) {
					playersModel.addElement(player);
				}
				
				lifes.setText("" + view.getGame().getYellow().getLifes());
				break;
			case 5:
				for (String player : view.getGame().getPurple().getPlayers()) {
					playersModel.addElement(player);
				}
				
				lifes.setText("" + view.getGame().getPurple().getLifes());
				break;
			case 6:
				for (String player : view.getGame().getCyan().getPlayers()) {
					playersModel.addElement(player);
				}
				
				lifes.setText("" + view.getGame().getCyan().getLifes());
				break;
			case 7:
				for (String player : view.getGame().getBlack().getPlayers()) {
					playersModel.addElement(player);
				}
				
				lifes.setText("" + view.getGame().getBlack().getLifes());
				break;
			case 8:
				for (String player : view.getGame().getWhite().getPlayers()) {
					playersModel.addElement(player);
				}
				
				lifes.setText("" + view.getGame().getWhite().getLifes());
				break;
			}
			
		} else {
			JOptionPane.showMessageDialog(view, "No game data was found. Please load data from a file first.");
			return;
		}
		
		this.setLayout(new BorderLayout());

		this.add(players, BorderLayout.CENTER);
		this.add(life, BorderLayout.EAST);
		this.add(buttonContainer, BorderLayout.SOUTH);

		this.setVisible(true);
	}
	
	public void applyTeamData() {
		Team t = getTeam();
		
		t.setPlayers(new ArrayList<String>());
		
		for (int i = 0; i < playersModel.size(); i++) {
			t.getPlayers().add(playersModel.getElementAt(i));
		}
		
		t.setLifes(Integer.parseInt(lifesLabel.getText()));
	}
	
	public Team getTeam() {
		Team t = null;
		
		switch(type.getId()) {
		case 1:
			t = view.getGame().getRed();
			break;
		case 2:
			t = view.getGame().getBlue();
			break;
		case 3:
			t = view.getGame().getGreen();
			break;
		case 4:
			t = view.getGame().getYellow();
			break;
		case 5:
			t = view.getGame().getPurple();
			break;
		case 6:
			t = view.getGame().getCyan();
			break;
		case 7:
			t = view.getGame().getBlack();
			break;
		case 8:
			t = view.getGame().getWhite();
			break;
		}
		
		return t;
	}

	public boolean checkForAdd(ActionEvent e) {
		if (e.getSource() == add)
			return true;
		return false;
	}

	public boolean checkForRem(ActionEvent e) {
		if (e.getSource() == del)
			return true;
		return false;
	}

	public boolean checkForSave(ActionEvent e) {
		if (e.getSource() == save) return true;
		return false;
	}
	
	public at.er.ytbattle.gui.TeamType getTeamType() {
		return type;
	}

	public void setTeamType(at.er.ytbattle.gui.TeamType type) {
		this.type = type;
	}

	public JTextField getLifes() {
		return lifes;
	}

	public void setLifes(JTextField lifes) {
		this.lifes = lifes;
	}

	public GUIView getView() {
		return view;
	}

	public void setView(GUIView view) {
		this.view = view;
	}

	public DefaultListModel<String> getPlayersModel() {
		return playersModel;
	}

	public void setPlayersModel(DefaultListModel<String> playersModel) {
		this.playersModel = playersModel;
	}

	public JLabel getLifesLabel() {
		return lifesLabel;
	}

	public void setLifesLabel(JLabel lifesLabel) {
		this.lifesLabel = lifesLabel;
	}

	public enum TeamType {
		RED(1, "Red"), BLUE(2, "Blue"), GREEN(3, "Green"), YELLOW(4, "Yellow"), PURPLE(5, "Purple"), CYAN(6, "Cyan"), BLACK(7, "Black"), WHITE(8, "White");
		
		private int id;
		private String name;
		
		private TeamType(int id, String name) {
			this.id = id;
			this.name = name;
		}
		
		public int getId() {
			return id;
		}
		
		public String getName() {
			return name;
		}
	}
}
