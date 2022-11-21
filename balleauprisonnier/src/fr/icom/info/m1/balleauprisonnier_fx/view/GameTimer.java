package fr.icom.info.m1.balleauprisonnier_fx.view;

import fr.icom.info.m1.balleauprisonnier_fx.model.Game;
import javafx.animation.AnimationTimer;

public class GameTimer extends AnimationTimer {
	
	private Game game;
	private GameView gameView;
	
	public GameTimer(Game game, GameView gameView) {
		this.game = game;
		this.gameView = gameView;
	}

	@Override
	public void handle(long currentNanoTime) {
		game.nextFrame();
		gameView.nextFrame();
	}
	
}
