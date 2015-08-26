package at.er.ytbattle.plugin.command.handler;

import at.er.ytbattle.plugin.BattlePlugin;
import at.er.ytbattle.plugin.command.AbstractCommand;
import at.er.ytbattle.plugin.player.BattlePlayer;
import org.bukkit.Bukkit;

public class BattleCommandPause extends AbstractCommand {

    @Override
    public boolean onCommand(String label, String[] args, BattlePlayer player) {
        BattlePlugin.instance().pauseGame();
        Bukkit.broadcastMessage(BattlePlugin.prefix() + "The game got paused!");
        return true;
    }
}
