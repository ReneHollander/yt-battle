package at.er.ytbattle.plugin.timer.timeables;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

import at.er.ytbattle.plugin.BattlePlugin;

public class BattleStartCountdown extends BukkitRunnable {

    private int startCountdownDuration;
    private int graceTimeDuration;

    private int handle;
    private int counter;

    public BattleStartCountdown(int startCountdownDuration, int graceTimeDuration) {
        this.startCountdownDuration = startCountdownDuration;
        this.graceTimeDuration = graceTimeDuration;
        this.counter = 0;

        this.handle = Bukkit.getScheduler().scheduleSyncRepeatingTask(BattlePlugin.instance(), this, 0, 20);
    }

    @Override
    public void run() {
        if (counter >= startCountdownDuration) {
            Bukkit.broadcastMessage(BattlePlugin.prefix() + "The Battle has been started! Let the games begin!");
            BattlePlugin.instance().startGame(graceTimeDuration);
            Bukkit.getScheduler().cancelTask(this.handle);
        } else {
            Bukkit.broadcastMessage(BattlePlugin.prefix() + "The game starts in " + (startCountdownDuration - counter) + " seconds!");
        }
        counter++;
    }
}
