package at.er.ytbattle.plugin.event;

import at.er.ytbattle.plugin.BattlePlugin;
import at.er.ytbattle.plugin.player.BattlePlayer;
import at.er.ytbattle.plugin.team.Team;
import at.er.ytbattle.util.LongWrapper;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.Wool;

import java.util.HashMap;

public class PlayerMoveListener implements Listener {

    private static final int CHECK_INVENTORY_MIN_DIFF_MS = 250;

    private HashMap<BattlePlayer, LongWrapper> lastMoveMap;

    public PlayerMoveListener() {
        this.lastMoveMap = new HashMap<>();

        Bukkit.getPluginManager().registerEvents(this, BattlePlugin.instance());
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        BattlePlayer player = BattlePlugin.game().getBattlePlayerManager().getBattlePlayer(event.getPlayer());

        if (BattlePlugin.game().isStarted()) {
            if (BattlePlugin.game().getTeamManager().isInTeam(player)) {
                if (event.getFrom().getX() != event.getTo().getX() && event.getFrom().getZ() != event.getTo().getZ()) {
                    this.check(player);
                }
            }
        }
    }

    public void check(BattlePlayer player) {
        LongWrapper lw = this.lastMoveMap.get(player);
        if (lw == null) {
            this.lastMoveMap.put(player, new LongWrapper(System.currentTimeMillis()));
            this.doActualCheck(player);
        } else {
            long diff = System.currentTimeMillis() - lw.get();
            if (diff > CHECK_INVENTORY_MIN_DIFF_MS) {
                lw.set(System.currentTimeMillis());
                this.doActualCheck(player);
            }
        }
    }

    public void doActualCheck(BattlePlayer player) {
        Team t = BattlePlugin.game().getTeamManager().getTeamByPlayer(player);
        boolean found = false;

        for (ItemStack is : player.getInventory().getContents()) {
            if (is != null && is.getType() == Material.WOOL) {
                DyeColor curr = ((Wool) is.getData()).getColor();
                if (curr == t.getTeamColor().getDyeColor()) {
                    found = true;
                    break;
                }
            }
        }
        if (!found) {
            player.sendMessage(BattlePlugin.prefix() + ChatColor.RED + "Your Inventory has to contain a wool of your color! Get another with /b life!");
        }
    }
}
