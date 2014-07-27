package at.er.ytbattle.plugin.command.handler;

import org.bukkit.material.Wool;

import at.er.ytbattle.plugin.BattlePlugin;
import at.er.ytbattle.plugin.Team;
import at.er.ytbattle.plugin.TeamManager;
import at.er.ytbattle.plugin.command.AbstractCommand;
import at.er.ytbattle.plugin.player.BattlePlayer;
import at.er.ytbattle.util.BattleUtils;

public class BattleCommandLife extends AbstractCommand {

    private TeamManager teamManager;

    public BattleCommandLife() {
        this.teamManager = BattlePlugin.game().getTeamManager();
    }

    @Override
    public boolean onCommand(String label, String[] args, BattlePlayer player) {
        if (BattlePlugin.game().isStarted()) {
            if (this.teamManager.isInTeam(player)) {
                Team t = this.teamManager.getTeamByPlayer(player);
                if (t.getLifes() > 0) {
                    player.getInventory().addItem(new Wool(t.getTeamColor().getDyeColor()).toItemStack(1));
                    t.setLifes(t.getLifes() - 1);
                    player.sendMessage(BattlePlugin.prefix() + "You recieved a new wool!");
                } else {
                    player.sendMessage(BattlePlugin.prefix() + "Your team hasn't enough lifes left!");
                }
                BattleUtils.updateScoreboard();
            } else {
                player.sendMessage(BattlePlugin.prefix() + "You aren't in a Team anymore!");
            }
        } else {
            player.sendMessage(BattlePlugin.prefix() + "The Game hasn't started yet");
        }
        return true;
    }
}
