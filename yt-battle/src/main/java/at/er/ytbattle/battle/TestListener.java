package at.er.ytbattle.battle;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.ItemDespawnEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;

public class TestListener implements Listener {

	@EventHandler
	public void onEntityDamage(EntityDamageEvent event) {
		Entity e = event.getEntity();
		if (e instanceof Item) {
			System.out.println("Item " + e.getEntityId() + " got damaged with " + event.getCause().toString());
		}
	}

	@EventHandler
	public void onItemPickup(PlayerPickupItemEvent event) {
		System.out.println("Item " + event.getItem().getEntityId() + " got picked up from " + event.getPlayer().getName() + " with event name " + event.getEventName());
	}

	@EventHandler
	public void onItemDespawn(ItemDespawnEvent event) {
		System.out.println("Item " + event.getEntity().getEntityId() + " despawned at location " + event.getLocation().toString());
	}

}
