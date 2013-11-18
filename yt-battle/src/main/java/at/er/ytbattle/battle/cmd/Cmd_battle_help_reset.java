package at.er.ytbattle.battle.cmd;

import org.bukkit.entity.Player;

import at.er.ytbattle.battle.Battle;

public class Cmd_battle_help_reset {

	public Cmd_battle_help_reset(Cmd_battle c) {
	}

	public boolean onCmdHelp(String[] args, Player player) {
		player.sendMessage(Battle.prefix() + "Battle Commands:  - <> = has to be attached [] = can be attached \n" + " - Alias for /battle is /b \n"
				+ " - /battle join <red;blue;green;yellow;purple;cyan;black;white> - Adds you to the attached Team \n" + " - /battle leave - Removes you from the Battle queue \n" + " - /battle start [graceperiod] - Starts the Battle \n"
				+ " - /battle life - Adds a live in form of a wool to your inventory \n" + " - /battle setspawn - Battlespawn will be set to you current location \n" + " - /battle spawn - Teleports you to spawn if set \n"
				+ " - /battle list - Lists all Battle players and spectators \n" + " - /battle stats <red;blue;green;yellow;purple;cyan;black;white> - Returns a summary of stats from the attached Team \n"
				+ " - /battle spectate [tp;inv] [playername] - Marks you as Spectator \n" + " - /battle border <borderradius> - Creates a Glassborder with attached radius. WorldEdit has to be installed!" + " - /battle reset - Resets the Battle\n");
		return true;
	}

	public boolean onCmdReset(String[] args, Player player) {
		/*
		 * boolean old =
		 * plugin.getConfig().getBoolean("config.enable-automatic-load");
		 * 
		 * plugin.getConfig().set("config.enable-automatic-load", false);
		 * 
		 * Bukkit.reload();
		 * 
		 * File file = new File(plugin.getDataFolder(), "battle.save");
		 * 
		 * if (file.exists()) { if(!file.delete()) file.deleteOnExit(); }
		 * 
		 * plugin.getConfig().set("config.enable-automatic-load", old);
		 */

		player.sendMessage(Battle.prefix() + "WIP!");

		return true;
	}
}
