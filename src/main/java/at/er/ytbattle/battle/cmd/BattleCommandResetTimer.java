package at.er.ytbattle.battle.cmd;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import at.er.ytbattle.battle.Battle;
import at.er.ytbattle.battle.player.BattlePlayer;
import at.er.ytbattle.battle.timer.RemindTimer;

public class BattleCommandResetTimer {

    public boolean onCmdResetTimer(String[] args, BattlePlayer player) {

        new RemindTimer();

        Bukkit.broadcastMessage(Battle.prefix() + ChatColor.RED + "Continuing the game! Have fun!");

        return true;
    }
}
