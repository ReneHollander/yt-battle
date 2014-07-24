package at.er.ytbattle.battle.timer.timeables;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import at.er.ytbattle.battle.Battle;

public class RemindTimer extends Thread {
    private static RemindTimer REMIND_TIMER;

    private static final long every = 1000 * 60 * 15;

    private int timeInMinutes = 0;

    private long time = System.currentTimeMillis();

    private boolean running;

    public RemindTimer() {
        if (REMIND_TIMER != null) {
            getRT().stopTimer();
        }
        REMIND_TIMER = this;
        this.running = true;
        this.start();
    }

    @Override
    public void run() {
        while (running) {

            long diff = System.currentTimeMillis() - time;

            if (diff > every) {
                time = System.currentTimeMillis();

                timeInMinutes += 15;

                broadcastTime();
                note();
            }

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
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

    public void stopTimer() {
        this.running = false;
    }

    public static RemindTimer getRT() {
        return REMIND_TIMER;
    }
}