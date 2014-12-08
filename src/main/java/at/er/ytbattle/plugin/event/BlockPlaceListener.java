package at.er.ytbattle.plugin.event;

import org.bukkit.Bukkit;
import org.bukkit.DyeColor;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.Wool;

import at.er.ytbattle.plugin.BattlePlugin;
import at.er.ytbattle.plugin.player.BattlePlayer;
import at.er.ytbattle.plugin.team.Team;
import at.er.ytbattle.util.BattleUtils;
import at.er.ytbattle.util.ConfigurationHelper;

public class BlockPlaceListener implements Listener {

	public BlockPlaceListener() {
		Bukkit.getPluginManager().registerEvents(this, BattlePlugin.instance());
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onBlockPlace(BlockPlaceEvent event) {
		BattlePlayer player = BattlePlugin.game().getBattlePlayerManager().getBattlePlayer(event.getPlayer());
		if (event.getBlock().getType() == Material.GLASS && player.getWorld() == BattlePlugin.game().getSpawn().getLocation().getWorld() && BattlePlugin.game().isStarted()) {
			event.setCancelled(true);
			player.sendMessage(BattlePlugin.prefix() + "You are unable to place a Block of the Bordermaterial.");
		}
		if (event.getBlock().getType() == Material.WOOL && BattlePlugin.game().isStarted() && BattlePlugin.game().getTeamManager().isInTeam(player)) {
			DyeColor color = ((Wool) event.getBlock().getState().getData()).getColor();
			Team t = BattlePlugin.game().getTeamManager().getTeamByPlayer(player);
			if (t.getTeamColor().getDyeColor().equals(color)) {
				boolean placed = this.placeWool(player, event.getBlock().getLocation(), color);
				if (placed == false) {
					player.sendMessage(BattlePlugin.prefix() + "Invalid Wool Location!");
					event.setCancelled(true);
				} else {
					t.getBlockPlaceTimerManager().woolPlace();
				}
			} else {
				player.sendMessage(BattlePlugin.prefix() + "You can't place other team's wool!");
				event.setCancelled(true);
				return;
			}
		}
		if (event.getBlock().getType() == Material.QUARTZ_ORE) {
			Location l = event.getBlock().getLocation();
			buildBase(l);
			if (player.getItemInHand().getAmount() > 1) {
				player.getItemInHand().setAmount(player.getItemInHand().getAmount() - 1);
			} else {
				player.getInventory().setItemInHand(new ItemStack(Material.AIR));
			}
		}
	}

	private boolean placeWool(Player p, Location l, DyeColor color) {

		int minHeight = BattlePlugin.configurationHelper().getConfigFile().getInt(ConfigurationHelper.GAME_WOOL_MINPLACEHEIGHT_PATH);

		if (l.getBlockY() < minHeight) {
			return false;
		} else {
			for (int i = l.getBlockY() + 1; i <= 255; i++) {
				Location now = l.clone();
				now.setY(i);
				Block b = now.getWorld().getBlockAt(now);
				if (b != null) {
					if (b.getType() != Material.AIR) {
						return false;
					}
				}
			}
		}

		World w = l.getWorld();

		int rad = BattlePlugin.configurationHelper().getConfigFile().getInt(ConfigurationHelper.GAME_WOOL_REMOVERADIUS_PATH);

		for (int x = -rad; x <= rad; x++) {
			for (int y = -rad; y <= rad; y++) {
				for (int z = -rad; z <= rad; z++) {
					Block b = w.getBlockAt((int) l.getX() + x, (int) l.getY() + y, (int) l.getZ() + z);
					if ((b.getType() != Material.WOOL) && (b.getType() != Material.BEDROCK) && (b.getType() != Material.GLASS)) {
						w.getBlockAt((int) l.getX() + x, (int) l.getY() + y, (int) l.getZ() + z).setType(Material.AIR);
						w.playEffect(new Location(l.getWorld(), (int) l.getX() + x, (int) l.getY() + y, (int) l.getZ() + z), Effect.MOBSPAWNER_FLAMES, 0);
					}
				}
			}
		}
		return true;
	}

	private void buildBase(Location l) {
		World w = l.getWorld();

		for (int x = -2; x <= 2; x++) {
			for (int y = 0; y <= 2; y++) {
				for (int z = -2; z <= 2; z++) {
					Material type = w.getBlockAt((int) l.getX() + x, (int) l.getY() + y, (int) l.getZ() + z).getType();
					if (type != Material.AIR && type != Material.IRON_ORE && type != Material.GOLD_ORE && type != Material.DIAMOND_ORE && type != Material.GLASS && type != Material.WOOL) {
						w.getBlockAt((int) l.getX() + x, (int) l.getY() + y, (int) l.getZ() + z).setType(Material.AIR);
						w.playEffect(new Location(l.getWorld(), (int) l.getX() + x, (int) l.getY() + y, (int) l.getZ() + z), Effect.MOBSPAWNER_FLAMES, 0);
					}
				}
			}
		}

		Location tmp = l;

		tmp.setZ(tmp.getZ() - 2);
		tmp.setX(tmp.getX() - 2);

		w.getBlockAt(tmp).setType(Material.CHEST);

		tmp.setZ(tmp.getZ() + 1);

		w.getBlockAt(tmp).setType(Material.CHEST);

		Chest c = (Chest) w.getBlockAt(tmp).getState();
		c.getInventory().setContents(BattleUtils.getStarterChestContents());
	}

}
