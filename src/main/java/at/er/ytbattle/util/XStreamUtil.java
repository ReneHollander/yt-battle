package at.er.ytbattle.util;

import at.er.ytbattle.battle.Game;
import at.er.ytbattle.battle.Team;
import at.er.ytbattle.battle.TeamColor;
import at.er.ytbattle.battle.player.BattlePlayer;
import at.er.ytbattle.battle.timer.timeables.InvincibilityTimer;
import at.er.ytbattle.battle.timer.timeables.WoolPlaceTimer;

import com.thoughtworks.xstream.XStream;

public class XStreamUtil {

    public static XStream createXStream() {
        XStream xstream = new XStream();
        xstream.setMode(XStream.ID_REFERENCES);

        xstream.alias("game", Game.class);
        xstream.alias("battlePlayer", BattlePlayer.class);
        xstream.alias("teamColor", TeamColor.class);
        xstream.alias("team", Team.class);
        xstream.alias("blockPlaceTimer", WoolPlaceTimer.class);
        xstream.alias("invincibilityTimer", InvincibilityTimer.class);

        xstream.useAttributeFor(BattlePlayer.class, "uuid");
        xstream.useAttributeFor(BattlePlayer.class, "lastValidName");

        xstream.useAttributeFor(Team.class, "teamColor");

        // xstream.useAttributeFor(BlockPlaceTimerManager.class, "timetoplace");
        // xstream.useAttributeFor(BlockPlaceTimer.class, "initTime");
        // xstream.useAttributeFor(BlockPlaceTimer.class, "time");

        xstream.useAttributeFor(SerializableLocation.class, "world");
        xstream.useAttributeFor(SerializableLocation.class, "uuid");
        xstream.useAttributeFor(SerializableLocation.class, "x");
        xstream.useAttributeFor(SerializableLocation.class, "y");
        xstream.useAttributeFor(SerializableLocation.class, "z");
        xstream.useAttributeFor(SerializableLocation.class, "yaw");
        xstream.useAttributeFor(SerializableLocation.class, "pitch");

        return xstream;
    }

}
