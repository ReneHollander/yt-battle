package at.er.ytbattle.battle;

import java.io.File;
import java.io.IOException;
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
import org.bukkit.material.Wool;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

import at.er.ytbattle.battle.command.CommandManager;
import at.er.ytbattle.battle.event.BlockBreakListener;
import at.er.ytbattle.battle.event.BlockPlaceListener;
import at.er.ytbattle.battle.event.EntityDeathListener;
import at.er.ytbattle.battle.event.EntityExplodeListener;
import at.er.ytbattle.battle.event.PlayerChatListener;
import at.er.ytbattle.battle.event.PlayerDeathListener;
import at.er.ytbattle.battle.event.PlayerInteractListener;
import at.er.ytbattle.battle.event.PlayerJoinListener;
import at.er.ytbattle.battle.event.PlayerMoveListener;
import at.er.ytbattle.battle.event.PlayerRespawnListener;
import at.er.ytbattle.battle.event.PlayerShearListener;
import at.er.ytbattle.battle.event.PrepareItemCraftListener;
import at.er.ytbattle.battle.player.BattlePlayer;
import at.er.ytbattle.battle.player.BattlePlayerManager;
import at.er.ytbattle.battle.timer.InvincibilityTimerManager;
import at.er.ytbattle.battle.timer.RemindTimer;
import at.er.ytbattle.util.PlayerArmor;
import at.rene8888.serilib.Deserialize;
import at.rene8888.serilib.Serialize;

import com.google.common.base.Functions;
import com.google.common.collect.Lists;

public class Battle extends JavaPlugin {

    private static Battle instance;

    public boolean dontSave;

    private Game game;

    public HashMap<Player, PlayerArmor> playerArmor;

    @Override
    public void onEnable() {

        instance = this;

        this.playerArmor = new HashMap<Player, PlayerArmor>();

        new BattlePlayerManager(this);

        
        
        this.loadConfig();
        this.loadGame();

        this.dontSave = false;

        this.addCraftings();
        this.registerCommands();
        this.registerEvents();

        this.setDisplayNames();
        this.setTags();
        this.updateScoreboard();

        new InvincibilityTimerManager(this, getConfig().getInt("config.invincibility-timer-duration"));

    }

    @Override
    public void onDisable() {
        try {
            RemindTimer.getRT().stopTimer();
        } catch (Exception e) {
        }
        saveGame();
    }

    public static Battle instance() {
        return instance;
    }

    public void registerEvents() {
        new BlockBreakListener();
        new BlockPlaceListener();
        new EntityDeathListener();
        new EntityExplodeListener();
        new PlayerChatListener();
        new PlayerDeathListener();
        new PlayerInteractListener();
        new PlayerJoinListener();
        new PlayerMoveListener();
        new PlayerRespawnListener();
        new PlayerShearListener();
        new PrepareItemCraftListener();
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
        this.getConfig().addDefault("config.invincibility-timer-duration", 10);
        this.getConfig().addDefault("config.enable-base-block", true);
        this.getConfig().addDefault("config.lifes-at-start", 10);
        this.getConfig().addDefault("config.minutes-till-broken-wool-effects-appears", 15);
        this.getConfig().addDefault("config.wool-place-remove-radius", 2);
        this.getConfig().addDefault("config.wool-place-min-height", 50);
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
        getCommand("battle").setExecutor(new CommandManager());
        getCommand("b").setExecutor(new CommandManager());
    }

    public void dontSave(boolean b) {
        this.dontSave = b;
    }

