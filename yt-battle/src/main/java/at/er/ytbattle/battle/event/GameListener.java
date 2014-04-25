package at.er.ytbattle.battle.event;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.Effect;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.event.player.PlayerShearEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.Wool;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import at.er.ytbattle.battle.Battle;
import at.er.ytbattle.battle.Team;
import at.er.ytbattle.battle.TeamManager;
import at.er.ytbattle.battle.timer.FireworkTimer;
import at.er.ytbattle.battle.timer.InvincibilityTimerManager;
import at.er.ytbattle.battle.timer.RemindTimer;
import at.er.ytbattle.util.PlayerArmor;

public class GameListener implements Listener, Serializable {
	private static final long serialVersionUID = 1L;

	private Battle plugin;

	private TeamManager teamManager;

	private HashMap<Player, PlayerArmor> playerArmor;

	public GameListener(Battle plugin) {
		this.plugin = plugin;
		this.teamManager = this.plugin.getGame().getTeamManager();
		this.playerArmor = new HashMap<Player, PlayerArmor>();
	}

	@EventHandler(priority = EventPriority.HIGH)
	public void onPlayerJoin(PlayerJoinEvent event) {

		plugin.setTags();
		plugin.updateScoreboard();
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onBlockBreak(BlockBreakEvent event) {
		Player player = (Player) event.getPlayer();
		if (event.getBlock().getType() == Material.WOOL && plugin.getGame().isStarted() && this.teamManager.isInTeam(player)) {
			DyeColor color = ((Wool) event.getBlock().getState().getData()).getColor();
			Team t = this.teamManager.getTeamByPlayer(player);

			Team victim = this.teamManager.getTeamByDyeColor(color);
			if (t.getTeamColor().getDyeColor().equals(color)) {
				player.sendMessage(Battle.prefix() + "You can't break your own team's wool!");
				event.setCancelled(true);
			} else {
				victim.getBlockPlaceTimerManager().woolBreak();

				if (InvincibilityTimerManager.getITM().timerRunning(player)) {
					InvincibilityTimerManager.getITM().stopTimer(player);
					player.sendMessage(Battle.prefix() + "You have lost your invincibility!");
				}

				Bukkit.broadcastMessage(Battle.prefix() + player.getName() + " destroyed a " + victim.getTeamColor().getChatColor() + victim.getTeamColor().getLongName() + ChatColor.RESET + " wool!");
			}
		}
		if (plugin.getGame().getSpawn() != null) {
			if (event.getBlock().getType() == Material.GLASS && player.getWorld() == plugin.getGame().getSpawn().getLocation().getWorld() && plugin.getGame().isStarted() && player.getGameMode() != GameMode.CREATIVE) {
				event.setCancelled(true);
				player.sendMessage(Battle.prefix() + "You have reached the Battleborder!");
			}
		}

	}

	@EventHandler(priority = EventPriority.HIGH)
	public void onEntityExplode(EntityExplodeEvent event) {
		if (event.getEntityType() == EntityType.CREEPER)
			event.blockList().clear();
		for (Block block : event.blockList()) {
			if (block.getType() == Material.WOOL || block.getType() == Material.GLASS)
				event.blockList().remove(block);
		}
	}

	@EventHandler(priority = EventPriority.HIGH)
	public void onPlayerShears(PlayerShearEntityEvent event) {
		event.setCancelled(true);
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onBlockPlace(BlockPlaceEvent event) {
		Player player = event.getPlayer();
		if (event.getBlock().getType() == Material.GLASS && player.getWorld() == plugin.getGame().getSpawn().getLocation().getWorld() && plugin.getGame().isStarted()) {
			event.setCancelled(true);
			player.sendMessage(Battle.prefix() + "You are unable to place a Block of the Bordermaterial.");
		}
		if (event.getBlock().getType() == Material.WOOL && plugin.getGame().isStarted() && this.teamManager.isInTeam(player)) {
			DyeColor color = ((Wool) event.getBlock().getState().getData()).getColor();
			Team t = this.teamManager.getTeamByPlayer(player);
			if (t.getTeamColor().getDyeColor().equals(color)) {
				boolean placed = this.placeWool(player, event.getBlock().getLocation(), color);
				if (placed == false) {
					player.sendMessage(Battle.prefix() + "Invalid Wool Location!");
					event.setCancelled(true);
				} else {
					t.getBlockPlaceTimerManager().woolPlace();
				}
			} else {
				player.sendMessage(Battle.prefix() + "You can't place other team's wool!");
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

	public boolean placeWool(Player p, Location l, DyeColor color) {

		int minHeight = this.plugin.getConfig().getInt("config.wool-place-min-height");

		if (l.getBlockY() < minHeight) {
			return false;
		} else {
			for (int i = l.getBlockY() + 1; i <= 255; i++) {
				Location now = (Location) l.clone();
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

		int rad = this.plugin.getConfig().getInt("config.wool-place-remove-radius");

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

	// Interact

	@EventHandler(priority = EventPriority.HIGH)
	public void onPlayerInteract(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		if ((event.getAction() == Action.RIGHT_CLICK_BLOCK || event.getAction() == Action.RIGHT_CLICK_AIR) && player.getItemInHand().getType() == Material.GHAST_TEAR && plugin.getGame().isStarted()) {
			if (this.teamManager.isInTeam(player) == false) {
				player.sendMessage(Battle.prefix() + "You left the battle, this item is worthless now!");
				return;
			}
			Team t = this.teamManager.getTeamByPlayer(player);
			if (t != null) {
				t.setLifes(t.getLifes() + 1);
				player.sendMessage(Battle.prefix() + t.getTeamColor().getLongName() + " team has now " + t.getLifes() + " lifes left!");
				player.playSound(player.getLocation(), Sound.LEVEL_UP, 10, 1);
			}
			if (player.getItemInHand().getAmount() > 1) {
				player.getItemInHand().setAmount(player.getItemInHand().getAmount() - 1);
			} else {
				player.getInventory().setItemInHand(new ItemStack(Material.AIR));
			}
			plugin.updateScoreboard();
		}
	}

	// Death

	@EventHandler(priority = EventPriority.HIGH)
	public void onEntityDeath(EntityDeathEvent event) {
		if (event.getEntityType() == EntityType.SHEEP)
			event.getDrops().clear();
	}

	@SuppressWarnings("rawtypes")
	@EventHandler(priority = EventPriority.HIGH)
	public void onPlayerDeath(PlayerDeathEvent event) {
		Player player = event.getEntity();

		if (plugin.getGame().isStarted() && this.teamManager.isInTeam(player)) {

			Location spawn = plugin.getGame().getSpawn().getLocation();

			Team t = this.teamManager.getTeamByPlayer(player);

			ItemStack helmet = player.getInventory().getHelmet();
			ItemStack chestplate = player.getInventory().getChestplate();
			ItemStack leggings = player.getInventory().getLeggings();
			ItemStack boots = player.getInventory().getBoots();

			if (helmet != null) {
				if (helmet.getType() == Material.DIAMOND_HELMET)
					helmet = new ItemStack(Material.IRON_HELMET);
				helmet.setDurability((short) 0);
				Iterator<?> hit = helmet.getEnchantments().entrySet().iterator();
				while (hit.hasNext())
					helmet.removeEnchantment((Enchantment) ((Map.Entry) hit.next()).getKey());
			}

			if (chestplate != null) {
				if (chestplate.getType() == Material.DIAMOND_CHESTPLATE)
					chestplate = new ItemStack(Material.IRON_CHESTPLATE);
				chestplate.setDurability((short) 0);
				Iterator<?> cit = chestplate.getEnchantments().entrySet().iterator();
				while (cit.hasNext())
					chestplate.removeEnchantment((Enchantment) ((Map.Entry) cit.next()).getKey());
			}

			if (leggings != null) {
				if (leggings.getType() == Material.DIAMOND_LEGGINGS)
					leggings = new ItemStack(Material.IRON_LEGGINGS);
				leggings.setDurability((short) 0);
				Iterator<?> lit = leggings.getEnchantments().entrySet().iterator();
				while (lit.hasNext())
					leggings.removeEnchantment((Enchantment) ((Map.Entry) lit.next()).getKey());
			}

			if (boots != null) {
				if (boots.getType() == Material.DIAMOND_BOOTS)
					boots = new ItemStack(Material.IRON_BOOTS);
				boots.setDurability((short) 0);
				Iterator<?> bit = boots.getEnchantments().entrySet().iterator();
				while (bit.hasNext())
					boots.removeEnchantment((Enchantment) ((Map.Entry) bit.next()).getKey());
			}

			PlayerArmor armor = new PlayerArmor(helmet, chestplate, leggings, boots);
			this.playerArmor.put(player, armor);

			if (t.getLifes() > 0) {
				t.setLifes(t.getLifes() - 1);
			} else {
				player.teleport(spawn);
				player.setDisplayName(player.getName());
				t.removePlayer(player.getName());
				Bukkit.broadcastMessage(Battle.prefix() + player.getName() + " from the " + t.getTeamColor().getShortName() + " team has lost!");
				if (t.getTeamSize() == 0) {
					t.setLost(true);
					Bukkit.broadcastMessage(Battle.prefix() + "Team " + t.getTeamColor().getShortName() + " has lost!");
				}
			}

//			if (this.teamManager.isLastTeam(t)) {
//				Bukkit.broadcastMessage(Battle.prefix() + "Team " + t.getTeamColor().getShortName() + " has won the Battle!");
//				for (String s : t.getPlayers()) {
//					Player p = Bukkit.getPlayer(s);
//					p.setDisplayName(ChatColor.GOLD + "[Winner]" + ChatColor.WHITE + " - " + p.getName());
//					p.teleport(spawn);
//					p.setAllowFlight(true);
//					p.setFlying(true);
//				}
//				RemindTimer.getRT().stopTimer();
//				plugin.getGame().setStarted(false);
//				FireworkTimer ft = new FireworkTimer();
//				int id = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, ft, 0, 20L);
//				ft.setID(id);
//				Bukkit.broadcastMessage(Battle.prefix() + "Thanks for playing! Battle Plugin v" + plugin.getDescription().getVersion() + " made by EXSolo and Rene8888.");
//			}
		}

		plugin.updateScoreboard();

	}

	@EventHandler(priority = EventPriority.HIGH)
	public void onPlayerRespawn(PlayerRespawnEvent event) {
		Player player = event.getPlayer();

		if (plugin.getGame().isStarted() && this.teamManager.isInTeam(player)) {

			Team t = this.teamManager.getTeamByPlayer(player);
			if (this.playerArmor.get(player) != null) {
				PlayerArmor armor = this.playerArmor.get(player);
				player.getInventory().addItem(new ItemStack(Material.IRON_SWORD));
				player.getInventory().setHelmet(armor.getHelmet());
				player.getInventory().setChestplate(armor.getChestplate());
				player.getInventory().setLeggings(armor.getLeggings());
				player.getInventory().setBoots(armor.getBoots());
				this.playerArmor.remove(player);
			}

			InvincibilityTimerManager.getITM().createTimer(player);
			event.setRespawnLocation(plugin.getGame().getSpawn().getLocation());
			player.getInventory().addItem(new Wool(t.getTeamColor().getDyeColor()).toItemStack(1));
			player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 300, 2));
		}
	}

	@EventHandler
	public void onPlayerMove(PlayerMoveEvent event) {
		Player player = event.getPlayer();

		if (plugin.getGame().isStarted()) {
			if (this.teamManager.isInTeam(player)) {
				if (player.getInventory().contains(Material.WOOL)) {

					Team t = this.teamManager.getTeamByPlayer(player);

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

	@EventHandler
	public void onPrepareItemCraft(PrepareItemCraftEvent event) {

		if (event.getInventory().getResult().getType() == Material.WOOL) {
			ItemStack ce = new ItemStack(Material.WRITTEN_BOOK);
			BookMeta ceMeta = (BookMeta) ce.getItemMeta();
			ceMeta.setDisplayName(ChatColor.GOLD + "Cheat Protection");
			ceMeta.addPage("#DisabledCheating :)\nNo wool by crafting :(");
			ceMeta.setAuthor("Ihr(e) Bundeskanzler(in)");
			ce.setItemMeta(ceMeta);
			event.getInventory().setResult(ce);
		}

		if (event.getInventory().getResult().getType() == Material.CARPET) {
			ItemStack tear = new ItemStack(Material.GHAST_TEAR, 1);
			ItemMeta tearMeta = tear.getItemMeta();
			tearMeta.setDisplayName(ChatColor.GOLD + "Live Exchanger");
			tearMeta.setLore(Arrays.asList("Right Click Me"));
			tear.setItemMeta(tearMeta);
			event.getInventory().setResult(tear);
		}
	}

	@EventHandler
	public void onPlayerChat(AsyncPlayerChatEvent event) {
		Player player = event.getPlayer();
		if (plugin.getGame().isStarted()) {
			if (this.teamManager.isInTeam(player)) {
				Team t = this.teamManager.getTeamByPlayer(player);
				if (this.teamManager.isLastTeam(t)) {
					event.setFormat(ChatColor.GOLD + "[Winner]" + ChatColor.WHITE + " - " + "%1$s: " + ChatColor.RESET + "%2$s");
				} else {
					event.setFormat(t.getTeamColor().getChatColor() + "[Battle]" + ChatColor.WHITE + " - " + "%1$s: " + ChatColor.RESET + "%2$s");
				}
			}
		}
	}

	public void buildBase(Location l) {
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

		if (this.plugin.getChestContent() != null) {
			c.getInventory().setContents(this.plugin.getChestContent().getContents());
		}
	}
}
