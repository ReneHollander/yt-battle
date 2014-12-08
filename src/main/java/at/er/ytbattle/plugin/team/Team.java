package at.er.ytbattle.plugin.team;

import java.util.ArrayList;

import at.er.ytbattle.plugin.player.BattlePlayer;
import at.er.ytbattle.plugin.timer.manager.WoolPlaceTimerManager;

public class Team {

	private TeamColor teamColor;
	private ArrayList<BattlePlayer> players;
	private int lifes;
	private boolean lost;
	private WoolPlaceTimerManager bptm;

	public Team(TeamColor teamColor) {
		this.teamColor = teamColor;
		this.players = new ArrayList<BattlePlayer>();
		this.lifes = 0;
		this.lost = false;

		this.bptm = new WoolPlaceTimerManager(this);
	}

	public TeamColor getTeamColor() {
		return this.teamColor;
	}

	public ArrayList<BattlePlayer> getPlayers() {
		return players;
	}

	public void setPlayers(ArrayList<BattlePlayer> players) {
		this.players = players;
	}

	public boolean addPlayer(BattlePlayer player) {
		return this.players.add(player);
	}

	public boolean removePlayer(BattlePlayer player) {
		return this.players.remove(player);
	}

	public boolean containsPlayer(BattlePlayer player) {
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

	public WoolPlaceTimerManager getBlockPlaceTimerManager() {
		return this.bptm;
	}

	public boolean hasLost() {
		return this.lost;
	}

	public void setLost(boolean lost) {
		this.lost = lost;
	}

}
