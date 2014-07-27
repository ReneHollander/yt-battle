package at.er.ytbattle.plugin.event;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.material.Wool;

import at.er.ytbattle.plugin.BattlePlugin;
import at.er.ytbattle.plugin.Team;
import at.er.ytbattle.plugin.player.BattlePlayer;

public class BlockBreakListener implements Listener {

    public BlockBreakListener() {
        Bukkit.getPluginManager().registerEvents(this, BattlePlugin.instance());
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onBlockBreak(BlockBreakEvent event) {
        BattlePlayer player = BattlePlugin.game().getBattlePlayerManager().getBattlePlayer(event.getPlayer());
        if (event.getBlock().getType() == Material.WOOL && BattlePlugin.game().isStarted() && BattlePlugin.game().getTeamManager().isInTeam(player)) {
            DyeColor color = ((Wool) event.getBlock().getState().getData()).getColor();
            Team t = BattlePlugin.game().getTeamManager().getTeamByPlayer(player);

            Team victim = BattlePlugin.game().getTeamManager().getTeamByDyeColor(color);
            if (t.getTeamColor().getDyeColor().equals(color)) {
                player.sendMessage(BattlePlugin.prefix() + "You can't break your own team's wool!");
                event.setCancelled(true);
            } else {
                victim.getBlockPlaceTimerManager().woolBreak();

                if (BattlePlugin.game().getInvincibilityTimerManager().timerRunning(player)) {
                    BattlePlugin.game().getInvincibilityTimerManager().stopTimer(player);
                    player.sendMessage(BattlePlugin.prefix() + "You have lost your invincibility!");
                }

                Bukkit.broadcastMessage(BattlePlugin.prefix() + player.getName() + " destroyed a " + victim.getTeamColor().getChatColor() + victim.getTeamColor().getLongName() + ChatColor.RESET + " wool!");
            }
        }
    }

}
