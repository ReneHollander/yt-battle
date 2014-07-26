package at.er.ytbattle.battle.command.handler;

import at.er.ytbattle.battle.Battle;
import at.er.ytbattle.battle.command.AbstractCommand;
import at.er.ytbattle.battle.player.BattlePlayer;

public class BattleCommandSpawn extends AbstractCommand {

    @Override
    public boolean onCommand(String label, String[] args, BattlePlayer player) {
        if (Battle.game().getSpawn() != null && Battle.game().isStarted() == false) {
            player.teleport(Battle.game().getSpawn().getLocation());
        } else {
            player.sendMessage(Battle.prefix() + "Battlespawn hasn't been set yet!");
        }
        return true;
    }
}
