package at.er.ytbattle.battle.command.handler;

import org.bukkit.material.Wool;

import at.er.ytbattle.battle.Battle;
import at.er.ytbattle.battle.Team;
import at.er.ytbattle.battle.TeamManager;
import at.er.ytbattle.battle.command.AbstractCommand;
import at.er.ytbattle.battle.player.BattlePlayer;

public class BattleCommandLife extends AbstractCommand {

    private TeamManager teamManager;

    public BattleCommandLife() {
        this.teamManager = Battle.instance().getGame().getTeamManager();
    }

    @Override
    public boolean onCommand(String label, String[] args, BattlePlayer player) {
        if (Battle.instance().getGame().isStarted()) {
            if (this.teamManager.isInTeam(player)) {
                Team t = this.teamManager.getTeamByPlayer(player);
                if (t.getLifes() > 0) {
                    player.getInventory().addItem(new Wool(t.getTeamColor().getDyeColor()).toItemStack(1));
                    t.setLifes(t.getLifes() - 1);
                    player.sendMessage(Battle.prefix() + "You recieved a new wool!");
                } else {
                    player.sendMessage(Battle.prefix() + "Your team hasn't enough lifes left!");
                }
                Battle.instance().updateScoreboard();
            } else {
                player.sendMessage(Battle.prefix() + "You aren't in a Team anymore!");
            }
        } else {
            player.sendMessage(Battle.prefix() + "The Game hasn't started yet");
        }
        return true;
    }
}
