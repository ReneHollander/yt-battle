package at.er.ytbattle.battle;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.ArrayList;
import java.util.UUID;

import at.er.ytbattle.battle.player.BattlePlayer;
import at.er.ytbattle.battle.player.BattlePlayerManager;
import at.er.ytbattle.battle.timer.BlockPlaceTimerManager;

public class Team implements Externalizable {

	private static final long serialVersionUID = -1836760149452108862L;

	private TeamColor teamColor;
	private ArrayList<BattlePlayer> players;
	private int lifes;
	private boolean lost;
	private BlockPlaceTimerManager bptm;

	public Team() {

	}

	public Team(Battle battle, TeamColor teamColor, ArrayList<BattlePlayer> players, int lifes) {
		this.teamColor = teamColor;
		this.players = players;
		this.lifes = lifes;
		this.lost = false;
		this.bptm = new BlockPlaceTimerManager(this, battle.getConfig().getInt("config.minutes-till-broken-wool-effects-appears") * 60);
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

	public BlockPlaceTimerManager getBlockPlaceTimerManager() {
		return this.bptm;
	}

	public boolean hasLost() {
		return this.lost;
	}

	public void setLost(boolean lost) {
		this.lost = lost;
	}

	@Override
	public void writeExternal(ObjectOutput out) throws IOException {
		out.writeObject(this.teamColor);
		out.writeInt(this.players.size());
		for (BattlePlayer p : this.players) {
			out.writeUTF(p.getUniqueId().toString());
		}
		out.writeInt(this.lifes);
		out.writeBoolean(this.lost);
		out.writeObject(this.bptm);
	}

	@Override
	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		this.teamColor = (TeamColor) in.readObject();
		int playerCount = in.readInt();
		this.players = new ArrayList<BattlePlayer>();
		for (int i = 0; i < playerCount; i++) {
			BattlePlayer p = BattlePlayerManager.instance().getBattlePlayer(UUID.fromString(in.readUTF()));
			this.players.add(p);
		}
		this.lifes = in.readInt();
		this.lost = in.readBoolean();
		this.bptm = (BlockPlaceTimerManager) in.readObject();
	}

}
