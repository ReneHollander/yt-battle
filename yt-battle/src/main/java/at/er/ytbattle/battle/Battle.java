package at.er.ytbattle.battle;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

import com.google.common.base.Functions;
import com.google.common.collect.Lists;

import at.er.ytbattle.battle.cmd.Cmd_battle;
import at.er.ytbattle.battle.event.GameListener;
import at.er.ytbattle.battle.event.SpectatorListener;
import at.rene8888.serilib.Deserialize;
import at.rene8888.serilib.Serialize;

public class Battle extends JavaPlugin implements Serializable {
	private static final long serialVersionUID = 1L;

	public boolean dontSave;

	private Game game;
	public GameListener gl;
	public SpectatorListener sl;
	private HashMap<String, ItemStack[]> inventories;
	private HashMap<String, ItemStack[]> armor;

	private Inventory chestInventory;

	public void onEnable() {

		this.dontSave = false;

		this.loadConfig();

		this.game = new Game(this);
		this.gl = new GameListener(this);
		this.sl = new SpectatorListener(this);
		this.inventories = new HashMap<String, ItemStack[]>();
		this.armor = new HashMap<String, ItemStack[]>();

		this.loadConfig();
		this.loadGame();

		this.addCraftings();
		this.registerCommands();
		this.registerEvents();

		this.setDisplayNames();
		this.setTags();
		this.updateScoreboard();

		this.loadChestInventory();

	}

	public void onDisable() {
		saveGame();
	}

	public void registerEvents() {
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(this.gl, this); // game
		pm.registerEvents(this.sl, this); // spectator
	}

	public void loadConfig() {
		ArrayList<String> defaultChestContent = new ArrayList<String>();
		
		defaultChestContent.add("392:16");
		defaultChestContent.add("272");
		defaultChestContent.add("273");
		defaultChestContent.add("274");
		defaultChestContent.add("275");
		
		this.getConfig().addDefault("config.enable-remind-scheduler", true);

		this.getConfig().addDefault("config.enable-automatic-save", true);
		this.getConfig().addDefault("config.enable-automatic-load", false);
		this.getConfig().addDefault("config.enable-base-block", true);
		this.getConfig().addDefault("config.lifes-at-start", 10);
		this.getConfig().addDefault("config.minutes-till-broken-wool-effects-appears", 15);
		this.getConfig().addDefault("config.base-block-chest-content", defaultChestContent);
		
		this.getConfig().addDefault("saves.spawn.world", "");
		this.getConfig().addDefault("saves.spawn.x", 0);
		this.getConfig().addDefault("saves.spawn.y", 0);
		this.getConfig().addDefault("saves.spawn.z", 0);

		this.getConfig().options().header("Battle Plugin by EXSolo and Rene8888");

		this.getConfig().options().copyDefaults(true);
		this.saveConfig();
	}

	public void registerCommands() {
		getCommand("battle").setExecutor(new Cmd_battle(this));
		getCommand("b").setExecutor(new Cmd_battle(this));
	}

	public void dontSave(boolean b) {
		this.dontSave = b;
	}

	@SuppressWarnings("deprecation")
	public void addCraftings() {

		ItemStack tear = new ItemStack(Material.GHAST_TEAR, 1);
		ItemMeta tearMeta = tear.getItemMeta();
		tearMeta.setDisplayName(ChatColor.GOLD + "Live Exchanger");
		tearMeta.setLore(Arrays.asList("Right Click Me"));
		tear.setItemMeta(tearMeta);

		ShapelessRecipe lifes = new ShapelessRecipe(tear);
		for (int i = 0; i <= 15; i++) {
			for (int j = 0; j <= 15; j++) {
				if ((i == 0 || i == 4 || i == 5 || i == 9 || i == 10 || i == 11 || i == 14 || i == 15) && (j == 0 || j == 4 || j == 5 || j == 9 || j == 10 || j == 11 || j == 14 || j == 15)) {
					lifes.addIngredient(1, Material.WOOL.getNewData((byte) i));
					lifes.addIngredient(1, Material.WOOL.getNewData((byte) j));
					Bukkit.getServer().addRecipe(lifes);
				}
				lifes.removeIngredient(1, Material.WOOL.getNewData((byte) i));
				lifes.removeIngredient(1, Material.WOOL.getNewData((byte) j));
			}
		}

		ItemStack steak = new ItemStack(Material.COOKED_BEEF);
		ItemMeta steakMeta = steak.getItemMeta();
		steakMeta.setDisplayName(ChatColor.DARK_PURPLE + "Nom Nom!");

		ShapelessRecipe food = new ShapelessRecipe(steak);
		food.addIngredient(Material.ROTTEN_FLESH);
		food.addIngredient(Material.GOLD_NUGGET);
		Bukkit.getServer().addRecipe(food);
	}

