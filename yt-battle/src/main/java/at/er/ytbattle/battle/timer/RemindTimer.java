package at.er.ytbattle.battle.timer;

import java.io.Serializable;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import at.er.ytbattle.battle.Battle;

public class RemindTimer extends Thread implements Serializable {
	private static final long serialVersionUID = 1L;

	private static final long every = 1000 * 60 * 15;

	private int timeInMinutes = 0;

	public RemindTimer() {
		this.start();
	}

	public void run() {

		long time = System.currentTimeMillis();

		while (true) {

			long diff = System.currentTimeMillis() - time;

			if (diff > every) {
				time = System.currentTimeMillis();

				timeInMinutes += 15;

				broadcastTime();
				note();
			}
		}
	}

	private void note() {
		for (Player player : Bukkit.getOnlinePlayers()) {
			player.playSound(player.getLocation(), Sound.NOTE_PLING, 10, 1);
		}
	}

	private void broadcastTime() {
		Bukkit.broadcastMessage(Battle.prefix() + ChatColor.DARK_RED + "The battle is going on for " + timeInMinutes + " minutes");
	}
}