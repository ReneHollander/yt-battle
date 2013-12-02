package at.er.ytbattle.battle.timer;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import at.er.ytbattle.battle.Battle;

public class InvincibilityTimer implements Runnable, Listener {

	private Battle plugin;
	private String player;
	private int time;
	
	private int handle;

	public InvincibilityTimer(Battle b, String pl, int time) {
		this.plugin = b;
		this.player = pl;
		this.time = time;
		handle = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, this, 0L, 1200L);
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}

	public void run() {
		if (time == 0) {
			Bukkit.getPlayer(player).sendMessage(Battle.prefix() + "Your invincibility ended!");
			HandlerList.unregisterAll(this);
			time--;
		}

		if (time > 0) {
			Bukkit.getPlayer(player).sendMessage(Battle.prefix() + "Your invincibility ends in " + time + " minutes!");
			time--;
		}
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onEntityDamage(EntityDamageByEntityEvent event) {
		if ((event.getEntity() instanceof Player) && (event.getDamager() instanceof Player)) {
			Player victim = (Player) event.getEntity();
			Player damager = (Player) event.getDamager();
			if (victim.getName().equals(player)) {
				damager.sendMessage(Battle.prefix() + victim.getName() + " died shortly before. He is invincible!");
				event.setCancelled(true);
			}
			if (damager.getName().equals(player)) {
				damager.sendMessage(Battle.prefix() + "You damaged " + victim.getName() + ". You have lost your invincibility!");
				HandlerList.unregisterAll(this);
				Bukkit.getScheduler().cancelTask(handle);
			}
		}
	}
}
