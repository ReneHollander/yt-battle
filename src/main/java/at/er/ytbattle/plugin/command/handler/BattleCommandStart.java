package at.er.ytbattle.plugin.command.handler;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.Wool;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import at.er.ytbattle.plugin.BattlePlugin;
import at.er.ytbattle.plugin.command.AbstractCommand;
import at.er.ytbattle.plugin.player.BattlePlayer;
import at.er.ytbattle.plugin.team.Team;
import at.er.ytbattle.plugin.timer.timeables.GraceTimer;
import at.er.ytbattle.plugin.timer.timeables.RemindTimer;
import at.er.ytbattle.util.BattleUtils;
import at.er.ytbattle.util.ConfigurationHelper;
import at.er.ytbattle.util.SerializableLocation;

public class BattleCommandStart extends AbstractCommand {

    @Override
    public boolean onCommand(String label, String[] args, BattlePlayer player) {
        if (BattlePlugin.game().isStarted() == false) {

            if (BattlePlugin.game().getTeamManager().getTeamCount() > 0) {

                Bukkit.broadcastMessage(BattlePlugin.prefix() + "The Battle has been started! Let the games begin!");

                int startlifes = BattlePlugin.configurationHelper().getConfigFile().getInt(ConfigurationHelper.GAME_STARTERLIFES_PATH);
                boolean reminder = BattlePlugin.configurationHelper().getConfigFile().getBoolean(ConfigurationHelper.TIMER_REMINDER_ENABLED_PATH);
                boolean baseItem = BattlePlugin.configurationHelper().getConfigFile().getBoolean(ConfigurationHelper.GAME_BASEBLOCK_ENABLED_PATH);

                if (reminder == true) {
                    new RemindTimer().startReminder();
                }

                if (BattlePlugin.game().getSpawn() == null) {
                    Location spawn = player.getLocation();
                    BattlePlugin.game().setSpawn(new SerializableLocation(spawn));
                    spawn.getWorld().setSpawnLocation((int) spawn.getX(), (int) spawn.getY(), (int) spawn.getZ());
                }

                try {
                    int timer = Integer.parseInt(args[0]);
                    BattlePlugin.game().getSpawn().getLocation().getWorld().setPVP(false);
                    new GraceTimer(timer * 60);
                } catch (Exception e) {
                }

                BattlePlugin.game().getSpawn().getLocation().getWorld().setTime(200);

                ItemStack base = new ItemStack(Material.QUARTZ_ORE);
                ItemMeta baseMeta = base.getItemMeta();
                baseMeta.setDisplayName(ChatColor.GRAY + "Base Block");
                baseMeta.setLore(Arrays.asList("Place me to create a base"));
                base.setItemMeta(baseMeta);

                for (Team t : BattlePlugin.game().getTeamManager().getTeams()) {
                    if (t.getTeamSize() > 0) {
                        t.setLifes(startlifes);
                        t.setupInitialWool();

                        for (BattlePlayer p : t.getPlayers()) {
                            p.closeInventory();
                            p.teleport(BattlePlugin.game().getSpawn().getLocation());
                            p.setGameMode(GameMode.SURVIVAL);
                            p.setAllowFlight(false);
                            p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 300, 1));
                            p.getInventory().clear();
                            p.getInventory().setHelmet(new ItemStack(Material.AIR));
                            p.getInventory().setChestplate(new ItemStack(Material.AIR));
                            p.getInventory().setLeggings(new ItemStack(Material.AIR));
                            p.getInventory().setBoots(new ItemStack(Material.AIR));
                            p.getInventory().addItem(new Wool(t.getTeamColor().getDyeColor()).toItemStack(2));
                            if (baseItem) {
                                p.getInventory().addItem(base);
                            }
                            p.setHealth(20);
                            p.setFoodLevel(20);
                            p.setSaturation(20);
                        }
                    }
                }

                BattlePlugin.game().setStarted(true);
                BattleUtils.setTags();
                BattleUtils.updateScoreboard();

                Location soundLocation = BattlePlugin.game().getSpawn().getLocation();

                soundLocation.getWorld().playSound(soundLocation, Sound.AMBIENCE_THUNDER, 10, 1);
                soundLocation.getWorld().playSound(soundLocation, Sound.EXPLODE, 10F, 0.5F);

                return true;
            } else {
                player.sendMessage(BattlePlugin.prefix() + "There have to be at least two teams with one or more Player(s) before the battle can be launched!");
            }
        } else {
            player.sendMessage(BattlePlugin.prefix() + "Battle has already been started!");
        }
        return true;
    }
}
