package at.er.ytbattle.battle;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import at.er.ytbattle.battle.player.BattlePlayerManager;
import at.er.ytbattle.battle.timer.GraceTimer;
import at.er.ytbattle.util.SerializableLocation;

public class Game implements Externalizable {

	private TeamManager teamManager;

	private SerializableLocation spawn;

	private boolean started;
	private boolean saved;

	private GraceTimer graceTimer;

	public Game() {

	}

	public Game(Battle battle, TeamManager tm) {
		this.teamManager = tm;
		this.spawn = null;
		this.started = false;
		this.saved = false;
		this.graceTimer = null;
	}

	public TeamManager getTeamManager() {
		return this.teamManager;
	}

	public boolean isStarted() {
		return started;
	}

	public GraceTimer getGraceTimer() {
		return graceTimer;
	}

	public void setGraceTimer(GraceTimer graceTimer) {
		this.graceTimer = graceTimer;
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

	@Override
	public void writeExternal(ObjectOutput out) throws IOException {
		out.writeObject(BattlePlayerManager.instance());
		out.writeObject(this.teamManager);
		out.writeObject(this.spawn);
		out.writeBoolean(started);
		out.writeBoolean(saved);
	}

	@Override
	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		BattlePlayerManager.setInstance((BattlePlayerManager) in.readObject());
		this.teamManager = (TeamManager) in.readObject();
		this.spawn = (SerializableLocation) in.readObject();
		this.started = in.readBoolean();
		this.saved = in.readBoolean();
	}

}
