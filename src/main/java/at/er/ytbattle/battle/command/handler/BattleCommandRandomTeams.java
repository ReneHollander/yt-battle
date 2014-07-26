package at.er.ytbattle.battle.command.handler;

import java.util.ArrayList;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import at.er.ytbattle.battle.Battle;
import at.er.ytbattle.battle.Team;
import at.er.ytbattle.battle.command.AbstractCommand;
import at.er.ytbattle.battle.player.BattlePlayer;
import at.er.ytbattle.util.BattleUtils;

public class BattleCommandRandomTeams extends AbstractCommand {

    @Override
    public boolean onCommand(String label, String[] args, BattlePlayer bp) {
        if (Battle.instance().getGame().isStarted() == false) {
            ArrayList<Team> teams = Battle.instance().getGame().getTeamManager().getTeams();
            Random r = new Random();
            for (BattlePlayer player : Battle.instance().getGame().getBattlePlayerManager().getAllBattlePlayers()) {
                Team t = teams.get(r.nextInt(teams.size()));
                Battle.instance().getGame().getTeamManager().removePlayerFromTeam(player);
                t.addPlayer(player);
                BattleUtils.setDisplayAndListName(player);
                Bukkit.broadcastMessage(Battle.prefix() + "Player " + player.getName() + " joined the " + t.getTeamColor().getChatColor() + t.getTeamColor().getLongName() + ChatColor.RESET + " Team!");
            }
            return true;
        } else {
            bp.sendMessage(Battle.prefix() + "Battle has already started!");
            return true;
        }
    }
}
