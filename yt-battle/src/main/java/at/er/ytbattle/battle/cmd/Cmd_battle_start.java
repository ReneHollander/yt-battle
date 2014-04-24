package at.er.ytbattle.battle.cmd;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.Wool;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import at.er.ytbattle.battle.Battle;
import at.er.ytbattle.battle.Team;
import at.er.ytbattle.battle.TeamManager;
import at.er.ytbattle.battle.timer.GraceTimer;
import at.er.ytbattle.util.SerializableLocation;

public class Cmd_battle_start {

	private Cmd_battle cmd;

	private TeamManager teamManager;

	public Cmd_battle_start(Cmd_battle c) {
		cmd = c;
		this.teamManager = cmd.getPlugin().getGame().getTeamManager();
	}

	public boolean onCmdStart(String[] args, Player player) {

		Battle plugin = cmd.getPlugin();

		int timer = 0;

		int startlifes = plugin.getConfig().getInt("config.lifes-at-start");
		boolean reminder = plugin.getConfig().getBoolean("config.enable-remind-scheduler");

		for (Team t : this.teamManager.getTeams()) {
			if (t.getTeamSize() > 0) {
				t.setLifes(startlifes);
			}
		}

		if (plugin.getGame().isStarted() == false) {
			if (this.teamManager.getTeams().size() > 1) {
				if (plugin.getGame().getSpawn() == null) {
					plugin.getGame().setSpawn(new SerializableLocation(player.getLocation()));
					plugin.getGame().getSpawn().getLocation().getWorld()
							.setSpawnLocation((int) plugin.getGame().getSpawn().getLocation().getX(), (int) plugin.getGame().getSpawn().getLocation().getY(), (int) plugin.getGame().getSpawn().getLocation().getZ());
					plugin.getConfig().set("saves.spawn.world", player.getLocation().getWorld().getName());
					plugin.getConfig().set("saves.spawn.x", player.getLocation().getX());
					plugin.getConfig().set("saves.spawn.y", player.getLocation().getY());
					plugin.getConfig().set("saves.spawn.z", player.getLocation().getZ());
					plugin.saveConfig();
					player.sendMessage(Battle.prefix() + "Battlespawn has been set to your current location!");
				}

				plugin.getGame().getSpawn().getLocation().getWorld().setTime(200);

				ItemStack base = new ItemStack(Material.QUARTZ_ORE);
				ItemMeta baseMeta = base.getItemMeta();
				baseMeta.setDisplayName(ChatColor.GRAY + "Base Block");
				baseMeta.setLore(Arrays.asList("Place me to create a base"));
				base.setItemMeta(baseMeta);

				boolean baseItem = plugin.getConfig().getBoolean("config.enable-base-block");

				for (Team t : this.teamManager.getTeams()) {
					for (String playername : t.getPlayers()) {
						Player p = Bukkit.getPlayerExact(playername);
						p.teleport(plugin.getGame().getSpawn().getLocation());
						p.setGameMode(GameMode.SURVIVAL);
						p.setAllowFlight(false);
						p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 300, 1));
						p.closeInventory();
						p.getInventory().clear();
						p.getInventory().setHelmet(new ItemStack(Material.AIR));
						p.getInventory().setChestplate(new ItemStack(Material.AIR));
						p.getInventory().setLeggings(new ItemStack(Material.AIR));
						p.getInventory().setBoots(new ItemStack(Material.AIR));
						p.getInventory().addItem(new Wool(t.getTeamColor().getDyeColor()).toItemStack(2));
						if (baseItem) {
							p.getInventory().addItem(base);
						}
						p.setHealth(20);
						p.setFoodLevel(20);
						p.setSaturation(20);
					}
				}

				try {
					timer = Integer.parseInt(args[1]);
				} catch (Exception e) {
				}
				if (timer > 0) {
					plugin.getGame().getSpawn().getLocation().getWorld().setPVP(false);

					plugin.getGame().setGraceTimer(new GraceTimer(timer * 60));
					Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, plugin.getGame().getGraceTimer(), 0, 20);
				}
				if (reminder == true) {
					Bukkit.broadcastMessage(Battle.prefix() + "Starting battle reminder. Scheduling every 15 minutes!");
					Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, plugin.getGame().getRemindTimer(), 0L, 1200L);
				}
				Bukkit.broadcastMessage(Battle.prefix() + "The game will warn you to place your wools in time! Remind to place ALL the wools before reloading or restarting!");
				for (Team t : this.teamManager.getTeams()) {
					t.setupInitialWool();
				}
				plugin.getGame().setStarted(true);
				plugin.setTags();
				plugin.updateScoreboard();
				return true;
			} else {
				player.sendMessage(Battle.prefix() + "There have to be at least two teams with one or more Player(s) before the battle can be launched!");
			}
		} else {
			player.sendMessage(Battle.prefix() + "Battle has already been started!");
		}
		return true;
	}
}
