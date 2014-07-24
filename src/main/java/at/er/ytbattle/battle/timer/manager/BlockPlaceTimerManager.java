package at.er.ytbattle.battle.timer.manager;

import java.util.HashSet;

import org.bukkit.Sound;

import at.er.ytbattle.battle.Battle;
import at.er.ytbattle.battle.Reference;
import at.er.ytbattle.battle.Team;
import at.er.ytbattle.battle.player.BattlePlayer;
import at.er.ytbattle.battle.timer.timeables.BlockPlaceTimer;
import at.er.ytbattle.util.timer.Timeable;

public class BlockPlaceTimerManager {

    private Team team;
    private int timetoplace;

    private HashSet<Timeable> timers;

    public BlockPlaceTimerManager(Team team, int timetoplace) {
        this.team = team;
        this.timetoplace = timetoplace;

        Battle.instance();
        Battle.instance().getGame().toString();
        Battle.instance().getGame().getTimerManager();
        Battle.instance().getGame().getTimerManager().toString();
        this.timers = Battle.instance().getGame().getTimerManager().getTimers(Reference.BLOCK_PLACE_TIMER_MANAGEER_ID);
    }

    public void woolPlace() {
        if (this.timers.size() > 0) {
            Timeable timeable = this.findFirstTimer();
            timeable.removeTimer();
            for (BattlePlayer p : team.getPlayers()) {
                p.playSound(p.getLocation(), Sound.ENDERMAN_TELEPORT, 10, 1);
                p.sendMessage(Battle.prefix() + "Wool was placed. Place " + this.getRemainingWoolCount() + " more to disable the timer.");
            }
        }
    }

    public void woolBreak() {
        BlockPlaceTimer bpt = new BlockPlaceTimer(this.team, this.timetoplace);
        Battle.instance().getGame().getTimerManager().registerTimer(bpt);
        bpt.startTimer();
    }

    private Timeable findFirstTimer() {
        long ref = this.timetoplace;
        Timeable last = null;
        for (Timeable timeable : this.timers) {
            if (timeable.getElapsedTime() >= ref) {
                last = timeable;
                ref = timeable.getElapsedTime();
            }
        }
        return last;
    }

    public int getRemainingWoolCount() {
        return this.timers.size();
    }

    public void setupInitialWool() {
        for (int i = 0; i < this.team.getPlayers().size(); i++) {
            BlockPlaceTimer bpt = new BlockPlaceTimer(this.team, this.timetoplace);
            Battle.instance().getGame().getTimerManager().registerTimer(bpt);
            bpt.startTimer();
        }
    }

}
