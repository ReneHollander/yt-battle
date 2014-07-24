package at.er.ytbattle.battle.command.handler;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import at.er.ytbattle.battle.Battle;
import at.er.ytbattle.battle.command.AbstractCommand;
import at.er.ytbattle.battle.player.BattlePlayer;
import at.er.ytbattle.battle.timer.timeables.RemindTimer;

public class BattleCommandResetTimer extends AbstractCommand {

    @Override
    public boolean onCommand(String label, String[] args, BattlePlayer player) {
        new RemindTimer();

        Bukkit.broadcastMessage(Battle.prefix() + ChatColor.RED + "Continuing the game! Have fun!");

        return true;
    }
}
