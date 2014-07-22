package at.er.ytbattle.battle.cmd;

import at.er.ytbattle.battle.Battle;
import at.er.ytbattle.battle.player.BattlePlayer;
import at.er.ytbattle.util.SerializableLocation;

public class BattleCommandSetSpawn {

	public boolean onCmdSpawn(String[] args, BattlePlayer player) {
		if (Battle.instance().getGame().getSpawn() != null) {
			player.teleport(Battle.instance().getGame().getSpawn().getLocation());
		} else {
			player.sendMessage(Battle.prefix() + "Battlespawn hasn't been set yet!");
		}

		return true;
	}

	public boolean onCmdSetspawn(String[] args, BattlePlayer player) {
		Battle.instance().getGame().setSpawn(new SerializableLocation(player.getLocation()));
		Battle.instance().getGame().getSpawn().getLocation().getWorld()
				.setSpawnLocation((int) Battle.instance().getGame().getSpawn().getLocation().getX(), (int) Battle.instance().getGame().getSpawn().getLocation().getY(), (int) Battle.instance().getGame().getSpawn().getLocation().getZ());

		Battle.instance().getConfig().set("saves.spawn.world", player.getLocation().getWorld().getName());
		Battle.instance().getConfig().set("saves.spawn.x", player.getLocation().getX());
		Battle.instance().getConfig().set("saves.spawn.y", player.getLocation().getY());
		Battle.instance().getConfig().set("saves.spawn.z", player.getLocation().getZ());

		Battle.instance().saveConfig();

		player.sendMessage(Battle.prefix() + "Battlespawn has been set to your current location!");

		return true;
	}
}
