package at.er.ytbattle.battle.command.handler;

import at.er.ytbattle.battle.Battle;
import at.er.ytbattle.battle.command.AbstractCommand;
import at.er.ytbattle.battle.player.BattlePlayer;

public class BattleCommandLeave extends AbstractCommand {

    @Override
    public boolean onCommand(String label, String[] args, BattlePlayer player) {
        if (Battle.game().getTeamManager().isInTeam(player)) {
            player.setDisplayName(player.getName());
            player.setPlayerListName(player.getName());
            Battle.game().getTeamManager().removePlayerFromTeam(player);
            player.sendMessage(Battle.prefix() + "You have left the Battle");
            return true;
        } else {
            player.sendMessage(Battle.prefix() + "You havn't joined the Battle before");
            return true;
        }
    }
}
