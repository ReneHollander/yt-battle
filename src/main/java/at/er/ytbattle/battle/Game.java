package at.er.ytbattle.battle;

import at.er.ytbattle.battle.player.BattlePlayerManager;
import at.er.ytbattle.battle.timer.InvincibilityTimerManager;
import at.er.ytbattle.util.SerializableLocation;

public class Game {

    private BattlePlayerManager battlePlayerManager;
    private TeamManager teamManager;
    private InvincibilityTimerManager invincibilityTimerManager;

    private SerializableLocation spawn;
    private boolean started;
    private boolean saved;

    public Game() {
        this.battlePlayerManager = new BattlePlayerManager();
        this.teamManager = new TeamManager(Battle.instance());
        this.invincibilityTimerManager = new InvincibilityTimerManager(Battle.instance().getConfig().getInt("config.invincibility-timer-duration"));

        this.spawn = null;
        this.started = false;
        this.saved = false;
    }

    public BattlePlayerManager getBattlePlayerManager() {
        return this.battlePlayerManager;
    }

    public TeamManager getTeamManager() {
        return this.teamManager;
    }

    public InvincibilityTimerManager getInvincibilityTimerManager() {
        return this.invincibilityTimerManager;
    }

    public boolean isStarted() {
        return started;
    }

    public void setStarted(boolean started) {
        this.started = started;
    }

    public SerializableLocation getSpawn() {
        return spawn;
    }

    public boolean isSaved() {
        return saved;
    }

    public void setSaved(boolean saved) {
        this.saved = saved;
    }

    public void setSpawn(SerializableLocation spawn) {
        this.spawn = spawn;
    }

}
