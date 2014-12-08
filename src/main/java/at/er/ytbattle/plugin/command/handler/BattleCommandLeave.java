package at.er.ytbattle.plugin.command.handler;

import at.er.ytbattle.plugin.BattlePlugin;
import at.er.ytbattle.plugin.command.AbstractCommand;
import at.er.ytbattle.plugin.player.BattlePlayer;

public class BattleCommandLeave extends AbstractCommand {

	@Override
	public boolean onCommand(String label, String[] args, BattlePlayer player) {
		if (BattlePlugin.game().getTeamManager().isInTeam(player)) {
			player.setDisplayName(player.getName());
			player.setPlayerListName(player.getName());
			BattlePlugin.game().getTeamManager().removePlayerFromTeam(player);
			player.sendMessage(BattlePlugin.prefix() + "You have left the Battle");
			return true;
		} else {
			player.sendMessage(BattlePlugin.prefix() + "You havn't joined the Battle before");
			return true;
		}
	}
}