    public void addCraftings() {

        ItemStack tear = new ItemStack(Material.GHAST_TEAR, 1);
        ItemMeta tearMeta = tear.getItemMeta();
        tearMeta.setDisplayName(ChatColor.GOLD + "Live Exchanger");
        tearMeta.setLore(Arrays.asList("Right Click Me"));
        tear.setItemMeta(tearMeta);

        for (TeamColor tc1 : TeamColor.values()) {
            for (TeamColor tc2 : TeamColor.values()) {
                Wool w1 = new Wool(tc1.getDyeColor());
                Wool w2 = new Wool(tc2.getDyeColor());
                ShapelessRecipe lifes = new ShapelessRecipe(tear);
                lifes.addIngredient(1, w1);
                lifes.addIngredient(1, w2);
                Bukkit.getServer().addRecipe(lifes);
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
                Object o = Deserialize.readFromFile(save, false);
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
            this.game = new Game(this, new TeamManager(this));
        }

    }

    public void saveGame() {
        if (!dontSave) {
            if (game.isStarted()) {
                try {
                    for (Player p : Bukkit.getOnlinePlayers()) {
                        p.setDisplayName(p.getName());
                    }
                    File file = new File(getDataFolder(), "battle.save");
                    game.setSaved(true);
                    Serialize.writeToFile(game, file, false);
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

        lifes.setDisplaySlot(DisplaySlot.SIDEBAR);

        if (game.isStarted() == false || dontSave) {
            lifes.setDisplayName(ChatColor.BOLD + "Battle Infos");

            lifes.getScore(ChatColor.ITALIC + "Battle v" + getDescription().getVersion()).setScore(5);
            lifes.getScore(ChatColor.ITALIC + "").setScore(4);
            lifes.getScore(ChatColor.ITALIC + "by").setScore(3);
            lifes.getScore(ChatColor.ITALIC + "EXSolo").setScore(2);
            lifes.getScore(ChatColor.ITALIC + "Rene8888").setScore(1);
        } else {
            lifes.setDisplayName(ChatColor.BOLD + "Battle Teamstats");

            for (Team t : this.game.getTeamManager().getTeams()) {
                if (t.getPlayers().size() > 0) {
                    lifes.getScore(t.getTeamColor().getChatColor() + "Team " + t.getTeamColor().getLongName()).setScore(t.getLifes());
                }
            }
        }

        for (Player p : Bukkit.getOnlinePlayers()) {
            try {
                p.setScoreboard(sb);
            } catch (Exception e) {
            }
        }
    }

    public void removeFromLists(BattlePlayer player) {
        try {
            this.game.getTeamManager().getTeamByPlayer(player).removePlayer(player);
        } catch (Exception e) {
        }
    }

    public void unsetTags() {
        for (Player p : Bukkit.getOnlinePlayers()) {
            p.setPlayerListName(p.getName());
            p.setDisplayName(p.getName());
        }
    }

    public void setTags() {
        for (Team t : this.game.getTeamManager().getTeams()) {
            for (BattlePlayer player : t.getPlayers()) {
                if (player != null && player.isLoaded()) {
                    setDisplayAndListName(player, t);
                }
            }
        }
    }

    public void setDisplayAndListName(BattlePlayer player, Team team) {
        String display = team.getTeamColor().getChatColor() + player.getName();
        if (display.length() > 15) {
            display = display.substring(0, 15);
        }
        player.setPlayerListName(display);
        player.setDisplayName(display + ChatColor.RESET);
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
    public Inventory getChestContent() {
        Inventory inv = Bukkit.createInventory(null, 54);

        List<?> confList = this.getConfig().getList("config.base-block-chest-content");

        try {

            List<String> itemIDs = Lists.transform(confList, Functions.toStringFunction());

            for (String s : itemIDs) {

                String[] cont = s.split(":");

                if (cont.length > 1) {
                    ItemStack item = new ItemStack(Integer.parseInt(cont[0]), Integer.parseInt(cont[1]));

                    inv.addItem(item);
                } else if (cont.length > 0) {
                    ItemStack item = new ItemStack(Integer.parseInt(cont[0]));

                    inv.addItem(item);
                }
            }

            return inv;

        } catch (Exception ex) {
            System.err.println("Error occured while loading base block chest content from config");
            return null;
        }
    }
}
