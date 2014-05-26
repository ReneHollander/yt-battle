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
import javax.swing.WindowConstants;

import at.er.ytbattle.battle.Team;

public class EditView extends JFrame {

	private static final long serialVersionUID = 1L;

	private JList<String> players;
	private DefaultListModel<String> playersModel;

	private EditControl l;

	private JTextField lifes;

	private JLabel lifesLabel;

	private JButton add;
	private JButton del;
	private JButton save;

	private GUIView view;

	private Team t;

	public EditView(GUIView v, Team t) {
		super("Edit Team " + t.getTeamColor().getLongName());

		this.view = v;

		this.t = t;

		l = new EditControl(this);

		this.setSize(500, 250);

		this.setLocationRelativeTo(null);

		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

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

		// TODO fix player editor
		// for (String player : t.getPlayers()) {
		// playersModel.addElement(player);
		// }
		lifes.setText("" + t.getLifes());

		this.setLayout(new BorderLayout());

		this.add(players, BorderLayout.CENTER);
		this.add(life, BorderLayout.EAST);
		this.add(buttonContainer, BorderLayout.SOUTH);

		this.setVisible(true);
	}

	public void applyTeamData() {
		Team t = getTeam();

		// t.setPlayers(new ArrayList<String>());

		// for (int i = 0; i < playersModel.size(); i++) {
		// t.getPlayers().add(playersModel.getElementAt(i));
		// }

		t.setLifes(Integer.parseInt(lifes.getText()));
	}

	public Team getTeam() {
		return this.t;
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
		if (e.getSource() == save)
			return true;
		return false;
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

	public JList<String> getPlayers() {
		return this.players;
	}

	public DefaultListModel<String> getPlayerModel() {
		return playersModel;
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
