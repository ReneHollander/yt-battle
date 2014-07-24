package at.er.ytbattle.battle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.bukkit.DyeColor;

import at.er.ytbattle.battle.player.BattlePlayer;

public class TeamManager {

    private Map<TeamColor, Team> teams;

    public TeamManager() {
        this.teams = new HashMap<TeamColor, Team>();
        for (TeamColor teamColor : TeamColor.values()) {
            Team team = new Team(teamColor);
            this.teams.put(teamColor, team);
        }
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
        return team == this.getLastTeam();
    }

    public Team getLastTeam() {
        int livingcount = 0;
        Team living = null;
        for (Team t : this.getTeams()) {
            if (t.hasLost() == false) {
                livingcount++;
                if (livingcount >= 2) {
                    return null;
                } else {
                    living = t;
                }
            }
        }
        if (livingcount == 1) {
            return living;
        } else {
            return null;
        }
    }
}
