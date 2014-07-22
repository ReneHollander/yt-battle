package at.er.ytbattle.battle.event;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import at.er.ytbattle.battle.Battle;
import at.er.ytbattle.battle.Team;
import at.er.ytbattle.battle.player.BattlePlayer;
import at.er.ytbattle.battle.player.BattlePlayerManager;

public class PlayerInteractListener implements Listener {

    public PlayerInteractListener() {
        Bukkit.getPluginManager().registerEvents(this, Battle.instance());
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onPlayerInteract(PlayerInteractEvent event) {
        BattlePlayer player = BattlePlayerManager.instance().getBattlePlayer(event.getPlayer());
        if ((event.getAction() == Action.RIGHT_CLICK_BLOCK || event.getAction() == Action.RIGHT_CLICK_AIR) && player.getItemInHand().getType() == Material.GHAST_TEAR && Battle.instance().getGame().isStarted()) {
            if (Battle.instance().getGame().getTeamManager().isInTeam(player) == false) {
                player.sendMessage(Battle.prefix() + "You left the battle, this item is worthless now!");
                return;
            }
            Team t = Battle.instance().getGame().getTeamManager().getTeamByPlayer(player);
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
            Battle.instance().updateScoreboard();
        }
    }

}
