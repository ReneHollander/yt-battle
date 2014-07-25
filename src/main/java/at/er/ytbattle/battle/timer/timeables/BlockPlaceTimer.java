package at.er.ytbattle.battle.timer.timeables;

import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import at.er.ytbattle.battle.Battle;
import at.er.ytbattle.battle.Team;
import at.er.ytbattle.battle.player.BattlePlayer;
import at.er.ytbattle.util.timer.Timeable;
import at.er.ytbattle.util.timer.TimerManager.TimeScale;

public class BlockPlaceTimer extends Timeable {

    private Team team;
    private int timeToPlace;

    public BlockPlaceTimer(Team team, int timeToPlace) {
        super(team, TimeScale.SECOND, 1);
        this.team = team;
        this.timeToPlace = timeToPlace;
    }

    @Override
    public void tick(long elapsedTime) {
        if (elapsedTime == 0) {
            for (BattlePlayer player : team.getPlayers()) {
                player.sendMessage(Battle.prefix() + "You have " + ((this.timeToPlace - elapsedTime) / 60) + " minutes left to place a wool.");
            }
        } else if (elapsedTime >= this.timeToPlace) {
            for (BattlePlayer player : team.getPlayers()) {
                player.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 40, 0));
                player.sendMessage(Battle.prefix() + "Place a wool to disable the whiter effect!");
            }
        } else {
            if (elapsedTime % 600 == 0) {
                for (BattlePlayer player : team.getPlayers()) {
                    player.sendMessage(Battle.prefix() + "You have " + ((this.timeToPlace - elapsedTime) / 60) + " minutes left to place a wool.");
                }
            } else if (elapsedTime % 60 == 0 && elapsedTime > this.timeToPlace - 600) {
                for (BattlePlayer player : team.getPlayers()) {
                    player.sendMessage(Battle.prefix() + "You have " + ((this.timeToPlace - elapsedTime) / 60) + " minutes left to place a wool.");
                }
            } else if (elapsedTime % 10 == 0 && elapsedTime > this.timeToPlace - 60) {
                for (BattlePlayer player : team.getPlayers()) {
                    player.sendMessage(Battle.prefix() + "You have " + (this.timeToPlace - elapsedTime) + " seconds left to place a wool.");
                }
            }
        }
    }
}
