package at.er.ytbattle.battle.command.handler;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import at.er.ytbattle.battle.Battle;
import at.er.ytbattle.battle.Team;
import at.er.ytbattle.battle.TeamColor;
import at.er.ytbattle.battle.command.AbstractCommand;
import at.er.ytbattle.battle.player.BattlePlayer;

public class BattleCommandJoin extends AbstractCommand {

    @Override
    public boolean onCommand(String label, String[] args, BattlePlayer player) {
        if (Battle.instance().getGame().isStarted() == false) {
            if (args.length == 0 || args.length > 1) {
                player.sendMessage(Battle.prefix() + "Correct usage: /battle join <teamname>");
                return true;
            }
            TeamColor tc = TeamColor.getTeamByShortName(args[0]);
            if (tc != null) {
                Team t = Battle.instance().getGame().getTeamManager().getTeam(tc);
                Battle.instance().removeFromLists(player);
                t.addPlayer(player);
                Battle.instance().setDisplayAndListName(player);
                Bukkit.broadcastMessage(Battle.prefix() + "Player " + player.getName() + " joined the " + t.getTeamColor().getChatColor() + t.getTeamColor().getLongName() + ChatColor.RESET + " Team!");
                return true;
            } else {
                player.sendMessage(Battle.prefix() + "Correct usage: /battle join <teamname>");
                return true;
            }
        } else {
            player.sendMessage(Battle.prefix() + "Battle has already started!");
            return true;
        }
    }
}
