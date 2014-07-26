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
import at.er.ytbattle.util.PlayerArmor;

public class PlayerRespawnListener implements Listener {

    public PlayerRespawnListener() {
        Bukkit.getPluginManager().registerEvents(this, Battle.instance());
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onPlayerRespawn(PlayerRespawnEvent event) {
        BattlePlayer player = Battle.game().getBattlePlayerManager().getBattlePlayer(event.getPlayer());

        if (Battle.game().isStarted() && Battle.game().getTeamManager().isInTeam(player)) {

            Team t = Battle.game().getTeamManager().getTeamByPlayer(player);
            if (Battle.instance().playerArmor.get(player) != null) {
                PlayerArmor armor = Battle.instance().playerArmor.get(player);
                player.getInventory().addItem(new ItemStack(Material.IRON_SWORD));
                player.getInventory().setHelmet(armor.getHelmet());
                player.getInventory().setChestplate(armor.getChestplate());
                player.getInventory().setLeggings(armor.getLeggings());
                player.getInventory().setBoots(armor.getBoots());
                Battle.instance().playerArmor.remove(player);
            }

            Battle.game().getInvincibilityTimerManager().createTimer(player);
            event.setRespawnLocation(Battle.game().getSpawn().getLocation());
            player.getInventory().addItem(new Wool(t.getTeamColor().getDyeColor()).toItemStack(1));
            player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 300, 2));
        }
    }

}
