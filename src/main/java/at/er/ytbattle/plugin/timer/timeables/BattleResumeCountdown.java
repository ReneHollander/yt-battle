package at.er.ytbattle.plugin.timer.timeables;

import org.bukkit.Bukkit;

import at.er.ytbattle.plugin.BattlePlugin;

public class BattleResumeCountdown implements Runnable {

	private int startCountdownDuration;

	private int handle;
	private int counter;

	public BattleResumeCountdown(int startCountdownDuration) {
		this.startCountdownDuration = startCountdownDuration;
		this.counter = 0;

		this.handle = Bukkit.getScheduler().scheduleSyncRepeatingTask(BattlePlugin.instance(), this, 0, 20);
	}

	@Override
	public void run() {
		if (counter >= startCountdownDuration) {
			Bukkit.broadcastMessage(BattlePlugin.prefix() + "The Battle has been resumed!");
			BattlePlugin.instance().resumeGame();
			Bukkit.getScheduler().cancelTask(this.handle);
		} else {
			Bukkit.broadcastMessage(BattlePlugin.prefix() + "The game resumes in " + (startCountdownDuration - counter) + " seconds!");
		}
		counter++;
	}
}
