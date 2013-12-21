package at.er.ytbattle.battle.cmd;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import at.er.ytbattle.battle.Battle;
import at.er.ytbattle.battle.timer.GraceTimer;
import at.er.ytbattle.util.SerializableLocation;

public class Cmd_battle_start {

	private Cmd_battle cmd;

	public Cmd_battle_start(Cmd_battle c) {
		cmd = c;
	}

	public boolean onCmdStart(String[] args, Player player) {

		Battle plugin = cmd.getPlugin();

		int timer = 0;

		int startlifes = plugin.getConfig().getInt("config.lifes-at-start");
		boolean reminder = plugin.getConfig().getBoolean("config.enable-remind-scheduler");

		if (plugin.getGame().getRed().getPlayers().size() > 0) {
			plugin.getGame().getRed().setLifes(startlifes);
			plugin.getGame().getTeams().add("red");
		}
		if (plugin.getGame().getBlue().getPlayers().size() > 0) {
			plugin.getGame().getBlue().setLifes(startlifes);
			plugin.getGame().getTeams().add("blue");
		}
		if (plugin.getGame().getGreen().getPlayers().size() > 0) {
			plugin.getGame().getGreen().setLifes(startlifes);
			plugin.getGame().getTeams().add("green");
		}
		if (plugin.getGame().getYellow().getPlayers().size() > 0) {
			plugin.getGame().getYellow().setLifes(startlifes);
			plugin.getGame().getTeams().add("yellow");
		}
		if (plugin.getGame().getPurple().getPlayers().size() > 0) {
			plugin.getGame().getPurple().setLifes(startlifes);
			plugin.getGame().getTeams().add("purple");
		}
		if (plugin.getGame().getCyan().getPlayers().size() > 0) {
			plugin.getGame().getCyan().setLifes(startlifes);
			plugin.getGame().getTeams().add("cyan");
		}
		if (plugin.getGame().getBlack().getPlayers().size() > 0) {
			plugin.getGame().getBlack().setLifes(startlifes);
			plugin.getGame().getTeams().add("black");
		}
		if (plugin.getGame().getWhite().getPlayers().size() > 0) {
			plugin.getGame().getWhite().setLifes(startlifes);
			plugin.getGame().getTeams().add("white");
		}

		if (plugin.getGame().isStarted() == false) {
			if (plugin.getGame().getTeams().size() > 1) {
				if (plugin.getGame().getSpawn() == null) {
					plugin.getGame().setSpawn(new SerializableLocation(player.getLocation()));
					plugin.getGame()
							.getSpawn()
							.getLocation()
							.getWorld()
							.setSpawnLocation((int) plugin.getGame().getSpawn().getLocation().getX(),
									(int) plugin.getGame().getSpawn().getLocation().getY(), (int) plugin.getGame().getSpawn().getLocation().getZ());

					plugin.getConfig().set("saves.spawn.world", player.getLocation().getWorld().getName());
					plugin.getConfig().set("saves.spawn.x", player.getLocation().getX());
					plugin.getConfig().set("saves.spawn.y", player.getLocation().getY());
					plugin.getConfig().set("saves.spawn.z", player.getLocation().getZ());

					plugin.saveConfig();

					player.sendMessage(Battle.prefix() + "Battlespawn has been set to your current location!");
				}

				plugin.getGame().getSpawn().getLocation().getWorld().setTime(200);

				for (String s : plugin.getGame().getPlayers()) {

					Player p = Bukkit.getPlayer(s);

					ItemStack base = new ItemStack(Material.QUARTZ_ORE);
					ItemMeta baseMeta = base.getItemMeta();
					baseMeta.setDisplayName(ChatColor.GRAY + "Base Block");
					baseMeta.setLore(Arrays.asList("Place me to create a base"));
					base.setItemMeta(baseMeta);

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

					p.setHealth(20);
					p.setFoodLevel(20);
					p.setSaturation(20);

					if (plugin.getGame().getRed().getPlayers().contains(p.getName())) {
						p.getInventory().addItem(new ItemStack(Material.WOOL, 2, (short) 14));
					}
					if (plugin.getGame().getBlue().getPlayers().contains(p.getName())) {
						p.getInventory().addItem(new ItemStack(Material.WOOL, 2, (short) 11));
					}
					if (plugin.getGame().getGreen().getPlayers().contains(p.getName())) {
						p.getInventory().addItem(new ItemStack(Material.WOOL, 2, (short) 5));
					}
					if (plugin.getGame().getYellow().getPlayers().contains(p.getName())) {
						p.getInventory().addItem(new ItemStack(Material.WOOL, 2, (short) 4));
					}
					if (plugin.getGame().getPurple().getPlayers().contains(p.getName())) {
						p.getInventory().addItem(new ItemStack(Material.WOOL, 2, (short) 10));
					}
					if (plugin.getGame().getCyan().getPlayers().contains(p.getName())) {
						p.getInventory().addItem(new ItemStack(Material.WOOL, 2, (short) 9));
					}
					if (plugin.getGame().getBlack().getPlayers().contains(p.getName())) {
						p.getInventory().addItem(new ItemStack(Material.WOOL, 2, (short) 15));
					}
					if (plugin.getGame().getWhite().getPlayers().contains(p.getName())) {
						p.getInventory().addItem(new ItemStack(Material.WOOL, 2));
					}

					if (plugin.getConfig().getBoolean("config.enable-base-block")) {
						p.getInventory().addItem(base);
					}
				}

				try {
					timer = Integer.parseInt(args[1]);
				} catch (Exception e) {
				}

				if (timer > 0) {
					plugin.getGame().getSpawn().getLocation().getWorld().setPVP(false);

					plugin.getGame().setGraceTimer(new GraceTimer(plugin, timer * 60));
					Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, plugin.getGame().getGraceTimer(), 0, 20);
				}

				if (reminder == true) {
					Bukkit.broadcastMessage(Battle.prefix() + "Starting battle reminder. Scheduling every 15 minutes!");
					Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, plugin.getGame().getRemindTimer(), 0L, 1200L);
				}

				Bukkit.broadcastMessage(Battle.prefix()
						+ "The game will warn you to place your wools in time! Remind to place ALL the wools before reloading or restarting!");

				plugin.updateScoreboard();

				plugin.getGame().getRed().setupInitialWool();
				plugin.getGame().getBlue().setupInitialWool();
				plugin.getGame().getGreen().setupInitialWool();
				plugin.getGame().getYellow().setupInitialWool();
				plugin.getGame().getPurple().setupInitialWool();
				plugin.getGame().getCyan().setupInitialWool();
				plugin.getGame().getBlack().setupInitialWool();
				plugin.getGame().getWhite().setupInitialWool();
				
				plugin.getGame().setStarted(true);

				plugin.setTags();

				return true;
			} else {
				player.sendMessage(Battle.prefix()
						+ "There have to be at least two teams with one or more Player(s) before the battle can be launched!");
			}
		} else {
			player.sendMessage(Battle.prefix() + "Battle has allready been started!");
		}
		return true;
	}
}
