package at.er.ytbattle.battle;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.bukkit.DyeColor;

import at.er.ytbattle.battle.player.BattlePlayer;

public class TeamManager implements Serializable {

    private static final long serialVersionUID = 8263676998251210643L;

    private Map<TeamColor, Team> teams;

    public TeamManager(Battle battle) {
        this.teams = new HashMap<TeamColor, Team>();
        this.teams.put(TeamColor.WHITE, new Team(battle, TeamColor.WHITE, new ArrayList<BattlePlayer>(), 0));
        this.teams.put(TeamColor.YELLOW, new Team(battle, TeamColor.YELLOW, new ArrayList<BattlePlayer>(), 0));
        this.teams.put(TeamColor.GREEN, new Team(battle, TeamColor.GREEN, new ArrayList<BattlePlayer>(), 0));
        this.teams.put(TeamColor.CYAN, new Team(battle, TeamColor.CYAN, new ArrayList<BattlePlayer>(), 0));
        this.teams.put(TeamColor.PURPLE, new Team(battle, TeamColor.PURPLE, new ArrayList<BattlePlayer>(), 0));
        this.teams.put(TeamColor.BLUE, new Team(battle, TeamColor.BLUE, new ArrayList<BattlePlayer>(), 0));
        this.teams.put(TeamColor.RED, new Team(battle, TeamColor.RED, new ArrayList<BattlePlayer>(), 0));
        this.teams.put(TeamColor.BLACK, new Team(battle, TeamColor.BLACK, new ArrayList<BattlePlayer>(), 0));
    }

    public Team getTeam(TeamColor teamColor) {
        return this.teams.get(teamColor);
    }

    public Team getTeamByPlayer(BattlePlayer p) {
        for (Entry<TeamColor, Team> t : this.teams.entrySet()) {
            if (t.getValue().containsPlayer(p)) {
                return t.getValue();
            }
        }
        return null;
    }

    public Team getTeamByDyeColor(DyeColor dyeColor) {
        for (Entry<TeamColor, Team> t : this.teams.entrySet()) {
            if (t.getValue().getTeamColor().getDyeColor().equals(dyeColor)) {
                return t.getValue();
            }
        }
        return null;
    }

    public ArrayList<Team> getTeams() {
        ArrayList<Team> teamList = new ArrayList<Team>();
        for (Entry<TeamColor, Team> t : this.teams.entrySet()) {
            teamList.add(t.getValue());
        }
        return teamList;
    }

    public boolean isInTeam(BattlePlayer p) {
        if (this.getTeamByPlayer(p) == null) {
            return false;
        } else {
            return true;
        }
    }

    public boolean isLastTeam(Team team) {
        // TODO fix this method

        int count = 0;
        for (Entry<TeamColor, Team> t : this.teams.entrySet()) {
            if (t.getValue().hasLost()) {
                if (t.getValue() == team) {
                    return false;
                } else {
                    count += 1;
                }
            }
        }

        return count < 1;
    }

    public Team getLastTeam() {
        ArrayList<Team> team = new ArrayList<Team>();
        for (Team t : this.getTeams()) {
            if (t.hasLost() == false) {
                team.add(t);
            }
        }
        if (team.size() == 1) {
            return team.get(0);
        } else {
            return null;
        }
    }
}
