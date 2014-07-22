package at.er.ytbattle.battle.event;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.material.Wool;

import at.er.ytbattle.battle.Battle;
import at.er.ytbattle.battle.Team;
import at.er.ytbattle.battle.player.BattlePlayer;
import at.er.ytbattle.battle.player.BattlePlayerManager;
import at.er.ytbattle.battle.timer.InvincibilityTimerManager;

public class BlockBreakListener implements Listener {

    public BlockBreakListener() {
        Bukkit.getPluginManager().registerEvents(this, Battle.instance());
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onBlockBreak(BlockBreakEvent event) {
        BattlePlayer player = BattlePlayerManager.instance().getBattlePlayer(event.getPlayer());
        if (event.getBlock().getType() == Material.WOOL && Battle.instance().getGame().isStarted() && Battle.instance().getGame().getTeamManager().isInTeam(player)) {
            DyeColor color = ((Wool) event.getBlock().getState().getData()).getColor();
            Team t = Battle.instance().getGame().getTeamManager().getTeamByPlayer(player);

            Team victim = Battle.instance().getGame().getTeamManager().getTeamByDyeColor(color);
            if (t.getTeamColor().getDyeColor().equals(color)) {
                player.sendMessage(Battle.prefix() + "You can't break your own team's wool!");
                event.setCancelled(true);
            } else {
                victim.getBlockPlaceTimerManager().woolBreak();

                if (InvincibilityTimerManager.instance().timerRunning(player)) {
                    InvincibilityTimerManager.instance().stopTimer(player);
                    player.sendMessage(Battle.prefix() + "You have lost your invincibility!");
                }

                Bukkit.broadcastMessage(Battle.prefix() + player.getName() + " destroyed a " + victim.getTeamColor().getChatColor() + victim.getTeamColor().getLongName() + ChatColor.RESET + " wool!");
            }
        }
    }

}
