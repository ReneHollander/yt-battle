package at.er.ytbattle.gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextField;

public class EditView extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JList<String> players;
	private DefaultListModel<String> playersModel;

	private JTextField lifes;

	private JLabel lifesLabel;

	private JButton add;
	private JButton edit;
	private JButton del;
	private JButton saveLife;

	private GUIView view;
	private TeamType type;

	public EditView(GUIView v, TeamType type) {
		super("Edit Team " + type.getName());

		this.view = v;
		this.type = type;

		this.setSize(500, 250);
		this.setLocationRelativeTo(null);

		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		this.add = new JButton("Add Player to Team");
		this.edit = new JButton("Edit Player");
		this.del = new JButton("Remove Player from Team");
		this.saveLife = new JButton("Save Lifes");

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

		buttonContainer.add(add);
		buttonContainer.add(edit);
		buttonContainer.add(del);
		buttonContainer.add(saveLife);

		this.setLayout(new BorderLayout());

		this.add(players, BorderLayout.CENTER);
		this.add(life, BorderLayout.EAST);
		this.add(buttonContainer, BorderLayout.SOUTH);

		this.setVisible(true);
	}

	public boolean checkForAdd(ActionEvent e) {
		if (e.getSource() == add)
			return true;
		return false;
	}

	public boolean checkForEdit(ActionEvent e) {
		if (e.getSource() == edit)
			return true;
		return false;
	}

	public boolean checkForRem(ActionEvent e) {
		if (e.getSource() == del)
			return true;
		return false;
	}

	public boolean checkForSaveLifes(ActionEvent e) {
		if (e.getSource() == saveLife)
			return true;
		return false;
	}

	public TeamType getTeamType() {
		return type;
	}

	public void setTeamType(TeamType type) {
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
}
