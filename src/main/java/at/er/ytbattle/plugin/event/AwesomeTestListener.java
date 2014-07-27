package at.er.ytbattle.plugin.event;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.scheduler.BukkitRunnable;

import at.er.ytbattle.plugin.BattlePlugin;

public class AwesomeTestListener implements Listener {

    public AwesomeTestListener() {
        Bukkit.getPluginManager().registerEvents(this, BattlePlugin.instance());
    }

    @EventHandler
    public void onBlockPlace(final BlockPlaceEvent event) {

        final Material usedBlock = Material.DIAMOND_BLOCK;

        if (event.getBlock().getType() == usedBlock) {

            BukkitRunnable animation = new BukkitRunnable() {

                int tick = 0;

                Location loc = event.getBlock().getLocation();
                World world = loc.getWorld();

                public void run() {

                    if (tick == 0) {
                        world.playSound(loc, Sound.PORTAL_TRAVEL, 10, 1);
                        world.playEffect(loc, Effect.ENDER_SIGNAL, 1);
                        destoyBlocks(loc, tick + 1, usedBlock);
                    }

                    if (tick != 0 && tick < 10) {
                        world.getBlockAt(loc).setType(Material.AIR);
                        loc.add(0, 1, 0);
                        world.getBlockAt(loc).setType(Material.DIAMOND_BLOCK);
                        world.playEffect(loc, Effect.ENDER_SIGNAL, 1);
                        destoyBlocks(loc, tick + 1, usedBlock);
                    }

                    if (tick >= 10 && tick < 17) {
                        world.getBlockAt(loc).setType(Material.AIR);
                        loc.subtract(0, 1, 0);
                        world.getBlockAt(loc).setType(Material.DIAMOND_BLOCK);
                        world.playEffect(loc, Effect.ENDER_SIGNAL, 1);
                    }

                    tick++;
                }

            };
            Bukkit.getScheduler().scheduleSyncRepeatingTask(BattlePlugin.instance(), animation, 0, 1);
        }
    }

    private static void destoyBlocks(Location rmBlocks, int radius, Material dontDestroy) {
        rmBlocks = rmBlocks.clone();
        World world = rmBlocks.getWorld();
        for (int i = -radius; i <= radius; i++) {
            Location tmp1 = rmBlocks.clone();
            tmp1.add(i, 0, 0);
            for (int j = -radius; j <= radius; j++) {
                Location tmp2 = tmp1.clone();
                tmp2.add(0, 0, j);
                Block block = world.getBlockAt(tmp2);
                if (block.getType() != Material.AIR && block.getType() != dontDestroy) {
                    world.playEffect(tmp2, Effect.MOBSPAWNER_FLAMES, 1);
                    world.playEffect(tmp2, Effect.STEP_SOUND, block.getType());
                    block.breakNaturally();
                }
            }
        }
    }
}
