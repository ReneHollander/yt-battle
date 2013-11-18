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
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class EditView extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JList<String> players;
	private DefaultListModel<String> playersModel;
	
	private EditControl l;
	
	private JTextField lifes;

	private JLabel lifesLabel;

	private JButton add;
	private JButton edit;
	private JButton del;
	private JButton save;
	private JButton saveLife;

	private GUIView view;
	private at.er.ytbattle.gui.TeamType type;

	public EditView(GUIView v, at.er.ytbattle.gui.TeamType red) {
		super("Edit Team " + red.getName());

		this.view = v;
		this.type = red;
		
		l = new EditControl(this);
		
		this.setSize(1000, 250);

		this.setSize(500, 250);
		this.setLocationRelativeTo(null);

		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		this.add = new JButton("Add Player to Team");
		this.edit = new JButton("Edit Player");
		this.del = new JButton("Remove Player from Team");
		this.save = new JButton("Save Team Options");
		
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

		add.addActionListener(l);
		edit.addActionListener(l);
		del.addActionListener(l);
		save.addActionListener(l);

		buttonContainer.add(add);
		buttonContainer.add(edit);
		buttonContainer.add(del);
		buttonContainer.add(save);
		
		if (view.getGame() != null) {
			switch(red.getId()) {
			case 1:
				for (String player : view.getGame().getRed().getPlayers()) {
					playersModel.addElement(player);
				}
				break;
			case 2:
				for (String player : view.getGame().getBlue().getPlayers()) {
					playersModel.addElement(player);
				}
				break;
			case 3:
				for (String player : view.getGame().getGreen().getPlayers()) {
					playersModel.addElement(player);
				}
				break;
			case 4:
				for (String player : view.getGame().getYellow().getPlayers()) {
					playersModel.addElement(player);
				}
				break;
			case 5:
				for (String player : view.getGame().getPurple().getPlayers()) {
					playersModel.addElement(player);
				}
				break;
			case 6:
				for (String player : view.getGame().getCyan().getPlayers()) {
					playersModel.addElement(player);
				}
				break;
			case 7:
				for (String player : view.getGame().getBlack().getPlayers()) {
					playersModel.addElement(player);
				}
				break;
			case 8:
				for (String player : view.getGame().getWhite().getPlayers()) {
					playersModel.addElement(player);
				}
				break;
			}
			
		}else JOptionPane.showMessageDialog(null, "null");
		
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

	public boolean checkForSave(ActionEvent e) {
		if (e.getSource() == save) return true;
		return false;
	}

	public boolean checkForSaveLifes(ActionEvent e) {
		if (e.getSource() == saveLife)
			return true;
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
