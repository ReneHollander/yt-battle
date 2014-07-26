package at.er.ytbattle.battle;

import at.er.ytbattle.battle.player.BattlePlayerManager;
import at.er.ytbattle.battle.timer.manager.InvincibilityTimerManager;
import at.er.ytbattle.util.ConfigurationHelper;
import at.er.ytbattle.util.SerializableLocation;
import at.er.ytbattle.util.timer.TimerManager;

public class Game {

    private BattlePlayerManager battlePlayerManager;
    private TeamManager teamManager;

    private TimerManager timeManager;
    private InvincibilityTimerManager invincibilityTimerManager;

    private SerializableLocation spawn;
    private boolean started;
    private boolean saved;

    public Game() {
        this.spawn = null;
        this.started = false;
        this.saved = false;
    }

    public void initManagers() {
        this.battlePlayerManager = new BattlePlayerManager();
        this.teamManager = new TeamManager();

        this.timeManager = new TimerManager();
        this.invincibilityTimerManager = new InvincibilityTimerManager(Battle.configurationHelper().getConfigFile().getInt(ConfigurationHelper.GAME_INVINCIBILITY_DURATION_PATH));
    }

    public TimerManager getTimerManager() {
        return this.timeManager;
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
