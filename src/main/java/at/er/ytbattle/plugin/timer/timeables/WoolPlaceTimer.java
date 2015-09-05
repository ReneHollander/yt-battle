package at.er.ytbattle.plugin.timer.timeables;

import at.er.ytbattle.plugin.BattlePlugin;
import at.er.ytbattle.plugin.player.BattlePlayer;
import at.er.ytbattle.plugin.team.Team;
import at.er.ytbattle.util.timer.Timeable;
import at.er.ytbattle.util.timer.TimerManager.TimeScale;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.List;

public class WoolPlaceTimer extends Timeable {

    private Team team;
    private List<Long> breakTimestamps;

    public WoolPlaceTimer(Team team) {
        super(team, TimeScale.SECOND, 1);
        this.team = team;
        this.breakTimestamps = new ArrayList<>();
    }

    @Override
    public void tick(long elapsedTime) {
        int duration = BattlePlugin.configurationHelper().getWoolTimeToPlace();
        if (elapsedTime == 0) {
            for (BattlePlayer player : team.getPlayers()) {
                if (player.hasPlayer()) {
                    player.sendMessage(BattlePlugin.prefix() + "You have " + ((duration - elapsedTime) / 60) + " minutes left to place a wool.");
                }
            }
        } else if (elapsedTime >= duration) {
            for (final BattlePlayer player : team.getPlayers()) {
                if (player.hasPlayer()) {
                    Bukkit.getScheduler().runTask(BattlePlugin.instance(), new Runnable() {
                        @Override
                        public void run() {
                            player.playSound(player.getLocation(), Sound.IRONGOLEM_HIT, 10, 0.5F);
                            player.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 40, 0));
                            player.sendMessage(BattlePlugin.prefix() + "Place a wool to disable the whiter effect!");
                        }
                    });
                }
            }
        } else {
            if (elapsedTime % 300 == 0) {
                for (BattlePlayer player : team.getPlayers()) {
                    if (player.hasPlayer()) {
                        player.sendMessage(BattlePlugin.prefix() + "You have " + ((duration - elapsedTime) / 60) + " minutes left to place a wool.");
                    }
                }
            } else if (elapsedTime % 60 == 0 && elapsedTime > duration - 300) {
                for (BattlePlayer player : team.getPlayers()) {
                    if (player.hasPlayer()) {
                        player.sendMessage(BattlePlugin.prefix() + "You have " + ((duration - elapsedTime) / 60) + " minutes left to place a wool.");
                    }
                }
            } else if (elapsedTime % 10 == 0 && elapsedTime > duration - 60) {
                for (BattlePlayer player : team.getPlayers()) {
                    if (player.hasPlayer()) {
                        player.sendMessage(BattlePlugin.prefix() + "You have " + (duration - elapsedTime) + " seconds left to place a wool.");
                    }
                }
            }
        }
    }

    public void woolPlace() {
        long oldest = getOldestWoolBreak();
        if (oldest > -1) {
            this.breakTimestamps.remove(oldest);
            for (BattlePlayer p : team.getPlayers()) {
                if (p.hasPlayer()) {
                    int remainingWoolCount = this.getRemainingWoolCount();
                    if (remainingWoolCount == 0) {
                        p.playSound(p.getLocation(), Sound.ENDERMAN_TELEPORT, 10, 1);
                        p.sendMessage(BattlePlugin.prefix() + "Wool was placed. You don't have to place any more wool for now!");
                    } else {
                        p.sendMessage(BattlePlugin.prefix() + "Wool was placed. Place " + this.getRemainingWoolCount() + " more to disable the timer.");
                    }
                    p.playSound(p.getLocation(), Sound.ENDERMAN_TELEPORT, 10, 1);
                }
            }
        }
    }

    public long getOldestWoolBreak() {
        long oldest = -1;
        for (long timestamp : this.breakTimestamps) {
            if (timestamp > oldest) {
                oldest = timestamp;
            }
        }
        return oldest;
    }

    public void woolBreak() {
        this.breakTimestamps.add(this.getElapsedTime());
    }

    public int getRemainingWoolCount() {
        return this.breakTimestamps.size();
    }

    public void setupInitialWool() {
        for (int i = 0; i < this.team.getPlayers().size(); i++) {
            this.breakTimestamps.add(this.getElapsedTime());
        }
    }

}
