package at.er.ytbattle.plugin.player;

import org.bukkit.entity.Player;

public class PlayerUtil {

	public static boolean areEqual(Player p1, Player p2) {
		return p1.getUniqueId().equals(p2.getUniqueId());
	}

}
