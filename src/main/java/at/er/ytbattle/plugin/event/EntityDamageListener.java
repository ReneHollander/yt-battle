package at.er.ytbattle.plugin.event;

import org.bukkit.Bukkit;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityCombustByBlockEvent;
import org.bukkit.event.entity.EntityCombustByEntityEvent;
import org.bukkit.event.entity.EntityCombustEvent;
import org.bukkit.event.entity.EntityDamageByBlockEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.ItemDespawnEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;

import at.er.ytbattle.plugin.BattlePlugin;

public class EntityDamageListener implements Listener {

    public EntityDamageListener() {
        Bukkit.getPluginManager().registerEvents(this, BattlePlugin.instance());
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onEntityDamage(EntityDamageEvent event) {
        if (event.getEntityType() == EntityType.DROPPED_ITEM)
            System.out.println(event);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
        if (event.getEntityType() == EntityType.DROPPED_ITEM)
            System.out.println(event);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onEntityDamageByBlock(EntityDamageByBlockEvent event) {
        if (event.getEntityType() == EntityType.DROPPED_ITEM)
            System.out.println(event);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onItemPickup(PlayerPickupItemEvent event) {
        System.out.println(event);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onItemDespawn(ItemDespawnEvent event) {
        if (event.getEntityType() == EntityType.DROPPED_ITEM)
            System.out.println(event);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onEntityCombust(EntityCombustEvent event) {
        if (event.getEntityType() == EntityType.DROPPED_ITEM)
            System.out.println(event);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onEntityCombust(EntityCombustByEntityEvent event) {
        if (event.getEntityType() == EntityType.DROPPED_ITEM)
            System.out.println(event);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onEntityCombust(EntityCombustByBlockEvent event) {
        if (event.getEntityType() == EntityType.DROPPED_ITEM)
            System.out.println(event);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onEntityExplode(EntityExplodeEvent event) {
        if (event.getEntityType() == EntityType.DROPPED_ITEM)
            System.out.println(event);
    }
}
