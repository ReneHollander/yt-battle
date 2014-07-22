package at.er.ytbattle.battle.player;

import java.io.Serializable;

import org.bukkit.entity.Player;

public class BattlePlayer extends CustomPlayer implements Serializable {

	public BattlePlayer() {

	}

	public BattlePlayer(Player player) {
		super(player);
	}

}
