package at.er.ytbattle.plugin.command.handler;

import org.bukkit.Bukkit;

import at.er.ytbattle.plugin.BattlePlugin;
import at.er.ytbattle.plugin.command.AbstractCommand;
import at.er.ytbattle.plugin.player.BattlePlayer;

public class BattleCommandPause extends AbstractCommand {

    @Override
    public boolean onCommand(String label, String[] args, BattlePlayer player) {
        BattlePlugin.game().getTimerManager().pauseAllTimers();
        BattlePlugin.instance().saveGame();
        Bukkit.broadcastMessage(BattlePlugin.prefix() + "The game got paused!");
        return true;
    }
}
