package at.er.ytbattle.plugin.command.handler;

import org.bukkit.ChatColor;

import at.er.ytbattle.plugin.BattlePlugin;
import at.er.ytbattle.plugin.command.AbstractCommand;
import at.er.ytbattle.plugin.player.BattlePlayer;
import at.er.ytbattle.plugin.team.Team;

public class BattleCommandList extends AbstractCommand {

	@Override
	public boolean onCommand(String label, String[] args, BattlePlayer player) {
		String list = "";
		for (Team t : BattlePlugin.game().getTeamManager().getTeams()) {
			for (BattlePlayer p : t.getPlayers()) {
				list = list + p.getName() + ", ";
			}
		}
		if (list.equals("")) {
			player.sendMessage(BattlePlugin.prefix() + "The Playerlist is empty!");
		} else {
			player.sendMessage(BattlePlugin.prefix() + "Battleplayers " + "[P] " + ChatColor.DARK_AQUA + "[S]" + ChatColor.GOLD + ": " + list.substring(0, list.lastIndexOf(',')));
		}
		return true;
	}

}
