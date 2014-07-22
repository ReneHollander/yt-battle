package at.er.ytbattle.battle.timer;

import java.io.Serializable;

import org.bukkit.Bukkit;

import at.er.ytbattle.battle.Battle;
import at.er.ytbattle.battle.Team;

public class BlockPlaceTimer implements Runnable, Serializable {
    private static final long serialVersionUID = 1L;

    // private Team t;

    private int handleID;

    // private int initTime;
    private int time;

    public BlockPlaceTimer(Battle b, Team t, int time) {
        // this.t = t;

        this.time = time;
        // this.initTime = time;

        this.handleID = Bukkit.getScheduler().scheduleSyncRepeatingTask(b, this, 0L, 20L);

    }

    @Override
    public void run() {
        // if (time == 0) {
        // for (String s : t.getPlayers()) {
        // Player p = Bukkit.getPlayer(s);
        //
        // p.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 120, 0));
        // p.sendMessage(Battle.prefix() +
        // "Place a wool to disable this effect");
        //
        // Bukkit.getScheduler().cancelTask(this.handleID);
        //
        // }
        // }
        //
        // if (time == this.initTime) {
        // for (String s : t.getPlayers()) {
        // Player p = Bukkit.getPlayer(s);
        //
        // p.sendMessage(Battle.prefix() + "You have " + time / 60 +
        // " minutes left to place a wool.");
        // }
        // }
        //
        // if (time % 600 == 0 && time >= 600) {
        // for (String s : t.getPlayers()) {
        // Player p = Bukkit.getPlayer(s);
        //
        // p.sendMessage(Battle.prefix() + "You have " + time / 60 +
        // " minutes left to place a wool.");
        // }
        // }
        //
        // if (time % 60 == 0 && time >= 60 && time < 600) {
        // for (String s : t.getPlayers()) {
        // Player p = Bukkit.getPlayer(s);
        //
        // p.sendMessage(Battle.prefix() + "You have " + time / 60 +
        // " minutes left to place a wool.");
        // }
        // }
        //
        // if (time % 10 == 0 && time > 0 && time < 60) {
        // for (String s : t.getPlayers()) {
        // Player p = Bukkit.getPlayer(s);
        //
        // p.sendMessage(Battle.prefix() + "You have " + time +
        // " seconds left to place a wool.");
        // }
        // }
        //
        // if (time > 0)
        // time--;
    }

    public void stopCountdown() {
        Bukkit.getScheduler().cancelTask(this.handleID);
    }

    public int getTime() {
        return this.time;
    }

}
