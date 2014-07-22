package at.er.ytbattle.battle.command.handler;

import at.er.ytbattle.battle.Battle;
import at.er.ytbattle.battle.command.AbstractCommand;
import at.er.ytbattle.battle.player.BattlePlayer;

public class BattleCommandSpawn extends AbstractCommand {

    @Override
    public boolean onCommand(String label, String[] args, BattlePlayer player) {
        if (Battle.instance().getGame().getSpawn() != null) {
            player.teleport(Battle.instance().getGame().getSpawn().getLocation());
        } else {
            player.sendMessage(Battle.prefix() + "Battlespawn hasn't been set yet!");
        }
        return true;
    }
}
