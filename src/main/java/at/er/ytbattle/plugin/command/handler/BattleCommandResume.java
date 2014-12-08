package at.er.ytbattle.plugin.command.handler;

import org.bukkit.Bukkit;

import at.er.ytbattle.plugin.BattlePlugin;
import at.er.ytbattle.plugin.command.AbstractCommand;
import at.er.ytbattle.plugin.player.BattlePlayer;
import at.er.ytbattle.plugin.timer.timeables.BattleResumeCountdown;
import at.er.ytbattle.util.ConfigurationHelper;

public class BattleCommandResume extends AbstractCommand {

	@Override
	public boolean onCommand(String label, String[] args, BattlePlayer player) {
		if (BattlePlugin.configurationHelper().getConfigFile().getBoolean(ConfigurationHelper.MISC_STARTCOUNTDOWN_ENABLED_PATH)) {
			new BattleResumeCountdown(BattlePlugin.configurationHelper().getConfigFile().getInt(ConfigurationHelper.MISC_STARTCOUNTDOWN_DURATION_PATH));
		} else {
			BattlePlugin.instance().resumeGame();
			Bukkit.broadcastMessage(BattlePlugin.prefix() + "The game got resumed!");
		}
		return true;
	}
}
