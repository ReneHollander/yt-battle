package at.er.ytbattle.plugin.event;

import at.er.ytbattle.plugin.BattlePlugin;
import at.er.ytbattle.plugin.player.BattlePlayer;
import at.er.ytbattle.plugin.team.Team;
import at.er.ytbattle.util.PlayerArmor;
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

public class PlayerRespawnListener implements Listener {

    public PlayerRespawnListener() {
        Bukkit.getPluginManager().registerEvents(this, BattlePlugin.instance());
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onPlayerRespawn(PlayerRespawnEvent event) {
        BattlePlayer player = BattlePlugin.game().getBattlePlayerManager().getBattlePlayer(event.getPlayer());

        if (BattlePlugin.game().isStarted() && BattlePlugin.game().getTeamManager().isInTeam(player)) {
            Team t = BattlePlugin.game().getTeamManager().getTeamByPlayer(player);
            PlayerArmor armor = BattlePlugin.instance().playerArmor.get(player);

            if (armor != null) {
                player.getInventory().addItem(new ItemStack(Material.IRON_SWORD));
                player.getInventory().setHelmet(armor.getHelmet());
                player.getInventory().setChestplate(armor.getChestplate());
                player.getInventory().setLeggings(armor.getLeggings());
                player.getInventory().setBoots(armor.getBoots());
                BattlePlugin.instance().playerArmor.remove(player);
            }

            BattlePlugin.game().getInvincibilityTimerManager().createTimer(player);
            event.setRespawnLocation(BattlePlugin.game().getSpawn().getLocation());
            player.getInventory().addItem(new Wool(t.getTeamColor().getDyeColor()).toItemStack(1));
            player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 300, 2));
        }
    }

}
