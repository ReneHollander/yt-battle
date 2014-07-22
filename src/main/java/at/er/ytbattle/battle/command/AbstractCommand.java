package at.er.ytbattle.battle.command;

import at.er.ytbattle.battle.player.BattlePlayer;

public abstract class AbstractCommand {

    public abstract boolean onCommand(String label, String[] args, BattlePlayer player);

}
