package at.er.ytbattle.battle;

import java.io.Serializable;
import java.util.ArrayList;

import at.er.ytbattle.battle.timer.GraceTimer;
import at.er.ytbattle.battle.timer.RemindTimer;
import at.er.ytbattle.util.SerializableLocation;

public class Game implements Serializable {
	private static final long serialVersionUID = 1L;

	private Battle plugin;

	private Team red;
	private Team blue;
	private Team green;
	private Team yellow;
	private Team purple;
	private Team cyan;
	private Team black;
	private Team white;

	private ArrayList<String> players;
	private ArrayList<String> spectators;
	private ArrayList<String> teams;

	private SerializableLocation spawn;

	private boolean started;
	private boolean saved;

	private GraceTimer graceTimer;
	private RemindTimer remindTimer;

	public Game(Battle b) {
		plugin = b;

		red = new Team(plugin);
		blue = new Team(plugin);
		green = new Team(plugin);
		yellow = new Team(plugin);
		purple = new Team(plugin);
		cyan = new Team(plugin);
		black = new Team(plugin);
		white = new Team(plugin);

		players = new ArrayList<String>();
		spectators = new ArrayList<String>();
		teams = new ArrayList<String>();

		spawn = null;

		started = false;
		saved = false;

		remindTimer = new RemindTimer();
		graceTimer = null;
	}

	public Team getRed() {
		return red;
	}

	public Team getBlue() {
		return blue;
	}

	public Team getGreen() {
		return green;
	}

	public Team getYellow() {
		return yellow;
	}

	public Team getPurple() {
		return purple;
	}

	public Team getCyan() {
		return cyan;
	}

	public Team getBlack() {
		return black;
	}

	public Team getWhite() {
		return white;
	}

	public void setRed(Team red) {
		this.red = red;
	}

	public void setBlue(Team blue) {
		this.blue = blue;
	}

	public void setGreen(Team green) {
		this.green = green;
	}

	public void setYellow(Team yellow) {
		this.yellow = yellow;
	}

	public void setPurple(Team purple) {
		this.purple = purple;
	}

	public void setCyan(Team cyan) {
		this.cyan = cyan;
	}

	public void setBlack(Team black) {
		this.black = black;
	}

	public void setWhite(Team white) {
		this.white = white;
	}

	public ArrayList<String> getPlayers() {
		return players;
	}

	public ArrayList<String> getSpectators() {
		return spectators;
	}

	public ArrayList<String> getTeams() {
		return teams;
	}

	public boolean isStarted() {
		return started;
	}

	public GraceTimer getGraceTimer() {
		return graceTimer;
	}

	public RemindTimer getRemindTimer() {
		return remindTimer;
	}

	public void setGraceTimer(GraceTimer graceTimer) {
		this.graceTimer = graceTimer;
	}

	public void setRemindTimer(RemindTimer remindTimer) {
		this.remindTimer = remindTimer;
	}

	public void setPlayers(ArrayList<String> players) {
		this.players = players;
	}

	public void setSpectators(ArrayList<String> spectators) {
		this.spectators = spectators;
	}

	public void setTeams(ArrayList<String> teams) {
		this.teams = teams;
	}

	public void setStarted(boolean started) {
		this.started = started;
	}

	public SerializableLocation getSpawn() {
		return spawn;
	}

	public boolean isSaved() {
		return saved;
	}

	public void setSaved(boolean saved) {
		this.saved = saved;
	}

	public void setSpawn(SerializableLocation spawn) {
		this.spawn = spawn;
	}

}
