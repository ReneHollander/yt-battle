package at.er.ytbattle.battle.command.handler;

import org.bukkit.ChatColor;

import at.er.ytbattle.battle.Battle;
import at.er.ytbattle.battle.Team;
import at.er.ytbattle.battle.TeamColor;
import at.er.ytbattle.battle.command.AbstractCommand;
import at.er.ytbattle.battle.player.BattlePlayer;

public class BattleCommandStats extends AbstractCommand {

    @Override
    public boolean onCommand(String label, String[] args, BattlePlayer player) {
        if (Battle.game().isStarted()) {
            String list = "";
            String winners = "";

            if (Battle.game().getTeamManager().getTeams().size() > 1) {
                if (args.length == 0) {
                    player.sendMessage(Battle.prefix() + "Please select a team: /battle stats <teamname>");
                } else {
                    Team t = Battle.game().getTeamManager().getTeam(TeamColor.getTeamByShortName(args[0].toLowerCase()));
                    if (t != null) {
                        for (BattlePlayer p : t.getPlayers()) {
                            list += p.getName() + " (" + p.getHealth() * 10.0 / 2.0 + "%), ";
                        }
                        if (list.length() > 0)
                            list = list.substring(0, list.lastIndexOf(','));
                        player.sendMessage(Battle.prefix() + t.getTeamColor().getChatColor() + t.getTeamColor().getLongName() + " Team:" + ChatColor.WHITE + "\n" + "Players: " + list + "\n" + "Lifes: " + t.getLifes() + "\n" + "Wools: " + t.getBlockPlaceTimerManager().getRemainingWoolCount());
                    } else {
                        player.sendMessage(Battle.prefix() + "Please select a team: /battle stats <teamname>");
                    }
                }
            } else {
                winners = Battle.game().getTeamManager().getLastTeam().getTeamColor().getLongName();
                player.sendMessage(Battle.prefix() + "The " + winners + " team has won the battle - Stats are disabled now!");
            }
        } else {
            player.sendMessage(Battle.prefix() + "Game is not running!");
        }
        return true;
    }

}
