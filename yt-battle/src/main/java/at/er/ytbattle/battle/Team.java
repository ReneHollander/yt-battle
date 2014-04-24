package at.er.ytbattle.battle;

import java.io.Serializable;
import java.util.ArrayList;

import at.er.ytbattle.battle.timer.BlockPlaceTimerManager;

public class Team implements Serializable {

	private static final long serialVersionUID = -1836760149452108862L;

	private TeamColor teamColor;
	private ArrayList<String> players;
	private int lifes;
	private boolean lost;
	private BlockPlaceTimerManager bptm;

	public Team(Battle battle, TeamColor teamColor, ArrayList<String> players, int lifes) {
		this.teamColor = teamColor;
		this.players = players;
		this.lifes = lifes;
		this.lost = false;
		this.bptm = new BlockPlaceTimerManager(battle, this, battle.getConfig().getInt("config.minutes-till-broken-wool-effects-appears") * 60);
	}

	public TeamColor getTeamColor() {
		return this.teamColor;
	}

	public ArrayList<String> getPlayers() {
		return players;
	}

	public void setPlayers(ArrayList<String> players) {
		this.players = players;
	}

	public boolean addPlayer(String player) {
		return this.players.add(player);
	}

	public boolean removePlayer(String player) {
		return this.players.remove(player);
	}

	public boolean containsPlayer(String player) {
		return this.players.contains(player);
	}

	public int getTeamSize() {
		return this.players.size();
	}

	public int getLifes() {
		return lifes;
	}

	public void setLifes(int lifes) {
		this.lifes = lifes;
	}

	public void setupInitialWool() {
		this.bptm.setupInitialWool();
	}

	public BlockPlaceTimerManager getBlockPlaceTimerManager() {
		return this.bptm;
	}

	public boolean hasLost() {
		return this.lost;
	}

	public void setLost(boolean lost) {
		this.lost = lost;
	}

}
