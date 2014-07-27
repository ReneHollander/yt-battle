package at.er.ytbattle.plugin.command;

import at.er.ytbattle.plugin.player.BattlePlayer;

public abstract class AbstractCommand {

    public abstract boolean onCommand(String label, String[] args, BattlePlayer player);

}
