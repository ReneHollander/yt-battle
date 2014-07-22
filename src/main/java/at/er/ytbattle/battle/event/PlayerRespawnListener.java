package at.er.ytbattle.battle.event;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.Wool;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import at.er.ytbattle.battle.Battle;
import at.er.ytbattle.battle.Team;
import at.er.ytbattle.battle.player.BattlePlayer;
import at.er.ytbattle.battle.player.BattlePlayerManager;
import at.er.ytbattle.battle.timer.InvincibilityTimerManager;
import at.er.ytbattle.util.PlayerArmor;

public class PlayerRespawnListener implements Listener {

    public PlayerRespawnListener() {
        Bukkit.getPluginManager().registerEvents(this, Battle.instance());
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onPlayerRespawn(PlayerRespawnEvent event) {
        BattlePlayer player = BattlePlayerManager.instance().getBattlePlayer(event.getPlayer());

        if (Battle.instance().getGame().isStarted() && Battle.instance().getGame().getTeamManager().isInTeam(player)) {

            Team t = Battle.instance().getGame().getTeamManager().getTeamByPlayer(player);
            if (Battle.instance().playerArmor.get(player) != null) {
                PlayerArmor armor = Battle.instance().playerArmor.get(player);
                player.getInventory().addItem(new ItemStack(Material.IRON_SWORD));
                player.getInventory().setHelmet(armor.getHelmet());
                player.getInventory().setChestplate(armor.getChestplate());
                player.getInventory().setLeggings(armor.getLeggings());
                player.getInventory().setBoots(armor.getBoots());
                Battle.instance().playerArmor.remove(player);
            }

            InvincibilityTimerManager.instance().createTimer(player);
            event.setRespawnLocation(Battle.instance().getGame().getSpawn().getLocation());
            player.getInventory().addItem(new Wool(t.getTeamColor().getDyeColor()).toItemStack(1));
            player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 300, 2));
        }
    }

}
