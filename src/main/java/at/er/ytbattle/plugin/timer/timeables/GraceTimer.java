package at.er.ytbattle.plugin.timer.timeables;

import org.bukkit.Bukkit;
import org.bukkit.Sound;

import at.er.ytbattle.plugin.BattlePlugin;
import at.er.ytbattle.plugin.player.BattlePlayer;
import at.er.ytbattle.plugin.team.Team;

public class GraceTimer implements Runnable {

    private int time;

    public GraceTimer(int timeSec) {
        this.time = timeSec;

        Bukkit.getScheduler().scheduleSyncRepeatingTask(BattlePlugin.instance(), this, 0, 20);
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
            BattlePlugin.game().getSpawn().getLocation().getWorld().setPVP(true);
            for (Team t : BattlePlugin.game().getTeamManager().getTeams()) {
                for (BattlePlayer p : t.getPlayers()) {
                    p.playSound(p.getLocation(), Sound.AMBIENCE_THUNDER, 10, 1);
                }
            }
            Bukkit.broadcastMessage(BattlePlugin.prefix() + "The grace period has ended!");
        }

        if (time >= 0) {
            time = time - 1;
        }
    }

    private void note() {
        for (Team t : BattlePlugin.game().getTeamManager().getTeams()) {
            for (BattlePlayer p : t.getPlayers()) {
                p.playSound(p.getLocation(), Sound.NOTE_SNARE_DRUM, 10, 1);
            }
        }
    }

    private void broadcastTime() {
        if (time > 60) {
            Bukkit.broadcastMessage(BattlePlugin.prefix() + "The grace period will end in " + time / 60 + " Minutes");
        } else {
            Bukkit.broadcastMessage(BattlePlugin.prefix() + "The grace period will end in " + time + " Seconds");
        }
    }

    public int getTime() {
        return this.time;
    }

}