	public void loadGame() {

		File save = new File(getDataFolder(), "battle.save");

		if (save.exists()) {
			try {
				Object o = Deserialize.readFromFile(save, true);
				if (o instanceof Game) {
					Game g = (Game) o;
					if (g.isSaved()) {
						game = g;
						System.out.println("Loaded battle.save!");
					}
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("No battle.save file detected...");
			System.out.println("Skipping data loading...");
		}

	}

	public void saveGame() {
		if (!dontSave) {
			if (game.isStarted()) {
				try {
					for (Player p : Bukkit.getOnlinePlayers()) {
						if (game.getPlayers().contains(p.getName())) {
							p.setDisplayName(p.getName());
						}
					}
					File file = new File(getDataFolder(), "battle.save");
					game.setSaved(true);
					Serialize.writeToFile(game, file, true);
				} catch (IOException e) {
					game.setSaved(false);
					e.printStackTrace();
				}
			}
		}
	}

	public void updateScoreboard() {
		Scoreboard sb = Bukkit.getScoreboardManager().getNewScoreboard();
		Objective lifes = sb.registerNewObjective("stats", "dummy");

		lifes.setDisplayName(ChatColor.BOLD + "Battle Teamstats");
		lifes.setDisplaySlot(DisplaySlot.SIDEBAR);

		if (game.getRed().getLifes() > 0)
			lifes.getScore(Bukkit.getOfflinePlayer(ChatColor.DARK_RED + "Team Red")).setScore(game.getRed().getLifes());
		if (game.getBlue().getLifes() > 0)
			lifes.getScore(Bukkit.getOfflinePlayer(ChatColor.DARK_BLUE + "Team Blue")).setScore(game.getBlue().getLifes());
		if (game.getGreen().getLifes() > 0)
			lifes.getScore(Bukkit.getOfflinePlayer(ChatColor.GREEN + "Team Green")).setScore(game.getGreen().getLifes());
		if (game.getYellow().getLifes() > 0)
			lifes.getScore(Bukkit.getOfflinePlayer(ChatColor.YELLOW + "Team Yellow")).setScore(game.getYellow().getLifes());
		if (game.getPurple().getLifes() > 0)
			lifes.getScore(Bukkit.getOfflinePlayer(ChatColor.DARK_PURPLE + "Team Purple")).setScore(game.getPurple().getLifes());
		if (game.getCyan().getLifes() > 0)
			lifes.getScore(Bukkit.getOfflinePlayer(ChatColor.AQUA + "Team Cyan")).setScore(game.getCyan().getLifes());
		if (game.getBlack().getLifes() > 0)
			lifes.getScore(Bukkit.getOfflinePlayer(ChatColor.BLACK + "Team Black")).setScore(game.getBlack().getLifes());
		if (game.getWhite().getLifes() > 0)
			lifes.getScore(Bukkit.getOfflinePlayer(ChatColor.WHITE + "Team White")).setScore(game.getWhite().getLifes());

		if (sb.getPlayers().size() == 0) {
			lifes.getScore(Bukkit.getOfflinePlayer(ChatColor.ITALIC + "Battle v" + getDescription().getVersion())).setScore(8);
			lifes.getScore(Bukkit.getOfflinePlayer(ChatColor.ITALIC + "")).setScore(7);
			lifes.getScore(Bukkit.getOfflinePlayer(ChatColor.ITALIC + "by")).setScore(6);
			lifes.getScore(Bukkit.getOfflinePlayer(ChatColor.ITALIC + "EXSolo")).setScore(5);
			lifes.getScore(Bukkit.getOfflinePlayer(ChatColor.ITALIC + "Rene8888")).setScore(4);
		}

		for (Player p : Bukkit.getOnlinePlayers()) {
			p.setScoreboard(sb);
		}
	}

	public void removeFromLists(Player player) {
		game.getRed().getPlayers().remove(player.getName());
		game.getBlue().getPlayers().remove(player.getName());
		game.getGreen().getPlayers().remove(player.getName());
		game.getYellow().getPlayers().remove(player.getName());
		game.getPurple().getPlayers().remove(player.getName());
		game.getCyan().getPlayers().remove(player.getName());
		game.getBlack().getPlayers().remove(player.getName());
		game.getWhite().getPlayers().remove(player.getName());
	}

	public void saveInventory(Player p) {
		ItemStack[] inv = p.getInventory().getContents();
		ItemStack[] arm = p.getInventory().getArmorContents();
		inventories.put(p.getName(), inv);
		armor.put(p.getName(), arm);
	}

	public ItemStack[][] loadInventory(Player p) {
		ItemStack[][] iss = { inventories.get(p.getName()), armor.get(p.getName()) };
		inventories.remove(p.getName());
		armor.remove(p.getName());

		return iss;
	}

	public void setTags() {
		if (game.isStarted() == false) {
			for (Player p : Bukkit.getOnlinePlayers()) {
				p.setPlayerListName(p.getName());
			}
		}

		for (Player p : Bukkit.getOnlinePlayers()) {
			String name = p.getName();

			if (game.getRed().getPlayers().contains(name)) {
				String s = ChatColor.DARK_RED + p.getName();
				if (s.length() > 16) {
					s = s.substring(0, 16);
				}
				p.setPlayerListName(s);
			} else if (game.getBlue().getPlayers().contains(name)) {
				String s = ChatColor.DARK_BLUE + p.getName();
				if (s.length() > 16) {
					s = s.substring(0, 16);
				}
				p.setPlayerListName(s);
			} else if (game.getGreen().getPlayers().contains(name)) {
				String s = ChatColor.DARK_GREEN + p.getName();
				if (s.length() > 16) {
					s = s.substring(0, 16);
				}
				p.setPlayerListName(s);
			} else if (game.getYellow().getPlayers().contains(name)) {
				String s = ChatColor.YELLOW + p.getName();
				if (s.length() > 16) {
					s = s.substring(0, 16);
				}
				p.setPlayerListName(s);
			} else if (game.getPurple().getPlayers().contains(name)) {
				String s = ChatColor.DARK_PURPLE + p.getName();
				if (s.length() > 16) {
					s = s.substring(0, 16);
				}
				p.setPlayerListName(s);
			} else if (game.getCyan().getPlayers().contains(name)) {
				String s = ChatColor.DARK_AQUA + p.getName();
				if (s.length() > 16) {
					s = s.substring(0, 16);
				}
				p.setPlayerListName(s);
			} else if (game.getBlack().getPlayers().contains(name)) {
				String s = ChatColor.BLACK + p.getName();
				if (s.length() > 16) {
					s = s.substring(0, 16);
				}
				p.setPlayerListName(s);
			} else if (game.getWhite().getPlayers().contains(name)) {
				String s = ChatColor.WHITE + p.getName();
				if (s.length() > 16) {
					s = s.substring(0, 16);
				}
				p.setPlayerListName(s);
			}
		}
	}

	public static String prefix() {
		return ChatColor.GOLD + "[Battle] " + ChatColor.WHITE;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public void setDisplayNames() {
		for (Player p : Bukkit.getOnlinePlayers()) {
			p.setDisplayName(p.getName());
		}
	}

	@SuppressWarnings("deprecation")
	public void loadChestInventory() {
		List<?> confList = this.getConfig().getList("config.base-block-chest-content");

		try {

			List<String> itemIDs = Lists.transform(confList, Functions.toStringFunction());

			for (String s : itemIDs) {
				String[] cont = s.split(":");

				if (cont.length > 1) {
					ItemStack item = new ItemStack(Integer.parseInt(cont[0]), Integer.parseInt(cont[1]));

					this.chestInventory.addItem(item);
				} else if (cont.length > 0) {
					ItemStack item = new ItemStack(Integer.parseInt(cont[0]));

					this.chestInventory.addItem(item);
				}
			}
		} catch (Exception ex) {
			System.err.println("Error occured while loading base block chest content from config");
			this.chestInventory = null;
		}
	}

	public Inventory getChestContent() {
		return this.chestInventory;
	}

}
