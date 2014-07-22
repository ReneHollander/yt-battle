package at.er.ytbattle.battle.event;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;

import at.er.ytbattle.battle.Battle;

public class EntityExplodeListener implements Listener {

    public EntityExplodeListener() {
        Bukkit.getPluginManager().registerEvents(this, Battle.instance());
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onEntityExplode(EntityExplodeEvent event) {
        if (event.getEntityType() == EntityType.CREEPER)
            event.blockList().clear();
        for (Block block : event.blockList()) {
            if (block.getType() == Material.WOOL || block.getType() == Material.GLASS)
                event.blockList().remove(block);
        }
    }

}
