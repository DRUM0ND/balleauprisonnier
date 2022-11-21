package fr.icom.info.m1.balleauprisonnier_fx.model.command;

import fr.icom.info.m1.balleauprisonnier_fx.view.GameTimer;

public class PauseGameCommand implements Command {
	
	private GameTimer gameTimer;
	public PauseGameCommand(GameTimer gameTimer) {
		this.gameTimer = gameTimer;
	}

	@Override
	public void handle() {
		this.gameTimer.stop();
	}

}
