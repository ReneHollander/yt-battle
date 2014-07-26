package at.er.ytbattle.battle.command.handler;

import org.bukkit.Bukkit;

import at.er.ytbattle.battle.Battle;
import at.er.ytbattle.battle.command.AbstractCommand;
import at.er.ytbattle.battle.player.BattlePlayer;

public class BattleCommandResume extends AbstractCommand {

    @Override
    public boolean onCommand(String label, String[] args, BattlePlayer player) {
        Battle.game().getTimerManager().resumeAllTimers();
        Bukkit.broadcastMessage(Battle.prefix() + "The game got resumed!");
        return true;
    }
}
