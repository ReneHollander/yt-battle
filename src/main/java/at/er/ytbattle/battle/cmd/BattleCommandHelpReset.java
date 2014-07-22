package at.er.ytbattle.battle.cmd;

import java.io.File;

import org.bukkit.Bukkit;

import at.er.ytbattle.battle.Battle;
import at.er.ytbattle.battle.player.BattlePlayer;

public class BattleCommandHelpReset {

	public boolean onCmdHelp(String[] args, BattlePlayer player) {
		player.sendMessage(Battle.prefix() + "Battle Commands:  - <> = has to be attached [] = can be attached \n" + " - Alias for /battle is /b \n"
				+ " - /battle join <red;blue;green;yellow;purple;cyan;black;white> - Adds you to the attached Team \n" + " - /battle leave - Removes you from the Battle queue \n" + " - /battle start [graceperiod] - Starts the Battle \n"
				+ " - /battle life - Adds a live in form of a wool to your inventory \n" + " - /battle setspawn - Battlespawn will be set to you current location \n" + " - /battle spawn - Teleports you to spawn if set \n"
				+ " - /battle list - Lists all Battle players and spectators \n" + " - /battle stats <red;blue;green;yellow;purple;cyan;black;white> - Returns a summary of stats from the attached Team \n" + " - /battle reset - Resets the Battle\n"
				+ " - /battle resettimer - Resets the Youtube Remind Timer\n");
		return true;
	}

	public boolean onCmdReset(String[] args, BattlePlayer player) {

		Battle.instance().dontSave(true);

		Battle.instance().unsetTags();
		Battle.instance().updateScoreboard();

		File file = new File(Battle.instance().getDataFolder(), "battle.save");

		if (file.exists()) {
			if (!file.delete())
				file.deleteOnExit();
		}

		Bukkit.reload();

		player.sendMessage(Battle.prefix() + "The Battle was reloaded and the save file reset!");

		return true;
	}
}
