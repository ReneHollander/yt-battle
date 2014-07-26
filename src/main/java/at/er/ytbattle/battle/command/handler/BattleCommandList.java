package at.er.ytbattle.battle.command.handler;

import org.bukkit.ChatColor;

import at.er.ytbattle.battle.Battle;
import at.er.ytbattle.battle.Team;
import at.er.ytbattle.battle.command.AbstractCommand;
import at.er.ytbattle.battle.player.BattlePlayer;

public class BattleCommandList extends AbstractCommand {

    @Override
    public boolean onCommand(String label, String[] args, BattlePlayer player) {
        String list = "";
        for (Team t : Battle.game().getTeamManager().getTeams()) {
            for (BattlePlayer p : t.getPlayers()) {
                list = list + p.getName() + ", ";
            }
        }
        if (list.equals("")) {
            player.sendMessage(Battle.prefix() + "The Playerlist is empty!");
        } else {
            player.sendMessage(Battle.prefix() + "Battleplayers " + "[P] " + ChatColor.DARK_AQUA + "[S]" + ChatColor.GOLD + ": " + list.substring(0, list.lastIndexOf(',')));
        }
        return true;
    }

}
