package at.er.ytbattle.plugin.event;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.Wool;

import at.er.ytbattle.plugin.BattlePlugin;
import at.er.ytbattle.plugin.player.BattlePlayer;
import at.er.ytbattle.plugin.team.Team;

public class PlayerMoveListener implements Listener {

    public PlayerMoveListener() {
        Bukkit.getPluginManager().registerEvents(this, BattlePlugin.instance());
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        BattlePlayer player = BattlePlugin.game().getBattlePlayerManager().getBattlePlayer(event.getPlayer());

        if (BattlePlugin.game().isStarted()) {
            if (BattlePlugin.game().getTeamManager().isInTeam(player)) {
                if (player.getInventory().contains(Material.WOOL)) {

                    Team t = BattlePlugin.game().getTeamManager().getTeamByPlayer(player);

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
                        player.sendMessage(BattlePlugin.prefix() + ChatColor.RED + "Your Inventory has to contain a wool of your color! Get another with /b life!");
                    }
                } else {
                    player.sendMessage(BattlePlugin.prefix() + ChatColor.RED + "Your Inventory has to contain a wool of your color! Get another with /b life!");
                }
            }
        }

    }

}
