package fr.icom.info.m1.balleauprisonnier_fx.controler;

import javafx.scene.input.KeyCode;

public class PlayerKeyboardMap {

	private KeyCode moveLeft;
	private KeyCode moveRight;
	private KeyCode turnLeft;
	private KeyCode turnRight;
	private KeyCode shoot;
	private KeyCode exit;

	public PlayerKeyboardMap(KeyCode moveLeft, KeyCode moveRight, KeyCode turnLeft, KeyCode turnRight, KeyCode shoot, KeyCode exit) {
		this.moveLeft = moveLeft;
		this.moveRight = moveRight;
		this.turnLeft = turnLeft;
		this.turnRight = turnRight;
		this.shoot = shoot;
		this.exit = exit;
	}

	public PlayerAction input2Action(KeyCode input) {
		if (input.equals(moveLeft))
			return PlayerAction.MOVE_LEFT;

		else if (input.equals(moveRight))
			return PlayerAction.MOVE_RIGHT;

		else if (input.equals(turnLeft))
			return PlayerAction.TURN_LEFT;

		else if (input.equals(turnRight))
			return PlayerAction.TURN_RIGHT;

		else if (input.equals(shoot))
			return PlayerAction.SHOOT;
		
		else if (input.equals(exit))
			return PlayerAction.EXIT;

		else
			return PlayerAction.UNKNOWN;
	}

}
