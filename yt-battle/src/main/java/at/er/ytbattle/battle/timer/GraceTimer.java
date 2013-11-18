package at.er.ytbattle.battle.timer;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import at.er.ytbattle.battle.Battle;

public class GraceTimer implements Runnable {

	private Battle plugin;

	private int time;

	public GraceTimer(Battle plugin) {
		this.plugin = plugin;
		this.time = -1;
	}

	public GraceTimer(Battle plugin, int timeSec) {
		this.plugin = plugin;
		this.time = timeSec;
	}

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
			plugin.getGame().getSpawn().getLocation().getWorld().setPVP(true);
			for (String s : plugin.getGame().getPlayers()) {
				Player player = Bukkit.getPlayer(s);

				player.playSound(player.getLocation(), Sound.AMBIENCE_THUNDER, 10, 1);
			}
			Bukkit.broadcastMessage(Battle.prefix() + "The grace period has ended!");
		}

		if (time >= 0) {
			time = time - 1;
		}
	}

	private void note() {
		for (String s : plugin.getGame().getPlayers()) {
			Player player = Bukkit.getPlayer(s);

			player.playSound(player.getLocation(), Sound.NOTE_SNARE_DRUM, 10, 1);
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
