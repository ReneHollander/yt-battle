package at.er.ytbattle.plugin.event;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import at.er.ytbattle.plugin.BattlePlugin;
import at.er.ytbattle.plugin.player.BattlePlayer;
import at.er.ytbattle.plugin.team.Team;
import at.er.ytbattle.util.BattleUtils;

public class PlayerInteractListener implements Listener {

	public PlayerInteractListener() {
		Bukkit.getPluginManager().registerEvents(this, BattlePlugin.instance());
	}

	@EventHandler(priority = EventPriority.HIGH)
	public void onPlayerInteract(PlayerInteractEvent event) {
		BattlePlayer player = BattlePlugin.game().getBattlePlayerManager().getBattlePlayer(event.getPlayer());
		if ((event.getAction() == Action.RIGHT_CLICK_BLOCK || event.getAction() == Action.RIGHT_CLICK_AIR) && player.getItemInHand().getType() == Material.GHAST_TEAR && BattlePlugin.game().isStarted()) {
			if (BattlePlugin.game().getTeamManager().isInTeam(player) == false) {
				player.sendMessage(BattlePlugin.prefix() + "You left the battle, this item is worthless now!");
				return;
			}
			Team t = BattlePlugin.game().getTeamManager().getTeamByPlayer(player);
			if (t != null) {
				t.setLifes(t.getLifes() + 1);
				player.sendMessage(BattlePlugin.prefix() + t.getTeamColor().getLongName() + " team has now " + t.getLifes() + " lifes left!");
				player.playSound(player.getLocation(), Sound.LEVEL_UP, 10, 1);
			}
			if (player.getItemInHand().getAmount() > 1) {
				player.getItemInHand().setAmount(player.getItemInHand().getAmount() - 1);
			} else {
				player.getInventory().setItemInHand(new ItemStack(Material.AIR));
			}
			BattleUtils.updateScoreboard();
		}
	}

}
