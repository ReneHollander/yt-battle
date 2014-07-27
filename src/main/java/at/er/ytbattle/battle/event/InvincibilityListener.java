package at.er.ytbattle.battle.event;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;

import at.er.ytbattle.battle.Battle;
import at.er.ytbattle.battle.player.BattlePlayer;
import at.er.ytbattle.battle.timer.timeables.InvincibilityTimer;

public class InvincibilityListener implements Listener {

    public InvincibilityListener() {
        Bukkit.getPluginManager().registerEvents(this, Battle.instance());
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerDeath(PlayerDeathEvent event) {
        BattlePlayer victim = Battle.game().getBattlePlayerManager().getBattlePlayer((Player) event.getEntity());
        InvincibilityTimer victimTimer = Battle.game().getInvincibilityTimerManager().getTimerByPlayer(victim);
        if (victimTimer != null) {
            victimTimer.removeTimer();
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onEntityDamage(EntityDamageByEntityEvent event) {
        if ((event.getEntity() instanceof Player) && (event.getDamager() instanceof Player)) {
            BattlePlayer victim = Battle.game().getBattlePlayerManager().getBattlePlayer((Player) event.getEntity());
            BattlePlayer damager = Battle.game().getBattlePlayerManager().getBattlePlayer((Player) event.getDamager());
            InvincibilityTimer victimTimer = Battle.game().getInvincibilityTimerManager().getTimerByPlayer(victim);
            InvincibilityTimer damagerTimer = Battle.game().getInvincibilityTimerManager().getTimerByPlayer(damager);

            if (victimTimer != null && damagerTimer != null) {
                damager.sendMessage(Battle.prefix() + victim.getName() + " died shortly before. He is invincible!");
                event.setCancelled(true);
            } else if (victimTimer != null) {
                damager.sendMessage(Battle.prefix() + victim.getName() + " died shortly before. He is invincible!");
                event.setCancelled(true);
            } else if (damagerTimer != null) {
                damager.sendMessage(Battle.prefix() + "You damaged " + victim.getName() + ". You have lost your invincibility!");
                damagerTimer.removeTimer();
            }
        } else if ((event.getDamager() instanceof Projectile) && (event.getEntity() instanceof Player)) {
            Projectile projectile = (Projectile) event.getDamager();
            if (projectile.getShooter() instanceof Player) {
                BattlePlayer victim = Battle.game().getBattlePlayerManager().getBattlePlayer((Player) event.getEntity());
                BattlePlayer damager = Battle.game().getBattlePlayerManager().getBattlePlayer((Player) projectile.getShooter());
                InvincibilityTimer victimTimer = Battle.game().getInvincibilityTimerManager().getTimerByPlayer(victim);
                InvincibilityTimer damagerTimer = Battle.game().getInvincibilityTimerManager().getTimerByPlayer(damager);

                if (victimTimer != null && damagerTimer != null) {
                    damager.sendMessage(Battle.prefix() + victim.getName() + " died shortly before. He is invincible!");
                    event.setCancelled(true);
                } else if (victimTimer != null) {
                    damager.sendMessage(Battle.prefix() + victim.getName() + " died shortly before. He is invincible!");
                    event.setCancelled(true);
                } else if (damagerTimer != null) {
                    damager.sendMessage(Battle.prefix() + "You damaged " + victim.getName() + ". You have lost your invincibility!");
                    damagerTimer.removeTimer();
                }
            }
        }
    }
}
