package at.er.ytbattle.battle.timer.manager;

import org.bukkit.Sound;

import at.er.ytbattle.battle.Battle;
import at.er.ytbattle.battle.Team;
import at.er.ytbattle.battle.player.BattlePlayer;
import at.er.ytbattle.battle.timer.timeables.BlockPlaceTimer;
import at.er.ytbattle.util.timer.Timeable;

public class BlockPlaceTimerManager {

    private Team team;
    private int timetoplace;

    public BlockPlaceTimerManager(Team team, int timetoplace) {
        this.team = team;
        this.timetoplace = timetoplace;
    }

    public void woolPlace() {
        if (Battle.instance().getGame().getTimerManager().getSize(this.team) > 0) {
            Timeable timeable = Battle.instance().getGame().getTimerManager().findLongestRunningTimer(this.team);
            timeable.removeTimer();
            for (BattlePlayer p : team.getPlayers()) {
                p.playSound(p.getLocation(), Sound.ENDERMAN_TELEPORT, 10, 1);
                int remainingWoolCount = this.getRemainingWoolCount();
                if (remainingWoolCount == 0) {
                    p.sendMessage(Battle.prefix() + "Wool was placed. You don't have to place any more wool for now!");
                } else {
                    p.sendMessage(Battle.prefix() + "Wool was placed. Place " + this.getRemainingWoolCount() + " more to disable the timer.");
                }
            }
        }
    }

    public void woolBreak() {
        BlockPlaceTimer bpt = new BlockPlaceTimer(this.team, this.timetoplace);
        Battle.instance().getGame().getTimerManager().registerTimer(bpt);
        bpt.startTimer();
    }

    public int getRemainingWoolCount() {
        return Battle.instance().getGame().getTimerManager().getSize(this.team);
    }

    public void setupInitialWool() {
        for (int i = 0; i < this.team.getPlayers().size(); i++) {
            BlockPlaceTimer bpt = new BlockPlaceTimer(this.team, this.timetoplace);
            Battle.instance().getGame().getTimerManager().registerTimer(bpt);
            bpt.startTimer();
        }
    }

}
