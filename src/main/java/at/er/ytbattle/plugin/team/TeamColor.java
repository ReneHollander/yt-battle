package at.er.ytbattle.plugin.team;

import org.bukkit.ChatColor;
import org.bukkit.DyeColor;

public enum TeamColor {

    WHITE(ChatColor.WHITE, DyeColor.WHITE, "white", "White"),
    YELLOW(ChatColor.YELLOW, DyeColor.YELLOW, "yellow", "Yellow"),
    GREEN(ChatColor.GREEN, DyeColor.LIME, "green", "Green"),
    CYAN(ChatColor.AQUA, DyeColor.CYAN, "cyan", "Cyan"),
    PURPLE(ChatColor.DARK_PURPLE, DyeColor.PURPLE, "purple", "Purple"),
    BLUE(ChatColor.DARK_BLUE, DyeColor.BLUE, "blue", "Blue"),
    RED(ChatColor.RED, DyeColor.RED, "red", "Red"),
    MAGENTA(ChatColor.LIGHT_PURPLE, DyeColor.MAGENTA, "magenta", "Magenta"),
    ORANGE(ChatColor.GOLD, DyeColor.ORANGE, "orange", "Orange"),
    GRAY(ChatColor.GRAY, DyeColor.GRAY, "gray", "Gray");

    private ChatColor chatColor;
    private DyeColor dyeColor;
    private String shortName;
    private String longName;

    private TeamColor(ChatColor chatColor, DyeColor dyeColor, String shortName, String longname) {
        this.chatColor = chatColor;
        this.dyeColor = dyeColor;
        this.shortName = shortName;
        this.longName = longname;
    }

    public ChatColor getChatColor() {
        return this.chatColor;
    }

    public DyeColor getDyeColor() {
        return this.dyeColor;
    }

    public String getShortName() {
        return this.shortName;
    }

    public String getLongName() {
        return this.longName;
    }

    public static TeamColor getTeamColorByChatColor(ChatColor chatColor) {
        for (TeamColor tc : TeamColor.values()) {
            if (tc.getChatColor().equals(chatColor)) {
                return tc;
            }
        }
        return null;
    }

    public static TeamColor getTeamColorByDyeColor(DyeColor dyeColor) {
        for (TeamColor tc : TeamColor.values()) {
            if (tc.getDyeColor().equals(dyeColor)) {
                return tc;
            }
        }
        return null;
    }

    public static TeamColor getTeamByShortName(String shortName) {
        for (TeamColor tc : TeamColor.values()) {
            if (tc.getShortName().equals(shortName)) {
                return tc;
            }
        }
        return null;
    }

    public static TeamColor getTeamByLongName(String longName) {
        for (TeamColor tc : TeamColor.values()) {
            if (tc.getLongName().equals(longName)) {
                return tc;
            }
        }
        return null;
    }
}
