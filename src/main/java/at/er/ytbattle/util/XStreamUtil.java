package at.er.ytbattle.util;

import at.er.ytbattle.battle.Game;
import at.er.ytbattle.battle.Team;
import at.er.ytbattle.battle.TeamColor;
import at.er.ytbattle.battle.player.BattlePlayer;
import at.er.ytbattle.battle.timer.BlockPlaceTimer;

import com.thoughtworks.xstream.XStream;

public class XStreamUtil {

    public static XStream createXStream() {
        XStream xstream = new XStream();
        xstream.setMode(XStream.ID_REFERENCES);

        xstream.alias("game", Game.class);
        xstream.alias("battlePlayer", BattlePlayer.class);
        xstream.alias("teamColor", TeamColor.class);
        xstream.alias("team", Team.class);
        xstream.alias("blockPlaceTimer", BlockPlaceTimer.class);
        
        xstream.useAttributeFor(BattlePlayer.class, "uuid");
        xstream.useAttributeFor(BattlePlayer.class, "lastValidName");
        
        xstream.useAttributeFor(Team.class, "teamColor");
        
        return xstream;
    }

}
