package at.er.ytbattle.plugin.timer.timeables;

import org.bukkit.Sound;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import at.er.ytbattle.plugin.BattlePlugin;
import at.er.ytbattle.plugin.Team;
import at.er.ytbattle.plugin.player.BattlePlayer;
import at.er.ytbattle.util.timer.Timeable;
import at.er.ytbattle.util.timer.TimerManager.TimeScale;

public class WoolPlaceTimer extends Timeable {

    private Team team;
    private int timeToPlace;

    public WoolPlaceTimer(Team team, int timeToPlace) {
        super(team, TimeScale.SECOND, 1);
        this.team = team;
        this.timeToPlace = timeToPlace;
    }

    @Override
    public void tick(long elapsedTime) {
        if (elapsedTime == 0) {
            for (BattlePlayer player : team.getPlayers()) {
                player.sendMessage(BattlePlugin.prefix() + "You have " + ((this.timeToPlace - elapsedTime) / 60) + " minutes left to place a wool.");
            }
        } else if (elapsedTime >= this.timeToPlace) {
            for (BattlePlayer player : team.getPlayers()) {
                if (player.hasPlayer()) {
                    player.playSound(player.getLocation(), Sound.IRONGOLEM_HIT, 10, 0.5F);
                    player.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 40, 0));
                    player.sendMessage(BattlePlugin.prefix() + "Place a wool to disable the whiter effect!");
                }
            }
        } else {
            if (elapsedTime % 300 == 0) {
                for (BattlePlayer player : team.getPlayers()) {
                    if (player.hasPlayer()) {
                        player.sendMessage(BattlePlugin.prefix() + "You have " + ((this.timeToPlace - elapsedTime) / 60) + " minutes left to place a wool.");
                    }
                }
            } else if (elapsedTime % 60 == 0 && elapsedTime > this.timeToPlace - 300) {
                for (BattlePlayer player : team.getPlayers()) {
                    if (player.hasPlayer()) {
                        player.sendMessage(BattlePlugin.prefix() + "You have " + ((this.timeToPlace - elapsedTime) / 60) + " minutes left to place a wool.");
                    }
                }
            } else if (elapsedTime % 10 == 0 && elapsedTime > this.timeToPlace - 60) {
                for (BattlePlayer player : team.getPlayers()) {
                    if (player.hasPlayer()) {
                        player.sendMessage(BattlePlugin.prefix() + "You have " + (this.timeToPlace - elapsedTime) + " seconds left to place a wool.");
                    }
                }
            }
        }
    }
}
