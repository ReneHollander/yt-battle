package at.er.ytbattle.battle.timer.timeables;

import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;

import at.er.ytbattle.battle.Battle;
import at.er.ytbattle.battle.player.BattlePlayer;
import at.er.ytbattle.battle.player.PlayerUtil;
import at.er.ytbattle.battle.timer.manager.InvincibilityTimerManager;
import at.er.ytbattle.util.timer.Timeable;
import at.er.ytbattle.util.timer.TimerManager.TimeScale;

public class InvincibilityTimer extends Timeable implements Listener {

    private BattlePlayer player;
    private int duration;

    public InvincibilityTimer(BattlePlayer player, int duration) {
        super(InvincibilityTimerManager.MANAGER_ID, TimeScale.MINUTE, 1, player);
        this.player = player;
        this.duration = duration;
    }

    @Override
    public void stopTimer() {
        super.stopTimer();
        HandlerList.unregisterAll(this);
    }

    @Override
    public void startTimer() {
        super.startTimer();
        Battle.instance().getServer().getPluginManager().registerEvents(this, Battle.instance());
    }

    @Override
    public void removeTimer() {
        super.removeTimer();
        HandlerList.unregisterAll(this);
    }

    @Override
    public void resetTimer() {
        super.resetTimer();
        HandlerList.unregisterAll(this);
    }

    @Override
    public void pauseTimer() {
        super.pauseTimer();
        HandlerList.unregisterAll(this);
    }

    public void resumeTimer() {
        super.resumeTimer();
        Battle.instance().getServer().getPluginManager().registerEvents(this, Battle.instance());
    }

    // TODO move events to aproriate event handler class

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerDeath(PlayerDeathEvent event) {
        if (PlayerUtil.areEqual(event.getEntity(), player)) {
            this.removeTimer();
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onEntityDamage(EntityDamageByEntityEvent event) {
        if ((event.getEntity() instanceof Player) && (event.getDamager() instanceof Player)) {
            Player victim = (Player) event.getEntity();
            Player damager = (Player) event.getDamager();
            if (PlayerUtil.areEqual(victim, player)) {
                damager.sendMessage(Battle.prefix() + victim.getName() + " died shortly before. He is invincible!");
                event.setCancelled(true);
            }
            if (PlayerUtil.areEqual(damager, player)) {
                damager.sendMessage(Battle.prefix() + "You damaged " + victim.getName() + ". You have lost your invincibility!");
                this.removeTimer();
            }
        } else if ((event.getDamager() instanceof Projectile) && (event.getEntity() instanceof Player)) {
            Projectile projectile = (Projectile) event.getDamager();
            if (projectile.getShooter() instanceof Player) {
                Player victim = (Player) event.getEntity();
                Player damager = (Player) projectile.getShooter();
                if (PlayerUtil.areEqual(victim, player)) {
                    damager.sendMessage(Battle.prefix() + victim.getName() + " died shortly before. He is invincible!");
                    event.setCancelled(true);
                }
                if (PlayerUtil.areEqual(damager, player)) {
                    damager.sendMessage(Battle.prefix() + "You damaged " + victim.getName() + ". You have lost your invincibility!");
                    this.removeTimer();
                }
            }
        }
    }

    @Override
    public void tick(long elapsedTime) {
        if (this.duration == elapsedTime) {
            player.sendMessage(Battle.prefix() + "Your invincibility ended!");
            this.removeTimer();
        } else {
            player.sendMessage(Battle.prefix() + "Your invincibility ends in " + (this.duration - elapsedTime) + " minutes!");
        }
    }
}
