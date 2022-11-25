package fr.icom.info.m1.balleauprisonnier_fx.view;

import fr.icom.info.m1.balleauprisonnier_fx.model.Game;
import javafx.animation.AnimationTimer;
import javafx.beans.property.SimpleDoubleProperty;

public class GameTimer extends AnimationTimer {
	
	private Game game;
	private GameView gameView;
	private FPSCounter fpsCounter;
	
	
	public GameTimer(Game game, GameView gameView) {
		this.game = game;
		this.gameView = gameView;
		this.fpsCounter = new FPSCounter(100);
	}

	@Override
	public void handle(long currentNanoTime) {
        fpsCounter.addFrame(currentNanoTime);
        double deltaSpeed = 60.0 / fpsCounter.getFrameRate().doubleValue();
		game.nextFrame(deltaSpeed);
		gameView.nextFrame();
	}
	
	public SimpleDoubleProperty getFrameRate() {
		return fpsCounter.getFrameRate();
	}
	
}
