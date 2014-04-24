package at.er.ytbattle.battle;

import java.io.Serializable;

import at.er.ytbattle.battle.timer.GraceTimer;
import at.er.ytbattle.util.SerializableLocation;

public class Game implements Serializable {

	private static final long serialVersionUID = 4886628307519507333L;

	private TeamManager teamManager;

	private SerializableLocation spawn;

	private boolean started;
	private boolean saved;

	private GraceTimer graceTimer;

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

}
