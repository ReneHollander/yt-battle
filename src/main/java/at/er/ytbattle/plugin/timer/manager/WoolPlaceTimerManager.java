package at.er.ytbattle.plugin.timer.manager;

import org.bukkit.Sound;

import at.er.ytbattle.plugin.BattlePlugin;
import at.er.ytbattle.plugin.Team;
import at.er.ytbattle.plugin.player.BattlePlayer;
import at.er.ytbattle.plugin.timer.timeables.WoolPlaceTimer;
import at.er.ytbattle.util.timer.Timeable;

public class WoolPlaceTimerManager {

    private Team team;
    private int timetoplace;

    public WoolPlaceTimerManager(Team team, int timetoplace) {
        this.team = team;
        this.timetoplace = timetoplace;
    }

    public void woolPlace() {
        if (BattlePlugin.game().getTimerManager().getSize(this.team) > 0) {
            Timeable timeable = BattlePlugin.game().getTimerManager().findLongestRunningTimer(this.team);
            timeable.removeTimer();
            for (BattlePlayer p : team.getPlayers()) {
                p.playSound(p.getLocation(), Sound.ENDERMAN_TELEPORT, 10, 1);
                int remainingWoolCount = this.getRemainingWoolCount();
                if (remainingWoolCount == 0) {
                    p.sendMessage(BattlePlugin.prefix() + "Wool was placed. You don't have to place any more wool for now!");
                } else {
                    p.sendMessage(BattlePlugin.prefix() + "Wool was placed. Place " + this.getRemainingWoolCount() + " more to disable the timer.");
                }
            }
        }
    }

    public void woolBreak() {
        WoolPlaceTimer bpt = new WoolPlaceTimer(this.team, this.timetoplace);
        BattlePlugin.game().getTimerManager().registerTimer(bpt);
        bpt.startTimer();
    }

    public int getRemainingWoolCount() {
        return BattlePlugin.game().getTimerManager().getSize(this.team);
    }

    public void setupInitialWool() {
        for (int i = 0; i < this.team.getPlayers().size(); i++) {
            WoolPlaceTimer bpt = new WoolPlaceTimer(this.team, this.timetoplace);
            BattlePlugin.game().getTimerManager().registerTimer(bpt);
            bpt.startTimer();
        }
    }

}
