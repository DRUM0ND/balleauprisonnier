package fr.icom.info.m1.balleauprisonnier_fx.controler;

import fr.icom.info.m1.balleauprisonnier_fx.model.command.ExitCommand;
import fr.icom.info.m1.balleauprisonnier_fx.model.player.Player;
import javafx.scene.input.KeyCode;

public class PlayerController {

	private PlayerKeyboardMap playerKeyboardMap;
	private Player player;

	public PlayerController(Player player, PlayerKeyboardMap playerKeyboardMap) {
		this.player = player;
		this.playerKeyboardMap = playerKeyboardMap;
	}

	public void process(KeyCode input) {
		PlayerAction playerAction = playerKeyboardMap.input2Action(input);
		execute(playerAction);
	}

	private void execute(PlayerAction playerAction) {
		if (playerAction.equals(PlayerAction.SHOOT))
			player.shoot();
		else if (playerAction.equals(PlayerAction.MOVE_LEFT))
			player.moveLeft();
		else if (playerAction.equals(PlayerAction.MOVE_RIGHT))
			player.moveRight();
		else if (playerAction.equals(PlayerAction.TURN_LEFT))
			player.turnLeft();
		else if (playerAction.equals(PlayerAction.TURN_RIGHT))
			player.turnRight();
		else if (playerAction.equals(PlayerAction.EXIT))
			new ExitCommand().handle();
	}

}
