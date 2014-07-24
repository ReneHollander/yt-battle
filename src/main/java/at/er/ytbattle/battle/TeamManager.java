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
        this.teams.put(TeamColor.WHITE, new Team(TeamColor.WHITE, new ArrayList<BattlePlayer>(), 0));
        this.teams.put(TeamColor.YELLOW, new Team(TeamColor.YELLOW, new ArrayList<BattlePlayer>(), 0));
        this.teams.put(TeamColor.GREEN, new Team(TeamColor.GREEN, new ArrayList<BattlePlayer>(), 0));
        this.teams.put(TeamColor.CYAN, new Team(TeamColor.CYAN, new ArrayList<BattlePlayer>(), 0));
        this.teams.put(TeamColor.PURPLE, new Team(TeamColor.PURPLE, new ArrayList<BattlePlayer>(), 0));
        this.teams.put(TeamColor.BLUE, new Team(TeamColor.BLUE, new ArrayList<BattlePlayer>(), 0));
        this.teams.put(TeamColor.RED, new Team(TeamColor.RED, new ArrayList<BattlePlayer>(), 0));
        this.teams.put(TeamColor.BLACK, new Team(TeamColor.BLACK, new ArrayList<BattlePlayer>(), 0));
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
