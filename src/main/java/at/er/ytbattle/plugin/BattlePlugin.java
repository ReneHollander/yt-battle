package at.er.ytbattle.plugin;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Arrays;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.Wool;
import org.bukkit.plugin.java.JavaPlugin;

import at.er.ytbattle.plugin.command.CommandManager;
import at.er.ytbattle.plugin.event.BlockBreakListener;
import at.er.ytbattle.plugin.event.BlockPlaceListener;
import at.er.ytbattle.plugin.event.EntityDamageListener;
import at.er.ytbattle.plugin.event.EntityDeathListener;
import at.er.ytbattle.plugin.event.EntityExplodeListener;
import at.er.ytbattle.plugin.event.InvincibilityListener;
import at.er.ytbattle.plugin.event.PlayerChatListener;
import at.er.ytbattle.plugin.event.PlayerDeathListener;
import at.er.ytbattle.plugin.event.PlayerInteractListener;
import at.er.ytbattle.plugin.event.PlayerJoinListener;
import at.er.ytbattle.plugin.event.PlayerMoveListener;
import at.er.ytbattle.plugin.event.PlayerRespawnListener;
import at.er.ytbattle.plugin.event.PlayerShearListener;
import at.er.ytbattle.plugin.event.PrepareItemCraftListener;
import at.er.ytbattle.util.BattleUtils;
import at.er.ytbattle.util.ConfigurationHelper;
import at.er.ytbattle.util.PlayerArmor;
import at.er.ytbattle.util.XStreamUtil;

import com.thoughtworks.xstream.XStream;

public class BattlePlugin extends JavaPlugin {

    private static BattlePlugin instance;
    private static ConfigurationHelper configurationHelper;
    private static Game game;

    public HashMap<Player, PlayerArmor> playerArmor;
    public boolean dontSave;

    @Override
    public void onEnable() {
        instance = this;
        configurationHelper = new ConfigurationHelper();
        game = loadGame(new File(getDataFolder(), BattleUtils.SAVE_FILE_NAME));

        this.dontSave = false;
        this.playerArmor = new HashMap<Player, PlayerArmor>();

        this.addCraftings();
        this.registerCommands();
        this.registerEvents();
        BattleUtils.setTags();
        BattleUtils.updateScoreboard();
    }

    @Override
    public void onDisable() {
        BattlePlugin.game().getTimerManager().pauseAllTimers();
        saveGame();
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
        new EntityDamageListener();
        new InvincibilityListener();
    }

    public void registerCommands() {
        CommandManager cm = new CommandManager();
        getCommand("battle").setExecutor(cm);
        getCommand("b").setExecutor(cm);
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

    public void dontSave(boolean b) {
        this.dontSave = b;
    }

    private static Game loadGame(File saveFile) {
        Game game = null;
        if (saveFile.exists()) {
            XStream xstream = XStreamUtil.createXStream();
            try {
                game = (Game) xstream.fromXML(saveFile);
                System.out.println("Loaded " + BattleUtils.SAVE_FILE_NAME + "!");
            } catch (Exception e) {
                System.err.println("Error while trying to load " + BattleUtils.SAVE_FILE_NAME + "!");
                e.printStackTrace(System.err);
                game = new Game();
            }
        } else {
            game = new Game();
        }
        return game;
    }

    public void saveGame() {
        if (!dontSave) {
            if (game.isStarted()) {
                XStream xstream = XStreamUtil.createXStream();
                try {
                    xstream.toXML(BattlePlugin.game(), new FileOutputStream(new File(getDataFolder(), BattleUtils.SAVE_FILE_NAME)));
                } catch (Exception e) {
                    System.err.println("Error while trying to write " + BattleUtils.SAVE_FILE_NAME + "!");
                    e.printStackTrace(System.err);
                }
            }
        }
    }

    public static String prefix() {
        return ChatColor.GOLD + "[Battle] " + ChatColor.WHITE;
    }

    public static BattlePlugin instance() {
        return instance;
    }

    public static ConfigurationHelper configurationHelper() {
        return configurationHelper;
    }

    public static Game game() {
        return game;
    }

}
