package at.er.ytbattle.battle.event;

import org.bukkit.Bukkit;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

import at.er.ytbattle.battle.Battle;

public class EntityDeathListener implements Listener {

	public EntityDeathListener() {
		Bukkit.getPluginManager().registerEvents(this, Battle.instance());
	}

	@EventHandler(priority = EventPriority.HIGH)
	public void onEntityDeath(EntityDeathEvent event) {
		if (event.getEntityType() == EntityType.SHEEP)
			event.getDrops().clear();
	}

}
