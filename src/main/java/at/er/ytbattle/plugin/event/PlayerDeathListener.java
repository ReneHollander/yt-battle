package at.er.ytbattle.plugin.event;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;

import at.er.ytbattle.plugin.BattlePlugin;
import at.er.ytbattle.plugin.player.BattlePlayer;
import at.er.ytbattle.plugin.team.Team;
import at.er.ytbattle.plugin.timer.timeables.FireworkTimer;
import at.er.ytbattle.util.BattleUtils;
import at.er.ytbattle.util.ConfigurationHelper;
import at.er.ytbattle.util.PlayerArmor;

public class PlayerDeathListener implements Listener {

	public PlayerDeathListener() {
		Bukkit.getPluginManager().registerEvents(this, BattlePlugin.instance());
	}

	@EventHandler(priority = EventPriority.HIGH)
	public void onPlayerDeath(PlayerDeathEvent event) {
		BattlePlayer player = BattlePlugin.game().getBattlePlayerManager().getBattlePlayer(event.getEntity());

		if (BattlePlugin.game().isStarted() && BattlePlugin.game().getTeamManager().isInTeam(player)) {
			Team t = BattlePlugin.game().getTeamManager().getTeamByPlayer(player);

			if (t.getLifes() > 0) {
				t.setLifes(t.getLifes() - 1);

				ItemStack helmet = player.getInventory().getHelmet();
				ItemStack chestplate = player.getInventory().getChestplate();
				ItemStack leggings = player.getInventory().getLeggings();
				ItemStack boots = player.getInventory().getBoots();

				if (helmet != null) {
					helmet = helmet.clone();
					if (helmet.getType() == Material.DIAMOND_HELMET)
						helmet = new ItemStack(Material.IRON_HELMET);
					helmet.setDurability((short) 0);
					BattleUtils.removeEnchants(helmet);
				}

				if (chestplate != null) {
					chestplate = chestplate.clone();
					if (chestplate.getType() == Material.DIAMOND_CHESTPLATE)
						chestplate = new ItemStack(Material.IRON_CHESTPLATE);
					chestplate.setDurability((short) 0);
					BattleUtils.removeEnchants(chestplate);
				}

				if (leggings != null) {
					leggings = leggings.clone();
					if (leggings.getType() == Material.DIAMOND_LEGGINGS)
						leggings = new ItemStack(Material.IRON_LEGGINGS);
					leggings.setDurability((short) 0);
					BattleUtils.removeEnchants(leggings);
				}

				if (boots != null) {
					boots = boots.clone();
					if (boots.getType() == Material.DIAMOND_BOOTS)
						boots = new ItemStack(Material.IRON_BOOTS);
					boots.setDurability((short) 0);
					BattleUtils.removeEnchants(boots);
				}

				PlayerArmor armor = new PlayerArmor(helmet, chestplate, leggings, boots);
				BattlePlugin.instance().playerArmor.put(player, armor);
			} else {
				player.teleport(BattlePlugin.game().getSpawn().getLocation());
				player.setDisplayName(player.getName());
				t.removePlayer(player);
				Bukkit.broadcastMessage(BattlePlugin.prefix() + player.getName() + " from the " + t.getTeamColor().getLongName() + " team has lost!");
				if (t.getTeamSize() == 0) {
					t.setLost(true);
					Bukkit.broadcastMessage(BattlePlugin.prefix() + "Team " + t.getTeamColor().getLongName() + " has lost!");
				}
			}

			Team lastTeam = BattlePlugin.game().getTeamManager().getLastTeam();
			if (lastTeam != null) {
				Bukkit.broadcastMessage(BattlePlugin.prefix() + "Team " + lastTeam.getTeamColor().getLongName() + " has won the Battle!");
				for (BattlePlayer p : BattlePlugin.game().getBattlePlayerManager().getAllBattlePlayers()) {
					p.teleport(BattlePlugin.game().getSpawn().getLocation());
					p.setAllowFlight(true);
					p.setFlying(true);
					p.setGameMode(GameMode.CREATIVE);
				}
				BattlePlugin.game().setStarted(false);
				FireworkTimer ft = new FireworkTimer();
				int id = Bukkit.getScheduler().scheduleSyncRepeatingTask(BattlePlugin.instance(), ft, 0, 20L);
				ft.setID(id);
				Bukkit.broadcastMessage(BattlePlugin.prefix() + "Thanks for playing! YT-Battle v" + BattleUtils.getShortVersion() + " made by EXSolo and Rene8888.");
			}

		}

		BattleUtils.updateScoreboard();

		if (BattlePlugin.configurationHelper().getConfigFile().getBoolean(ConfigurationHelper.MISC_AUTOREPSAWN_ENABLED_PATH)) {
			BattleUtils.respawnPlayer(player, BattlePlugin.configurationHelper().getConfigFile().getInt(ConfigurationHelper.MISC_AUTOREPSAWN_DELAY_PATH));
		}
	}
}
