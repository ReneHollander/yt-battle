package at.er.ytbattle.plugin.command.handler;

import org.bukkit.Bukkit;

import at.er.ytbattle.plugin.BattlePlugin;
import at.er.ytbattle.plugin.command.AbstractCommand;
import at.er.ytbattle.plugin.player.BattlePlayer;

public class BattleCommandResume extends AbstractCommand {

    @Override
    public boolean onCommand(String label, String[] args, BattlePlayer player) {
        BattlePlugin.instance().resumeGame();
        Bukkit.broadcastMessage(BattlePlugin.prefix() + "The game got resumed!");
        return true;
    }
}
