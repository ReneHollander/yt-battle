package at.er.ytbattle.plugin.timer.manager;

import at.er.ytbattle.plugin.BattlePlugin;
import at.er.ytbattle.plugin.player.BattlePlayer;
import at.er.ytbattle.plugin.team.Team;
import at.er.ytbattle.plugin.timer.timeables.WoolPlaceTimer;
import at.er.ytbattle.util.timer.Timeable;
import org.bukkit.Sound;

public class WoolPlaceTimerManager {

    private Team team;

    public WoolPlaceTimerManager(Team team) {
        this.team = team;
    }

    public void woolPlace() {
        if (BattlePlugin.game().getTimerManager().getSize(this.team) > 0) {
            Timeable timeable = BattlePlugin.game().getTimerManager().findLongestRunningTimer(this.team);
            timeable.removeTimer();
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

    public void woolBreak() {
        WoolPlaceTimer bpt = new WoolPlaceTimer(this.team);
        BattlePlugin.game().getTimerManager().registerTimer(bpt);
        bpt.startTimer();
    }

    public int getRemainingWoolCount() {
        return BattlePlugin.game().getTimerManager().getSize(this.team);
    }

    public void setupInitialWool() {
        for (int i = 0; i < this.team.getPlayers().size(); i++) {
            WoolPlaceTimer bpt = new WoolPlaceTimer(this.team);
            BattlePlugin.game().getTimerManager().registerTimer(bpt);
            bpt.startTimer();
        }
    }

}
