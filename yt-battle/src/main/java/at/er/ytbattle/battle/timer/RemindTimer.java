package at.er.ytbattle.battle.timer;

import java.io.Serializable;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import at.er.ytbattle.battle.Battle;

public class RemindTimer implements Runnable, Serializable {
	private static final long serialVersionUID = 1L;
	
	private int timeInMinutes = 0;

	public void run() {

		if (timeInMinutes % 15 == 0 && timeInMinutes > 0) {
			broadcastTime();
			note();
		}

		timeInMinutes += 1;
	}

	private void note() {
		for (Player player : Bukkit.getOnlinePlayers()) {
			player.playSound(player.getLocation(), Sound.NOTE_PLING, 10, 1);
		}
	}

	private void broadcastTime() {
		Bukkit.broadcastMessage(Battle.prefix() + ChatColor.DARK_RED
				+ "The battle is going on for " + timeInMinutes
				+ " minutes");
	}
}