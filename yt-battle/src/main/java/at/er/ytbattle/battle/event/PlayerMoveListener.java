package at.er.ytbattle.battle.event;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.Wool;

import at.er.ytbattle.battle.Battle;
import at.er.ytbattle.battle.Team;
import at.er.ytbattle.battle.player.BattlePlayer;
import at.er.ytbattle.battle.player.BattlePlayerManager;

public class PlayerMoveListener implements Listener {

	public PlayerMoveListener() {
		Bukkit.getPluginManager().registerEvents(this, Battle.instance());
	}

	@EventHandler
	public void onPlayerMove(PlayerMoveEvent event) {
		BattlePlayer player = BattlePlayerManager.instance().getBattlePlayer(event.getPlayer());

		if (Battle.instance().getGame().isStarted()) {
			if (Battle.instance().getGame().getTeamManager().isInTeam(player)) {
				if (player.getInventory().contains(Material.WOOL)) {

					Team t = Battle.instance().getGame().getTeamManager().getTeamByPlayer(player);

					boolean found = false;

					for (ItemStack is : player.getInventory().getContents()) {
						if (is != null) {
							if (is.getType() == Material.WOOL) {
								DyeColor curr = ((Wool) is.getData()).getColor();
								if (curr == t.getTeamColor().getDyeColor()) {
									found = true;
								}
							}
						}
					}
					if (!found) {
						player.sendMessage(Battle.prefix() + ChatColor.RED + "Your Inventory has to contain a wool of your color! Get another with /b life!");
					}
				} else {
					player.sendMessage(Battle.prefix() + ChatColor.RED + "Your Inventory has to contain a wool of your color! Get another with /b life!");
				}
			}
		}

	}

}
