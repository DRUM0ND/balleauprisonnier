package fr.icom.info.m1.balleauprisonnier_fx.model.command;

import fr.icom.info.m1.balleauprisonnier_fx.*;
import fr.icom.info.m1.balleauprisonnier_fx.view.GameTimer;

public class StartGameCommand implements Command {
	
	private GameTimer gameTimer;
	public StartGameCommand(GameTimer gameTimer) {
		this.gameTimer = gameTimer;
	}

	@Override
	public void handle() {
		this.gameTimer.start();
	}

}
