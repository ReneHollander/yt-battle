package at.er.ytbattle.battle.timer;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import at.er.ytbattle.battle.Battle;

public class InvincibilityTimer implements Runnable, Listener {

	private String player;
	private int time;
	
	public InvincibilityTimer(String pl, int time) {
		this.player = pl;
		this.time = time;
	}
	
	public void run() {
		if (time == 0) {
			Bukkit.getPlayer(player).sendMessage(Battle.prefix() + "Your invincibility ended!");
			HandlerList.unregisterAll(this);
			time--;
		}
		
		if (time > 0) {
			Bukkit.getPlayer(player).sendMessage(Battle.prefix() + "Your invincibility end in " + time + "minutes!");
			time--;
		}
	}

	@EventHandler
	public void onEntityDamage(EntityDamageByEntityEvent event) {
		if (event.getEntity() instanceof Player) {
			
			Player player = (Player) event.getEntity();
			
			if (player.getName().equals(player) && event.getDamager() instanceof Player) {
				Player damager = (Player) event.getDamager();
				
				damager.sendMessage(Battle.prefix() + player.getName() + " died shortly before. He is invincible!");
				event.setCancelled(true);
			}
		}
	}
}
