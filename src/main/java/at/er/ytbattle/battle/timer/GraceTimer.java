package at.er.ytbattle.battle.timer;

import java.io.Serializable;

import org.bukkit.Bukkit;
import org.bukkit.Sound;

import at.er.ytbattle.battle.Battle;
import at.er.ytbattle.battle.Team;
import at.er.ytbattle.battle.player.BattlePlayer;

public class GraceTimer implements Runnable, Serializable {

	private static final long serialVersionUID = 1L;

	private int time;

	public GraceTimer() {
		this.time = -1;
	}

	public GraceTimer(int timeSec) {
		this.time = timeSec;
	}

	@Override
	public void run() {
		if (time >= 600 && time % 600 == 0) {
			broadcastTime();
		}
		if (time <= 300 && time % 60 == 0 && time > 60) {
			broadcastTime();
		}

		if (time <= 60 && time % 15 == 0 && time > 0) {
			broadcastTime();
		}

		if (time <= 10 && time > 0) {
			broadcastTime();
			if (time <= 3)
				note();
		}

		if (time == 0) {
			Battle.instance().getGame().getSpawn().getLocation().getWorld().setPVP(true);
			for (Team t : Battle.instance().getGame().getTeamManager().getTeams()) {
				for (BattlePlayer p : t.getPlayers()) {
					p.playSound(p.getLocation(), Sound.AMBIENCE_THUNDER, 10, 1);
				}
			}
			Bukkit.broadcastMessage(Battle.prefix() + "The grace period has ended!");
		}

		if (time >= 0) {
			time = time - 1;
		}
	}

	private void note() {
		for (Team t : Battle.instance().getGame().getTeamManager().getTeams()) {
			for (BattlePlayer p : t.getPlayers()) {
				p.playSound(p.getLocation(), Sound.NOTE_SNARE_DRUM, 10, 1);
			}
		}
	}

	private void broadcastTime() {
		if (time > 60) {
			Bukkit.broadcastMessage(Battle.prefix() + "The grace period will end in " + time / 60 + " Minutes");
		} else {
			Bukkit.broadcastMessage(Battle.prefix() + "The grace period will end in " + time + " Seconds");
		}
	}

	public int getTime() {
		return this.time;
	}

}
