package at.er.ytbattle.util;

import at.er.ytbattle.plugin.BattlePlugin;
import at.er.ytbattle.plugin.player.BattlePlayer;
import at.er.ytbattle.plugin.team.Team;
import com.google.common.base.Functions;
import com.google.common.collect.Lists;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class BattleUtils {

    public static final String SAVE_FILE_NAME = "savegame.xml";

    private static ItemStack[] starterChestContents;

    private static Scoreboard scoreboardBattle;
    private static Objective objectiveBattleStats;

    @SuppressWarnings("deprecation")
    public static ItemStack[] getStarterChestContents() {
        if (starterChestContents == null) {
            List<?> confList = BattlePlugin.configurationHelper().getConfigFile().getList(ConfigurationHelper.GAME_BASEBLOCK_CONTENTS_PATH);
            ArrayList<ItemStack> contents = new ArrayList<ItemStack>();
            try {
                List<String> itemIDs = Lists.transform(confList, Functions.toStringFunction());
                for (String s : itemIDs) {
                    String[] cont = s.split(":");
                    if (cont.length > 1) {
                        ItemStack item = new ItemStack(Integer.parseInt(cont[0]), Integer.parseInt(cont[1]));
                        contents.add(item);
                    } else if (cont.length > 0) {
                        ItemStack item = new ItemStack(Integer.parseInt(cont[0]));
                        contents.add(item);
                    }
                }
            } catch (Exception ex) {
                System.err.println("Error occured while loading base block chest content from config");
                return null;
            }
            starterChestContents = contents.toArray(new ItemStack[contents.size()]);
        }
        return starterChestContents;
    }

    public static void unsetTags() {
        for (Player p : Bukkit.getOnlinePlayers()) {
            p.setPlayerListName(p.getName());
            p.setDisplayName(p.getName());
        }
    }

    public static void setTags() {
        for (BattlePlayer player : BattlePlugin.game().getBattlePlayerManager().getAllBattlePlayers()) {
            setDisplayAndListName(player);
        }
    }

    public static void setDisplayAndListName(BattlePlayer player) {
        if (player != null && player.hasPlayer()) {
            Team t = BattlePlugin.game().getTeamManager().getTeamByPlayer(player);
            if (t != null) {
                String display = t.getTeamColor().getChatColor() + player.getName();
                if (display.length() > 15) {
                    display = display.substring(0, 15);
                }
                player.setPlayerListName(display);
                player.setDisplayName(display + ChatColor.RESET);
            }
        }
    }

    public static Scoreboard getBattleScoreboard() {
        if (scoreboardBattle == null) {
            scoreboardBattle = Bukkit.getScoreboardManager().getNewScoreboard();
            for (Player p : Bukkit.getOnlinePlayers()) {
                addToScoreboard(p);
            }
        }
        return scoreboardBattle;
    }

    public static Objective getBattleStatsObjective() {
        if (objectiveBattleStats == null) {
            objectiveBattleStats = getBattleScoreboard().registerNewObjective("stats", "dummy");
            objectiveBattleStats.setDisplaySlot(DisplaySlot.SIDEBAR);
        }
        return objectiveBattleStats;
    }

    public static void addToScoreboard(Player p) {
        p.setScoreboard(getBattleScoreboard());
    }

    public static void updateScoreboard() {
        Scoreboard scoreboard = getBattleScoreboard();
        Objective objective = getBattleStatsObjective();
        if (BattlePlugin.game().isStarted() == false) {
            if (objective.getDisplayName().equals(ChatColor.BOLD + "Battle Teamstats")) {
                for (String entry : scoreboard.getEntries()) {
                    scoreboard.resetScores(entry);
                }
            }
            objective.setDisplayName(ChatColor.BOLD + "Battle Infos");
            objective.getScore(ChatColor.ITALIC + "Battle v" + getShortVersion()).setScore(9);
            objective.getScore(ChatColor.ITALIC + "").setScore(8);
            objective.getScore(ChatColor.ITALIC + "by").setScore(7);
            objective.getScore(ChatColor.ITALIC + "EXSolo").setScore(6);
            objective.getScore(ChatColor.ITALIC + "Rene8888").setScore(5);
            objective.getScore(ChatColor.ITALIC + "").setScore(4);
            objective.getScore(ChatColor.ITALIC + "Download:").setScore(3);
            objective.getScore(ChatColor.ITALIC + "bit.ly").setScore(2);
            objective.getScore(ChatColor.ITALIC + "/battleplugin").setScore(1);
        } else {
            if (objective.getDisplayName().equals(ChatColor.BOLD + "Battle Infos")) {
                for (String entry : scoreboard.getEntries()) {
                    scoreboard.resetScores(entry);
                }
            }
            objective.setDisplayName(ChatColor.BOLD + "Battle Teamstats");
            for (Team t : BattlePlugin.game().getTeamManager().getTeams()) {
                if (t.getPlayers().size() > 0) {
                    objective.getScore(t.getTeamColor().getChatColor() + "Team " + t.getTeamColor().getLongName()).setScore(t.getLifes());
                } else {
                    scoreboard.resetScores(t.getTeamColor().getChatColor() + "Team " + t.getTeamColor().getLongName());
                }
            }
        }
    }

    public static ItemStack removeEnchants(ItemStack input) {
        for (Enchantment enchantment : input.getEnchantments().keySet()) {
            input.removeEnchantment(enchantment);
        }
        return input;
    }

    public static void respawnPlayer(Player p, int delay) {
        final Player player = (Player) p.getPlayer();

        new BukkitRunnable() {
            @Override
            public void run() {
                try {
                    Object nmsPlayer = player.getClass().getMethod("getHandle").invoke(player);
                    Object packet = Class.forName(nmsPlayer.getClass().getPackage().getName() + ".PacketPlayInClientCommand").newInstance();
                    Class<?> enumClass = Class.forName(nmsPlayer.getClass().getPackage().getName() + ".EnumClientCommand");
                    for (Object ob : enumClass.getEnumConstants()) {
                        if (ob.toString().equals("PERFORM_RESPAWN")) {
                            packet = packet.getClass().getConstructor(enumClass).newInstance(ob);
                        }
                    }
                    Object con = nmsPlayer.getClass().getField("playerConnection").get(nmsPlayer);
                    con.getClass().getMethod("a", packet.getClass()).invoke(con, packet);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.runTaskLater(BattlePlugin.instance(), delay);
    }

    public static File getSaveFile() {
        return new File(BattlePlugin.instance().getDataFolder(), SAVE_FILE_NAME);
    }

    public static String getShortVersion() {
        String version = BattlePlugin.instance().getDescription().getVersion();
        version = version.substring(0, version.indexOf('-'));
        return version;
    }

}
