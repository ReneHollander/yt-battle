package at.er.ytbattle.plugin.command.handler;

import at.er.ytbattle.plugin.BattlePlugin;
import at.er.ytbattle.plugin.command.AbstractCommand;
import at.er.ytbattle.plugin.player.BattlePlayer;

public class BattleCommandSpawn extends AbstractCommand {

	@Override
	public boolean onCommand(String label, String[] args, BattlePlayer player) {
		if (BattlePlugin.game().getSpawn() != null && BattlePlugin.game().isStarted() == false) {
			player.teleport(BattlePlugin.game().getSpawn().getLocation());
		} else {
			player.sendMessage(BattlePlugin.prefix() + "Battlespawn hasn't been set yet!");
		}
		return true;
	}
}
