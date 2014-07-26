package at.er.ytbattle.battle.command.handler;

import org.bukkit.Bukkit;

import at.er.ytbattle.battle.Battle;
import at.er.ytbattle.battle.command.AbstractCommand;
import at.er.ytbattle.battle.player.BattlePlayer;

public class BattleCommandPause extends AbstractCommand {

    @Override
    public boolean onCommand(String label, String[] args, BattlePlayer player) {
        Battle.game().getTimerManager().pauseAllTimers();
        Battle.instance().saveGame();
        Bukkit.broadcastMessage(Battle.prefix() + "The game got paused!");
        return true;
    }
}
